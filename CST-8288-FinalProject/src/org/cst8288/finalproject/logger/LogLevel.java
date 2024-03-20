package org.cst8288.finalproject.logger;

/**
 * The LogLevel enum defines various levels of logging that can be used in the LMSLogger.
 * It includes levels TRACE, DEBUG, INFO, WARN, and ERROR, each with a specific integer value 
 * indicating their order or severity. TRACE is the most detailed and ERROR is the least detailed but most severe.
 * These levels are used to categorize and control the output of log messages.
 */
public enum LogLevel {
    TRACE(1),  // Most detailed information, typically for debugging at a fine-grained level
    DEBUG(2),  // General debugging information
    INFO(3),   // General information about the application's execution process
    WARN(4),   // Warning situations that are not errors but could be harmful
    ERROR(5);  // Error events that might still allow the application to continue running

    final int level;

    /**
     * Constructor for the LogLevel enum.
     *
     * @param level The integer level associated with the log level.
     */
    LogLevel(int level) 
    {
        this.level = level;
    }
}
