package org.cst8288.finalproject.logger;

/**
 * The LMSLogger class is a singleton implementation of a logger utility.
 * It allows for logging messages at different levels (TRACE, DEBUG, INFO, WARN, ERROR).
 * The singleton pattern ensures that only one instance of the logger is used throughout the application.
 * This logger class was inspired by an example found at: 
 * https://medium.com/@rosuth/implementing-a-singleton-logger-in-java-eec94d2556c9
 *
 * @see com.algonquin.cst8288.assignment2.logger.LogLevel
 */
public class LMSLogger {

    private static LMSLogger lmsLoggerInstance;
    private LogLevel currentLevel = LogLevel.INFO;
    
    private LMSLogger() 
    {
    
    }
    
    /**
     * Retrieves the single instance of LMSLogger. If it doesn't exist, a new one is created.
     *
     * @return The single instance of LMSLogger
     */
    public static LMSLogger getInstance() 
    {
        if (lmsLoggerInstance == null) 
        {
            lmsLoggerInstance = new LMSLogger();
        }
        return lmsLoggerInstance;
    }
    
    /**
     * Sets the logging level of the LMSLogger.
     *
     * @param level The LogLevel to set
     */
    public void setLogLevel(LogLevel level) 
    {
        this.currentLevel = level;
    }
    
    /**
     * Logs a message at the specified log level.
     *
     * @param level The level of the log message
     * @param message The message to log
     */
    public void log(LogLevel level, String message) 
    {
        if (level.level >= currentLevel.level) 
        {
            System.out.println("LogLevel."+ level + " : " + message);
        }
    }
  
    /**
     * Logs an exception and prints its stack trace.
     *
     * @param e The exception to log
     */
    public void logException(Exception e) 
    {
        error("Exception occurred: " + e.getMessage());
        e.printStackTrace(); 
    }
       
    // Simple methods for logging at specific levels
    public void trace(String message) 
    {
        log(LogLevel.TRACE, message);
    }

    public void debug(String message) 
    {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) 
    {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) 
    {
        log(LogLevel.WARN, message);
    }

    public void error(String message) 
    {
        log(LogLevel.ERROR, message);
    }
    

}
