package Model;

import lombok.Data;

@Data
public class Thing {

    public Thing() {}

    private boolean pickedUp;

    public Thing(boolean pickedUp) {this.pickedUp = pickedUp;}

    public boolean isPickedUp() {return pickedUp;}

    public void setPickedUp(boolean pickedUp) {this.pickedUp = pickedUp;}
}
