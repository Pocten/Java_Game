import View.GameFrame;
import lombok.Data;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

@Data
public class Main {
    private static final Logger logger = Logger.getGlobal();

    //start the game
    public static void main(String[] args) throws IOException, AWTException {
        logger.info("The game has begun");
        GameFrame frame = new GameFrame();
    }
}
