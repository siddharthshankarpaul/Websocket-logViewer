package com.sid.logback.appender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import com.sid.logback.websocket.WSServer;

import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.net.LoggingEventPreSerializationTransformer;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.spi.PreSerializationTransformer;

public class WebSocketAppender extends AppenderBase<ILoggingEvent> {
	
	private static final PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();
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
		ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
		//Serializable serEvent = pst.transform(event);
		EventToModel eventToModel = new EventToModel(event);
//		Serializable serEvent=eventToModel.toString();
//		try {
//			ObjectOutputStream oos = new ObjectOutputStream(baos);
//			oos.writeObject(serEvent);
//			oos.flush();
//			sender.send(ByteBuffer.wrap(baos.toByteArray()));
//		} catch (IOException ex) {
//			throw new RuntimeException(ex);
//		}
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
