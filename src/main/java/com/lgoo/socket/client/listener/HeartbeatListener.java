package com.lgoo.socket.client.listener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.lgoo.socket.client.Connection;

public class HeartbeatListener extends AbstractListener {

	public HeartbeatListener(Connection connection) {
		super(connection);
	}

	@Override
	public void doRead(InputStream in) throws Exception {

	}

	@Override
	public void doWrite(OutputStream out) throws InterruptedException, IOException  {
		int count = 0;
		while (super.connection().isConnected()) {
			Thread.sleep(1000);
			out.write(null);
			count = 0;
			count++;
			if (count > 10) {
				System.out.println("心跳无响应~~~");
				super.connection().unconnect();
			}

		}
	}

}
