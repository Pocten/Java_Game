package Model;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class GameOver{

    private Image door;
    private int x;
    private int y;

    public GameOver(int x, int y){
        ImageIcon icon_door = new ImageIcon("src/main/resources/images/door.png");
        this.door = icon_door.getImage();
        this.x = x;
        this.y = y;
    }

    /** try to open the door (game over)
     @param grandmother - player **/
    public boolean try_open(Grandmother grandmother){
        if (grandmother.getPosition_x() >= x - 10 && grandmother.getPosition_x() <= x+41){
            if (grandmother.getPosition_y() >= y - 10 && grandmother.getPosition_y() <= y+41){
                return true;
            }
        }
        return false;
    }

}
