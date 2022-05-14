package Controller;

import lombok.Data;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

@Data
public class OnKeyPressed extends KeyAdapter {

    MainController mainController;

    public OnKeyPressed(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_R){
            try {
                mainController.save_game();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (key == KeyEvent.VK_E){
            mainController.player_interaction();
        }

        if (key == KeyEvent.VK_1){
            mainController.set_item_hold(0);
        }else if (key == KeyEvent.VK_2) {
            mainController.set_item_hold(1);
        }

        if (key == KeyEvent.VK_LEFT) {
            mainController.setLeft(true);
            mainController.setRight(false);
            mainController.setUp(false);
            mainController.setDown(false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            mainController.setLeft(false);
            mainController.setRight(true);
            mainController.setUp(false);
            mainController.setDown(false);

        }
        if (key == KeyEvent.VK_UP) {
            mainController.setLeft(false);
            mainController.setRight(false);
            mainController.setUp(true);
            mainController.setDown(false);
        }
        if (key == KeyEvent.VK_DOWN) {
            mainController.setLeft(false);
            mainController.setRight(false);
            mainController.setUp(false);
            mainController.setDown(true);
        }
    }
    /** stops movement if the button is released **/
    @Override
    public void keyReleased(KeyEvent event){
        super.keyPressed(event);
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            mainController.setLeft(false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            mainController.setRight(false);
        }
        if (key == KeyEvent.VK_UP) {
            mainController.setUp(false);
        }
        if (key == KeyEvent.VK_DOWN) {
            mainController.setDown(false);
        }
    }

}
