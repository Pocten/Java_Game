package Model;

import Controller.Controller;
import Controller.Map;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Logger;

@Data
public class Grandmother extends Character {

    ImageIcon imagePlayerDown = new ImageIcon("src/main/resources/images/player_down.png");
    ImageIcon imagePlayerRight = new ImageIcon("src/main/resources/images/player_right.png");
    ImageIcon imagePlayerLeft = new ImageIcon("src/main/resources/images/player_left.png");
    ImageIcon imagePlayerUp = new ImageIcon("src/main/resources/images/player_up.png");

    private Image player;
    private final Map map;
    private final BufferedImage background;
    private String itemHold;
    private String[] inventory = {"sword", ""};
    private int heroWay;


    public Grandmother(int position_x, int position_y, int damage, int healthPower, Map map, BufferedImage image, int levelData) {
        super(position_x, position_y, damage, healthPower);

        this.player = imagePlayerDown.getImage();
        this.map = map;
        this.background = image;
        this.itemHold = inventory[0];
        if (levelData == 1) {this.inventory[1] = "key";}
    }

    private static Logger logger = Logger.getLogger(Grandmother.class.getName());

    //set drug holding
    public void itemPicked(int a) {
        itemHold = inventory[a];
    }

    public void findKey() {
        inventory[1] = "key";
    }

    /*if player holding sword - try to attack (check for a collision with the enemy) if player holding sword - try to open the door
     * @ArrayList<Employer> robots - array of enemyes
     * */
    public void use(ArrayList<Robot> robots, GameOver door, Controller controller) {
        if (itemHold == "sword") {
            for (Robot robot : robots) {
                switch (heroWay) {
                    case 1:
                        if (super.getPosition_y() + 15 >= robot.getPosition_y() - 15 && super.getPosition_y() + 15 <= robot.getPosition_y() + 46) {
                            if (robot.getPosition_x() + 31 <= super.getPosition_x() && robot.getPosition_x() + 41 >= super.getPosition_x()) {
                                robot.setHealthPower(robot.getHealthPower() - super.getDamage());
                            }
                        }
                    case 2:
                        if (super.getPosition_y() + 15 >= robot.getPosition_y() - 15 && super.getPosition_y() + 15 <= robot.getPosition_y() + 46) {
                            if (robot.getPosition_x() >= super.getPosition_x() + 31 && robot.getPosition_x() <= super.getPosition_x() + 41) {
                                robot.setHealthPower(robot.getHealthPower() - super.getDamage());
                            }
                        }
                    case 3:
                        if (super.getPosition_x() + 15 >= robot.getPosition_x() - 15 && super.getPosition_x() + 15 <= robot.getPosition_x() + 46) {
                            if (robot.getPosition_y() + 31 <= super.getPosition_y() && robot.getPosition_y() + 31 >= super.getPosition_y() - 10) {
                                robot.setHealthPower(robot.getHealthPower() - super.getDamage());
                            }
                        }
                    case 4:
                        if (super.getPosition_x() + 15 >= robot.getPosition_x() - 15 && super.getPosition_x() + 15 <= robot.getPosition_x() + 46) {
                            if (robot.getPosition_y() >= super.getPosition_y() + 31 && robot.getPosition_y() <= super.getPosition_y() + 41) {
                                robot.setHealthPower(robot.getHealthPower() - super.getDamage());
                            }
                        }
                }
            }
        }
        if (itemHold == "key") {
            if (door.try_open(this)) {
                controller.setWin_game(true);
                logger.info("Game over, you win");
            }
        }
    }

    public String[] getInventory() {
        return inventory;
    }

    /* check pixels on color and move if color not black*/
    public void moveLeft() {
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 1)) {
            this.player = imagePlayerLeft.getImage();
            super.setPosition_x(x - 4);
            this.heroWay = 1;
        }
    }

    /* check pixels on color and move if color not black*/
    public void moveRight() {
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 2)) {
            this.player = imagePlayerRight.getImage();
            super.setPosition_x(x + 4);
            this.heroWay = 2;
        }
    }

    /* check pixels on color and move if color not black*/
    public void moveUp() {
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 3)) {
            this.player = imagePlayerUp.getImage();
            super.setPosition_y(y - 4);
            this.heroWay = 3;
        }
    }

    /* check pixels on color and move if color not black*/
    public void moveDown() {
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 4)) {
            this.player = imagePlayerDown.getImage();
            super.setPosition_y(y + 4);
            this.heroWay = 4;
        }
    }

    public Image getPlayerImage() { return player; }
}
