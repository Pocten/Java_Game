package Controller;

import Model.*;
import Model.Robot;
import View.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class Controller extends JPanel implements ActionListener {
    Grandmother grandmother;
    ArrayList<Robot> robots;
    BufferedImage background;
    Map map;
    Dimension screen;
    GameOver door;
    Drug drug;
    Weapon weapon;
    Key key;

    private GamePanel gamePanel;
    private Timer timer;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private int enemy_way;
    private boolean win_game = false;
    private boolean inGame = true;



    public Controller(GamePanel gp, Grandmother grandmother, ArrayList<Robot> robots, BufferedImage image, Map map, Drug drug, Weapon weapon, Key key, GameOver door, Dimension screen) throws AWTException {
        this.background = image;
        this.gamePanel = gp;
        this.grandmother = grandmother;
        this.map = map;
        this.screen = screen;
        this.drug = drug;
        this.weapon = weapon;
        this.robots = robots;
        this.door = door;
        this.key = key;
        initGame();

    }

    private static Logger logger = Logger.getLogger(Controller.class.getName());

    public void initGame() throws AWTException {
        timer = new Timer(50, this);
        timer.start();
    }
    /**check if drug was picked up
     taking the middle of hero coord x and compare it with coord x +- half size drug
     than do the same things whith coord y
     **/
    public void checkItemPickedUp() {
        if (!drug.pickedUp()) {
            if (grandmother.getPosition_x()+15 >= drug.getX() && grandmother.getPosition_x()+15 <= drug.getX() + 31) {
                if (grandmother.getPosition_y()+15 <= drug.getY() + 31 && grandmother.getPosition_y()+15 >= drug.getY()) {
                    drug.setPickedUp(true);
                    logger.info("Item for heal was picked up");
                    drug.boostHealthPower(grandmother);
                }
            }

        }
        if (!weapon.pickedUp()) {
            if (grandmother.getPosition_x() + 15 >= weapon.getX() && grandmother.getPosition_x() + 15 <= weapon.getX() + 31) {
                if (grandmother.getPosition_y() + 15 <= weapon.getY() + 31 && grandmother.getPosition_y() + 15 >= weapon.getY()) {
                    weapon.setPickedUp(true);
                    logger.info("Item for damage was picked up");
                    weapon.boostDamage(grandmother);
                }
            }
        }
        if (robots.isEmpty()) {
            if (!key.pickedUp()) {
                if (grandmother.getPosition_x()+15 >= key.getX() && grandmother.getPosition_x()+15 <= key.getX() + 31) {
                    if (grandmother.getPosition_y()+15 <= key.getY() + 31 && grandmother.getPosition_y()+15 >= key.getY()) {
                        key.setPickedUp(true);
                        logger.info("Key was picked up");
                        grandmother.findKey();
                    }

                }
            }
        }
    }
    /** do move hero, update array of enemyes, check if items was picked up, check if the game was end **/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (grandmother.getHealthPower() <= 0){inGame = false;}
        if (inGame) {
            move();
            updating_list_enemy();
            for (Robot robot: robots) {
                moveEnemy();
            }
            checkItemPickedUp();
        }
        gamePanel.drawElements();
        if (!inGame || win_game){
            logger.info("Game over");
            timer.stop();
        }
    }

    public void setLeft(boolean left) { this.left = left; }

    public void setRight(boolean right) { this.right = right; }

    public void setUp(boolean up) { this.up = up; }

    public void setDown(boolean down) { this.down = down; }

    public boolean isWin_game() {
        return win_game;
    }

    public void setWin_game(boolean win_game) {
        this.win_game = win_game;
    }

    public boolean isInGame() {
        return inGame;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }


    public void save_game() throws IOException {
        FileWriter file = new FileWriter("src/main/resources/constructor/save_game.txt");
        file.write("Grandmother_x " + grandmother.getPosition_x()
                +"\nGrandmother_y " + grandmother.getPosition_y()
                +"\nCount_enemy " + robots.size()
                +"\nDrug " + drug.isPickedUp()
                +"\nWeapon " + weapon.isPickedUp()
                +"\nKey " + key.isPickedUp());
        file.close();
        logger.info("The game was saved");
    }

    /** check if enemy is dead, and remove him from the array if he is **/
    public void updating_list_enemy(){
        if (!robots.isEmpty()) {
            for (int a = 0; a < robots.size(); a++){
                if (robots.get(a).getHealthPower() <= 0) {
                    robots.remove(robots.get(a));
                    logger.info("enemy was killed");
                }
            }
        }
    }

    public void playerInteraction(){
        grandmother.use(robots, door, this);
    }
    public void setItemHold(int a){
        grandmother.itemPicked(a);
    }

    /** takes the direction of movement hero and do the move **/
    public void move() {
        if (left){
            grandmother.moveLeft();
        }
        if (right){
            grandmother.moveRight();
        }
        if (up){
            grandmother.moveUp();
        }
        if (down){
            grandmother.moveDown();
        }
    }
    /** takes the direction of movement enemy and do the move, during this, check if exist the colisions with coordinations of hero and if they are - attack hero **/
    public  void moveEnemy(){
        for (Robot robot: robots) {
            int i = robot.generateRandomWay();
            if (i == 0) {
                robot.moveLeft();
                if (robot.tryAttack(grandmother, 1)){
                    grandmother.setHealthPower(grandmother.getHealthPower() - robot.getDamage());
                }
            }
            if (i == 1) {
                robot.moveRight();
                if (robot.tryAttack(grandmother, 2)){
                    grandmother.setHealthPower(grandmother.getHealthPower() - robot.getDamage());
                }
            }
            if (i == 2) {
                robot.moveUp();
                if (robot.tryAttack(grandmother, 3)){
                    grandmother.setHealthPower(grandmother.getHealthPower() - robot.getDamage());
                }
            }
            if (i == 3) {
                robot.moveDown();
                if (robot.tryAttack(grandmother, 4)){
                    grandmother.setHealthPower(grandmother.getHealthPower() - robot.getDamage());
                }
            }
        }
    }
}
