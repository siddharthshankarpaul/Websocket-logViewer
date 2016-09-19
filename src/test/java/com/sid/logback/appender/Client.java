package com.sid.logback.appender;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.FrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

public class Client extends WebSocketClient {

	public Client(String hostName, Integer port) {
		// format ws://localhost:8887
		super(URI.create("ws://" + hostName + ":" + port));
	}

	@Override
	public void onMessage(String message) {
		System.out.println("Received message:" + message);
	}

	@Override
	public void onMessage(ByteBuffer blob) {
		 ByteArrayInputStream bis = new ByteArrayInputStream(blob.array());
			try {
				ObjectInput	in = new ObjectInputStream(bis);
				 String h2 = (String) in.readObject();
					System.out.println("Received byte:" + h2);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void onError(Exception ex) {
		System.out.println("Error: ");
		ex.printStackTrace();
	}

	@Override
	public void onOpen(ServerHandshake handshake) {
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("Closed: " + code + " " + reason);
	}

	@Override
	public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
		FrameBuilder builder = (FrameBuilder) frame;
		builder.setTransferemasked(true);
		getConnection().sendFrame(frame);
	}
	public static void main(String[] args) {
		Client client= new Client("127.0.0.1", 9001);
		client.connect();
		System.out.println("****starting client**");
	}
}
