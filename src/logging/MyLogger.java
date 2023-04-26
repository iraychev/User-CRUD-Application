package logging;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());
    private static final String LOG_FILE = "logs/errors.log";

    static{
        try{
            FileHandler fileHandler = new FileHandler(LOG_FILE);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to configure logger.", e);
        }
    }


    public static Logger getLogger() {
        return logger;
    }
}
