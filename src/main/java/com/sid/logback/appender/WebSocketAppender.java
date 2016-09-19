package com.sid.logback.appender;

import java.io.IOException;

import com.sid.logback.websocket.WSServer;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class WebSocketAppender extends AppenderBase<ILoggingEvent> {
	
	/**
     * The host name of websocket.
     */ 
    protected String hostName = null;
	/**
     * The port of websocket.
     */
    protected Integer port = null;
    
    /**
     * The websocket server.
     */
    private WSServer sender=null;
	@Override
	public void start() {
		sender=new WSServer(hostName, port);
		sender.start();
		super.start();
	}

	@Override
	public void stop() {
		if (!isStarted())
			return;
		try {
			sender.stop();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		super.stop();
	}

	@Override
	protected void append(ILoggingEvent event) {
		if (event == null || !isStarted())
			return;
		EventToModel eventToModel = new EventToModel(event);
		sender.send(eventToModel.toJsonString());
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
