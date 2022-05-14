package Model;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Key extends Thing{
    int x;
    int y;
    Image image;
    ImageIcon i_key = new ImageIcon("src/main/java/resources/images/key.png");

    public Key(boolean picked_up) {
        super(picked_up);
        this.x = 256;
        this.y = 200;
        this.image = i_key.getImage();
    }

    public boolean picked_up(){
        return super.isPicked_up();
    }
}
