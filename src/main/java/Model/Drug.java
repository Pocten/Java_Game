package Model;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Drug extends Thing{
    int x;
    int y;
    Image image;
    ImageIcon i_heal = new ImageIcon("src/main/resources/images/heal.png");

    public Drug(boolean picked_up) {
        super(picked_up);
        this.x = 256;
        this.y = 256;
        this.image = i_heal.getImage();
    }

    public void boost_hp(Grandmother grandmother){
        grandmother.setHealthPower(grandmother.getHealthPower() + 20);
    }

    public boolean picked_up(){
        return super.isPicked_up();
    }

}
