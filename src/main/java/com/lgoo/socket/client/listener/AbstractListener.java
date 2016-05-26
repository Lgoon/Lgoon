package com.lgoo.socket.client.listener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.lgoo.socket.client.Connection;


public abstract class AbstractListener implements SocketListener {

	private Connection connection;
	
	private boolean isActive = true;
	
	private Thread writeThread;
	
	private Thread readThread;
	
	protected Connection connection() {
		return connection;
	}
	
	public AbstractListener(Connection connection) {
		this.connection = connection;
	}
	
	public void listen() {
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		this.writeThread = new Thread() {
			public void run(){
				try {
					while (true && isActive()) {
						doWrite(connection.getOutputStream());
					}
				} catch (Exception e) {
					e.printStackTrace();
					isActive = false;
					connection.unconnect();
				}
				
			}
		};
		
		this.readThread = new Thread() {
			public void run(){
				try {
					while (true && isActive()) {
						doRead(connection.getInputStream());
					}
				} catch (Exception e) {
					e.printStackTrace();
					isActive = false;
					connection.unconnect();
				}
			}
		};
		
		writeThread.start();
		readThread.start();
	}
	
	@Override
	public void shutdown() {
		isActive = false;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public abstract void doRead(InputStream in) throws Exception;
	
	public abstract void doWrite(OutputStream out) throws Exception;

}
