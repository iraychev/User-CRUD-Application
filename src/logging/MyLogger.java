package logging;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    static{
        try{
            FileHandler fileHandler = new FileHandler("logs/errors.log");
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
