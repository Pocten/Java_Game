package Controller;

import lombok.Data;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

@Data
public class KeyHandler extends KeyAdapter {

    Controller controller;

    public KeyHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_R){
            try {
                controller.save_game();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (key == KeyEvent.VK_E){
            controller.playerInteraction();
        }

        if (key == KeyEvent.VK_1){
            controller.setItemHold(0);
        }else if (key == KeyEvent.VK_2) {
            controller.setItemHold(1);
        }

        if (key == KeyEvent.VK_LEFT) {
            controller.setLeft(true);
            controller.setRight(false);
            controller.setUp(false);
            controller.setDown(false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            controller.setLeft(false);
            controller.setRight(true);
            controller.setUp(false);
            controller.setDown(false);

        }
        if (key == KeyEvent.VK_UP) {
            controller.setLeft(false);
            controller.setRight(false);
            controller.setUp(true);
            controller.setDown(false);
        }
        if (key == KeyEvent.VK_DOWN) {
            controller.setLeft(false);
            controller.setRight(false);
            controller.setUp(false);
            controller.setDown(true);
        }
    }
    /** stops movement if the button is released **/
    @Override
    public void keyReleased(KeyEvent event){
        super.keyPressed(event);
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            controller.setLeft(false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            controller.setRight(false);
        }
        if (key == KeyEvent.VK_UP) {
            controller.setUp(false);
        }
        if (key == KeyEvent.VK_DOWN) {
            controller.setDown(false);
        }
    }

}
