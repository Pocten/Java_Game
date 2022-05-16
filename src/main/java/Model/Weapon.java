package Model;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Weapon extends Thing{
    int x;
    int y;
    Image image;
    ImageIcon imageWeapon = new ImageIcon("src/main/resources/images/weapon.png");

    public Weapon(boolean picked_up) {
        super(picked_up);
        this.x = 600;
        this.y = 400;
        this.image = imageWeapon.getImage();
    }

    public void boostDamage(Grandmother grandmother) {grandmother.setDamage(grandmother.getDamage() + 25);}

    public boolean pickedUp() {return super.isPickedUp();}

}
