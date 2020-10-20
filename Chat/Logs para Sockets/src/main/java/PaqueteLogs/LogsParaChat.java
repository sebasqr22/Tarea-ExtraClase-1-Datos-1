package PaqueteLogs;
import org.apache.logging.log4j.Logger;

import jdk.internal.org.jline.utils.Log;

import org.apache.logging.log4j.LogManager;

public class LogsParaChat {

	private static final Logger log = LogManager.getLogger(LogsParaChat.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Ete sech");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error", new NullPointerException());
		log.fatal("error");
		log.trace("trace");
	}

}
