package core;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MiLogger {
	
	private MiLogger() {
	}
	
	private static Logger logger = null; 

	public static Logger get() {
		if ( logger == null ) {
			logger = Logger.getLogger("Mi Programa");	
			Logger.getLogger("").getHandlers()[0].setLevel(Level.ALL);		
		}
		return logger;
	}
	

}
