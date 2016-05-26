package com.lgoo.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.lgoo.socket.client.listener.SocketListener;

public class Connection {
	private Socket socket;
	private String host;
	private int port;
	private ListenerManager listenerManager;
	
	private boolean conneced = false;
	
	public Connection(String host,int port) throws Exception{
		this.host = host;
		this.port = port;
	}
	
	public void connect() throws Exception {
		socket = new Socket(host, port);
		listenerManager = new ListenerManager(this);
		listenerManager.initDefaultLisener();
		conneced = true;
		listenerManager.lisen();
		
	}
	
	public void reconnect() throws Exception {
		listenerManager.shutdown();
		connect();
	}
	
	public boolean isConnected() {
		return conneced;
	}
	
	
	public InputStream getInputStream() {
		try {
			return socket.getInputStream();
		} catch (IOException e) {
			conneced = false;
			return null;
		}
	}
	
	public OutputStream getOutputStream() {
		try {
			return socket.getOutputStream();
		} catch (IOException e) {
			conneced = false;
			return null;
		}
	}
	
	public void close() {
		try {
			this.socket.close();
		} catch (IOException e) {
			this.socket = null;
		}
	}

	public void unconnect() {
		this.conneced = false;
	}
	
	public void addListen(SocketListener listener) {
		listenerManager.addLisener(listener);
	}
	
}
