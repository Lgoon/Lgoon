package com.lgoo.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientMain extends JFrame {
	public static void main(String[] args) throws Exception {
		final NettyClient client = new NettyClient("127.0.0.1", 8111);
		ClientMain main = new ClientMain();

		NettyClient.getThreadPool().execute(client);
		
		final JTextField content = new JTextField();
		content.setSize(100, 100);
		
		JTextField custId = new JTextField();
		content.setSize(100, 100);
		
		JTextField channelType = new JTextField();
		content.setSize(100, 100);
		
		JTextField loginToken = new JTextField();
		content.setSize(100, 100);
		
		JButton btn = new JButton("发送");
		btn.setSize(100, 50);

		
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.sendMsg(content.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.add(custId, BorderLayout.CENTER);
		pnl.add(channelType, BorderLayout.CENTER);
		pnl.add(loginToken, BorderLayout.CENTER);
		pnl.add(content, BorderLayout.CENTER);
		pnl.add(btn, BorderLayout.SOUTH);

		main.add(pnl);
		main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		main.setSize(300, 500);
		main.setVisible(true);
		main.setResizable(false);

		main.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	//	http://192.168.41.193:8081/hsim-psi/userAuthCenter/consumerAuth?custId=0603200000000000000&channelType=1&loginToken=07b9360c25aaa898098016a5bf1568ac5ec25e66ffaa06eda9cb79486c7dcc21
		
		
	}
}
