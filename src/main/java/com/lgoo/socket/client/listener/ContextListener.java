package com.lgoo.socket.client.listener;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

import com.lgoo.socket.client.Connection;
import com.lgoo.socket.handler.DispatchHandler;
import com.lgoo.socket.handler.impl.DispatchHandlerImpl;
import com.lgoo.util.ByteUtils;
import com.lgoo.util.HeadEntity;

public class ContextListener extends AbstractListener {

	public ContextListener(Connection connection) {
		super(connection);
	}

	private byte[] caches = new byte[0];

	@Override
	public void doRead(InputStream in) throws Exception {
		byte[] bs = new byte[1];
		int len = in.read(bs);
		if (len > 0) {

			caches = ByteUtils.byteMerger(caches, bs);

			if (caches.length < HeadEntity.HEAD_LEN) {
				return;
			}

			byte[] header = ByteUtils.getHeader(caches);
			HeadEntity head = new HeadEntity(header);

			String tag = head.getTag();
			if (!tag.equals("hs")) {
				caches = new byte[0];
				return;
			}

			int length = head.getPkLength();
			if (caches.length < length) {
				return;
			}

			byte[] content = ByteUtils.getContent(caches, length);
			byte[] newCaches = new byte[caches.length - length];

			ByteBuffer cachesBf = ByteBuffer.wrap(caches, length, caches.length - length);
			cachesBf.get(newCaches, 0, caches.length - length);

			caches = newCaches;
			
			
			DispatchHandler dispatch = new DispatchHandlerImpl();
			
			dispatch.dispatch(head.getCmdId(), content);

			System.out.println("server----->client" + new String(content));
		}

	}

	@Override
	public void doWrite(OutputStream out) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("你已连接服务器，请输入cmdid 选择操作!");
		String c = sc.next();
		short cid;
		if (c.indexOf("0x") != -1) {
			c = c.substring(2);
			cid = Short.parseShort(c,16);
		} else {
			cid = Short.parseShort(c,16);
		}
		
		if (!isActive()) {
			return;
		}
		
		DispatchHandler dispatch = new DispatchHandlerImpl();
		dispatch.dispatch(cid, out);
		
	}

}
