package com.lgoo.socket.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lgoo.socket.client.listener.ContextListener;
import com.lgoo.socket.client.listener.ReconnectionListener;
import com.lgoo.socket.client.listener.SocketListener;

public class ListenerManager {
    
    private List<SocketListener> listeners = new ArrayList<SocketListener>();
    
    private Map<String,SocketListener> sysListener = new HashMap<String,SocketListener>();
    
    private static final String CONTEXT_LISTENER = "CONTEXT";
    
    private  Connection connection;
    
    public ListenerManager(Connection connection) {
    	this.connection = connection;
    }
    
    public void addLisener(SocketListener listener) {
    	this.listeners.add(listener);
    }
    
    public void initDefaultLisener() throws IOException {
    	SocketListener contextListenner = new ContextListener(this.connection);
    	SocketListener reconnectionListener = new ReconnectionListener(this.connection);
//    	SocketListener heartListener = new HeartbeatListener(connection);
    	listeners.add(contextListenner);
    	listeners.add(reconnectionListener);
    	
    	sysListener.put(CONTEXT_LISTENER, contextListenner);
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

	public ContextListener getContextListener() {
		return (ContextListener) sysListener.get(CONTEXT_LISTENER);
	}
    
}
