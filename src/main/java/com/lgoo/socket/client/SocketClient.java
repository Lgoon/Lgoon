package com.lgoo.socket.client;


public class SocketClient {
	public static void main(String[] args) throws Exception {
		Connection connection = new Connection("192.168.229.139", 13000);
//		Connection connection = new Connection("127.0.0.1", 8111);
		connection.connect();
	}
}
