package Model;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Key extends Thing{
    int x;
    int y;
    Image image;
    ImageIcon imageKey = new ImageIcon("src/main/resources/images/key.png");

    public Key(boolean picked_up) {
        super(picked_up);
        this.x = 80;
        this.y = 500;
        this.image = imageKey.getImage();
    }

    public boolean pickedUp(){
        return super.isPickedUp();
    }
}
