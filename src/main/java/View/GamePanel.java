package View;

import Controller.MainController;
import Controller.Map;
import Controller.OnKeyPressed;
import Model.*;
import Model.Robot;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GamePanel extends JPanel {
    BufferedImage image = ImageIO.read(new File("src/main/resources/images/background.png"));
    BufferedImage win =   ImageIO.read(new File("src/main/resources/images/win.jpg"));
    BufferedImage lose =  ImageIO.read(new File("src/main/resources/images/lose.png"));
    FileReader level = new FileReader("src/main/resources/constructor/level1.txt");

    MainController controller;
    Grandmother grandmother;
    GameOver door;
    Key key;
    ArrayList<Robot> robots = new ArrayList();
    Map map;
    Drug drug;
    Weapon weapon;
    JLabel label;

    public GamePanel(Dimension screen) throws IOException, AWTException {
        Scanner scan = new Scanner(level);
        int i = 0;
        int[] level_data = new int[6];

        while(scan.hasNextLine()){
            level_data[i] = Integer.parseInt(scan.nextLine());
            i++;
        }

        map = new Map();
        grandmother = new Grandmother(64,64, level_data[0], level_data[1], map, image, level_data[2]);

        if (level_data[2] == 1){
            key = new Key(true);
        }else {
            key = new Key(false);
        }

        label = new JLabel();
        add(label);

        for (int a = 0; a < level_data[3]; a++){
            robots.add(new Robot(512+(a*50), 160, level_data[4],level_data[5], map, image));
        }

        drug = new Drug(false);
        weapon = new Weapon(false);
        door = new GameOver(390, 740);
        controller = new MainController(this, grandmother, robots, image, map, drug, weapon,key, door,screen);
//        setBackground(Color.white);
        addKeyListener(new OnKeyPressed(controller));
        setFocusable(true);

    }



    public void drawElements() {
        label.setText("Health: " + grandmother.getHealthPower() + "Damage: " + grandmother.getDamage() + "   Inventory: 1) " + grandmother.getInventory()[0] + " 2) " + grandmother.getInventory()[1] + " Holden item: " + grandmother.getItem_hold());
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (controller.isInGame() && !controller.isWin_game()) {
            g.drawImage(image, 0, 0, this);
            g.drawImage(door.getDoor(), door.getX(), door.getY(), this);
            g.drawImage(grandmother.getPlayerImage(), grandmother.getPosition_x(), grandmother.getPosition_y(), this);
            if (controller.getRobots().isEmpty() && !key.isPicked_up()) {
                g.drawImage(key.getImage(), key.getX(), key.getY(), this);
            }
            for (Robot robot : controller.getRobots()) {
                g.drawImage(robot.getPlayerImage(), robot.getPosition_x(), robot.getPosition_y(), this);
            }
            if (!drug.isPicked_up()) {
                g.drawImage(drug.getImage(), drug.getX(), drug.getY(), this);
            }
            if (!weapon.isPicked_up()) {
                g.drawImage(weapon.getImage(), weapon.getX(), weapon.getY(), this);
            }
        }if (!controller.isInGame()){
            g.drawImage(lose, 0, 0, this);
        }if (controller.isWin_game()){
            g.drawImage(win, 0, 0, this);
        }

    }
}
