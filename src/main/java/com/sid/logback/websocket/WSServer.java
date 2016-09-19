package com.sid.logback.websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class WSServer extends WebSocketServer {


	public WSServer(String hostName, Integer port) {
		super(new InetSocketAddress(hostName, port));
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println("open");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("close");

	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("Received:" + message);

	}
	@Override
	public void onMessage(WebSocket conn, ByteBuffer message) {
		System.out.println("Received bytes:" + message); 
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
	}

	/**
     * Sends message to clients.
     */
	public void send(ByteBuffer byteMessage) {
		for(WebSocket conn:this.connections()){
			conn.send(byteMessage.duplicate());
		}		
	}
	public void send(String message) {
		for(WebSocket conn:this.connections()){
			synchronized(conn){
			 conn.send(message);
			}
		}		
	}
}
