package com.lgoo.socket.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lgoo.socket.client.listener.ContextListener;
import com.lgoo.socket.client.listener.ReconnectionListener;
import com.lgoo.socket.client.listener.SocketListener;

public class ListenerManager {
    
    private List<SocketListener> listeners = new ArrayList<SocketListener>();
    
    private  Connection connection;
    
    public ListenerManager(Connection connection) {
    	this.connection = connection;
    }
    
    public void addLisener(SocketListener listener) {
    	this.listeners.add(listener);
    }
    
    public void initDefaultLisener() throws IOException {
    	SocketListener inlisenter = new ContextListener(this.connection);
    	SocketListener reconnectionListener = new ReconnectionListener(this.connection);
//    	SocketListener heartListener = new HeartbeatListener(connection);
    	listeners.add(inlisenter);
    	listeners.add(reconnectionListener);
//    	listeners.add(heartListener);
    }
    
    public void lisen() {
    	for (int i = 0; i < listeners.size(); i++) {
    		listeners.get(i).listen();
		}
    }

	public void shutdown() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).shutdown();
		}
	}
    
}
