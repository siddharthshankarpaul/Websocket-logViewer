package com.sid.logback.appender;

import org.slf4j.LoggerFactory;

public class LogGenerator {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(LogGenerator.class);

	public static void main(String[] args) {
		int i=0;
		while (true) {
			if(i%4==0)
			 logger.info("Sending log {}",i);
			else if(i%3==0)
				logger.debug("Sending debug log {}",i);
			else if(i%2 ==0)
				logger.warn("sending warn message {}",i);
			else 
				logger.error("Sent trace message {}",i);
			try {
				i++;
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
