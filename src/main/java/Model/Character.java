package Model;

import lombok.Data;

@Data
public class Character {

    private int position_x;
    private int position_y;
    private int damage;
    private int healthPower;


    public Character(int position_x, int position_y, int damage, int healthPower) {
        this.position_x = position_x;
        this.position_y = position_y;
        this.damage = damage;
        this.healthPower = healthPower;
    }

    public Character() {}
}
