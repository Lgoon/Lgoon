package com.lgoo.socket.client.listener;

public interface SocketListener extends Runnable {
	void listen();
	void shutdown();
}
