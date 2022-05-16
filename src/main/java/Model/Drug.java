package Model;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Drug extends Thing{
    int x;
    int y;
    Image image;
    ImageIcon imageHeal = new ImageIcon("src/main/resources/images/drug.png");

    public Drug(boolean pickedUp) {
        super(pickedUp);
        this.x = 256;
        this.y = 200;
        this.image = imageHeal.getImage();
    }

    public void boostHealthPower(Grandmother grandmother){
        grandmother.setHealthPower(grandmother.getHealthPower() + 50);
    }

    public boolean pickedUp(){
        return super.isPickedUp();
    }

}
