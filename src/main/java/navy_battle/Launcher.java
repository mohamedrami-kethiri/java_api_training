package navy_battle;

import java.io.IOException;
import java.util.logging.Logger;

public class Launcher {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(String.valueOf(Launcher.class));
        try {
            if (args.length == 0) {
                logger.severe("Vous devez respecter la syntaxe :) tel que : Launcher [port] [url]");
                System.exit(-1);
            }

            int port = Integer.parseInt(args[0]);
            logger.info("L'application est en cours de démarrage, port " + port);
            new MyApi().start(port, args.length > 1 ? args[1] : null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
