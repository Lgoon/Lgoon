package com.lgoo.socket.client.listener;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.jvnet.hk2.internal.ThreeThirtyResolver;

import com.lgoo.socket.client.Connection;

public class ReconnectionListener extends AbstractListener {

	public ReconnectionListener(Connection connection) {
		super(connection);
	}

	@Override
	public void doRead(InputStream in) throws Exception {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e){
			
		}
		
		while (!super.connection().isConnected()) {
			System.out.println("你已掉线，正在尝试重连……");

			int count = 1;
			while (count < 6 && !super.connection().isConnected()) {
				try {
					System.out.println("自动重连中………… 尝试第" + count + "次重连");
					Thread.sleep(1000 * count);
					super.connection().reconnect();
					if (super.connection().isConnected()) {
						System.out.println("重连成功");
						return;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("重连失败……");
				} finally {
					count++;
				}
			}
			
			//自动重连失败  进入手动重连
			System.out.println("多次重连失败是否手动重连（y/n）");
			while (!super.connection().isConnected()) {
				try {
					Scanner scanner = new Scanner(System.in);
					if (scanner.next().equals("y")) {
						if (!super.connection().isConnected()) {
							super.connection().reconnect();
							System.out.println("重连成功!!");
							this.shutdown();
							return;
						}
					} else {
						this.shutdown();
						return;
					}
					// Successfully reconnected.
				} catch (Exception e) {
					System.out.println("重连失败！是否继续？");
				}
			}
		}
	}

	@Override
	public void doWrite(OutputStream out) throws Exception {
		
	}
}
