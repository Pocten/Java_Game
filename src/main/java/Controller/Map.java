package Controller;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public class Map {

    public Map () throws AWTException {
        Robot r = new Robot();
    }

    /**take background and check if the pixels are black @return false otherwise @return false
     @param  image - background
     @param  x - coordination x, where hero want to go
     @param  y - coordination y, where hero want to go
     @param way - the direction the hero wants to go
     **/
    public boolean CheckPixelColor (BufferedImage image, int x, int y, int way) {
        int color;
        int start_x = x;
        int start_y = y;
        switch (way){
            case 1:
                for (int a = 1; a < 5; a++) {
                    x--;
                    for (int b = 0; b < 32; b++){
                        y++;
                        color = image.getRGB(x,y);
                        if (color == -16777216) {
                            return false;
                        }
                        else if (b == 31){
                            if (a == 4) x = start_x;
                            y = start_y;
                        }
                    }
                }
                return true;
            case 2:
                for (int a = 1; a < 5; a++) {
                    x++;
                    for (int b = 0; b < 32; b++){
                        y ++;
                        color = image.getRGB(x+31,y);
                        if (color == -16777216) {
                            return false;
                        }
                        else if (b == 31){
                            if (a == 4) x = start_x;
                            y = start_y;
                        }
                    }
                }
                return true;
            case 3:
                for (int a = 1; a < 6; a++) {
                    y--;
                    for (int b = 0; b < 32; b++){
                        x++;
                        color = image.getRGB(x,y);
                        if (color == -16777216) {
                            return false;
                        } else if (b == 31){
                            x = start_x;
                            if (a == 4) y = start_y;
                        }
                    }
                }
                return true;
            case 4:
                for (int a = 1; a < 6; a++) {
                    y++;
                    for (int b = 0; b < 32; b++){
                        x++;
                        color = image.getRGB(x,y+31);
                        if (color == -16777216) {
                            return false;
                        } else if (b == 31){
                            x = start_x;
                            if (a == 4) y = start_y;
                        }
                    }
                }
                return true;
            default:
                return false;
        }

    }

}
