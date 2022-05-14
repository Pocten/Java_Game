package Model;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Weapon extends Thing{
    int x;
    int y;
    Image image;
    ImageIcon i_damage = new ImageIcon("src/main/resources/images/damage.png");

    public Weapon(boolean picked_up) {
        super(picked_up);
        this.x = 512;
        this.y = 256;
        this.image = i_damage.getImage();
    }

    public void boost_damage(Grandmother grandmother) {
        grandmother.setDamage(grandmother.getDamage() + 10);
    }

    public boolean picked_up() {
        return super.isPicked_up();
    }

}
