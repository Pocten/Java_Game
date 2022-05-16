package Model;

import Controller.Map;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Data
public class Robot extends Character{

    ImageIcon imageEnemyDown = new ImageIcon("src/main/resources/images/enemy_down.png");
    ImageIcon imageEnemyRight = new ImageIcon("src/main/resources/images/enemy_right.png");
    ImageIcon imageEnemyLeft = new ImageIcon("src/main/resources/images/enemy_left.png");
    ImageIcon imageEnemyUp = new ImageIcon("src/main/resources/images/enemy_up.png");

    private Image robot;
    private final Map map;
    private final BufferedImage background;
    int counter = 0;
    int way;
    Random random = new Random();

    public Robot(int position_x, int position_y, int damage, int hp, Map map, BufferedImage image) {
        super(position_x, position_y, damage, hp);
        this.robot = imageEnemyDown.getImage();
        this.map = map;
        this.background = image;
    }

    /** generate random way for enemyes for the next 15 steps **/
    public int generateRandomWay(){
        counter++;

        if (counter == 15){
            way = random.nextInt(4);
            counter = 0;
        }
        return way;
    }

    public Image getPlayerImage() { return robot; }


    /** depending on the direction, reads out if there is a collision with the player's coordinates, if there it is - attack
     @param wayMove - way of move enemy
     @param grandmother - player
     **/
    public boolean tryAttack(Grandmother grandmother, int wayMove){
        switch (wayMove){
            case 1:
                if (grandmother.getPosition_x()+32 == super.getPosition_x()){
                    int y = grandmother.getPosition_y();
                    for (int a = 0; a < 32; a++){
                        y++;
                        if (y >= super.getPosition_y() && y <= super.getPosition_y()+31){
                            return true;
                        }
                    }
                }else return false;
            case 2:
                if (grandmother.getPosition_x()-1 == super.getPosition_x()+31){
                    int y = grandmother.getPosition_y();
                    for (int a = 0; a < 32; a++){
                        y++;
                        if (y >= super.getPosition_y() && y <= super.getPosition_y()+31){
                            return true;
                        }
                    }
                }else return false;
            case 3:
                if (grandmother.getPosition_y()+32 == super.getPosition_y()){
                    int x = grandmother.getPosition_x();
                    for (int a = 0; a < 32; a++) {
                        x++;
                        if (x >= super.getPosition_x() && x <= super.getPosition_x() + 31) {
                            return true;
                        }
                    }
                }else return false;
            case 4:
                if (grandmother.getPosition_y()-1 == super.getPosition_y()+31){
                    int x = grandmother.getPosition_x();
                    for (int a = 0; a < 32; a++) {
                        x++;
                        if (x >= super.getPosition_x() && x <= super.getPosition_x() + 31) {
                            return true;
                        }
                    }

                }else return false;
            default:
                return false;
        }

    }
    /** check pixels on color and move if color not black **/
    public void moveLeft(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 1)) {
            this.robot = imageEnemyLeft.getImage();
            super.setPosition_x(x-4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void moveRight(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 2)) {
            this.robot = imageEnemyRight.getImage();
            super.setPosition_x(x + 4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void moveUp(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 3)) {
            this.robot = imageEnemyUp.getImage();
            super.setPosition_y(y - 4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void moveDown(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 4)) {
            this.robot = imageEnemyDown.getImage();
            super.setPosition_y(y + 4);
        }
    }
}
