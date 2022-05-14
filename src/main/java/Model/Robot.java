package Model;

import Controller.Map;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Data
public class Robot extends Character{

    private Image robot;
    ImageIcon icon_enemy_down = new ImageIcon("src/main/java/resources/images/enemy_down.png");
    ImageIcon icon_enemy_right = new ImageIcon("src/main/java/resources/images/enemy_right.png");
    ImageIcon icon_enemy_left = new ImageIcon("src/main/java/resources/images/enemy_left.png");
    ImageIcon icon_enemy_up = new ImageIcon("src/main/java/resources/images/enemy_up.png");
    private final Map map;
    private final BufferedImage background;
    int counter = 0;
    int way;
    Random random = new Random();

    public Robot(int position_x, int position_y, int damage, int hp, Map map, BufferedImage image) {
        super(position_x, position_y, damage, hp);
        this.robot = icon_enemy_down.getImage();
        this.map = map;
        this.background = image;
    }
    /** generate random way for enemyes for the next 15 steps **/
    public int generate_random_way(){
        counter++;

        if (counter == 15){
            way = random.nextInt(4);
            counter = 0;
        }
        return way;
    }

    public Image getPlayerImage() { return robot; }

    /** depending on the direction, reads out if there is a collision with the player's coordinates, if there it is - attack
     @param way_move - way of move enemy
     @param grandmother - player
     **/
    public boolean try_attack(Grandmother grandmother, int way_move){

        switch (way_move){
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
    public void move_left(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 1)) {
            this.robot = icon_enemy_left.getImage();
            super.setPosition_x(x-4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void move_right(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 2)) {
            this.robot = icon_enemy_right.getImage();
            super.setPosition_x(x + 4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void move_up(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 3)) {
            this.robot = icon_enemy_up.getImage();
            super.setPosition_y(y - 4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void move_down(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 4)) {
            this.robot = icon_enemy_down.getImage();
            super.setPosition_y(y + 4);
        }
    }
}
