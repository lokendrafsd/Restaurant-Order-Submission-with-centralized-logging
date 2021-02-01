package main.java.com.foodrace.services.order.create.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Queue;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import main.java.com.foodrace.services.order.create.model.LoggingDetailsModel;

/**
 * LoggingComponent class This class is intended to be use with the default
 * logging class of java It save the log in an XML file and display a friendly
 * message to the user
 * 
 * @author lokendrav
 */
public class LoggingComponent {

	protected static final Logger logger = Logger.getLogger("MyLog");

	/**
	 * log Method enable to log all exceptions to a file and display user message on
	 * demand
	 * 
	 * @param logQueue
	 */
	public static void log(Queue<LoggingDetailsModel> logQueue) {
		int size = logQueue.size();
		FileHandler fh = null;
		try {
			fh = new FileHandler(ApplicationConstants.LOG_FILE_PATH + LocalDate.now().toString()
					+ ApplicationConstants.LOG_FILE_EXTENSION_TXT, true);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			for (int i = 0; i < size; i++) {
				LoggingDetailsModel logInstance = logQueue.poll();
				switch (logInstance.getLevel()) {
				case "severe":
					logger.log(Level.SEVERE, logInstance.getMsg());
					break;
				case "warning":
					logger.log(Level.WARNING, logInstance.getMsg());
					break;
				case "info":
					logger.log(Level.INFO, logInstance.getMsg());
					break;
				case "config":
					logger.log(Level.CONFIG, logInstance.getMsg());
					break;
				case "fine":
					logger.log(Level.FINE, logInstance.getMsg());
					break;
				case "finer":
					logger.log(Level.FINER, logInstance.getMsg());
					break;
				case "finest":
					logger.log(Level.FINEST, logInstance.getMsg());
					break;
				default:
					logger.log(Level.CONFIG, logInstance.getMsg());
					break;
				}
			}
		} catch (SecurityException | IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			if (fh != null)
				fh.close();
		}

	}

}
