package com.lgoo.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.ByteBuffer;

import javax.swing.JOptionPane;

import com.lgoo.util.ByteUtils;
import com.lgoo.util.HeadEntity;
 
public class NettyClientHandler extends  SimpleChannelInboundHandler<Object> {
	 protected void channelRead(ChannelHandlerContext ctx, ByteBuf s) {
		System.out.println("server----->client1");
		//NettyClient.channel = ctx.channel();
		//JOptionPane.showMessageDialog(null, "服务器推送给你\r\n" + new String("ccc"), "接收到的消息",JOptionPane.ERROR_MESSAGE); 
	}
	 
	private byte[] caches = new byte[0];

	@Override
	protected void messageReceived(ChannelHandlerContext ctx,
			Object o) throws Exception {
		// TODO Auto-generated method stub
		byte[] bs = (byte[])o;
		
		caches = ByteUtils.byteMerger(caches, bs);
		
		if (caches.length < 10) {
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
		
		byte[] content = ByteUtils.getContent(caches,length);
		byte[] newCaches = new byte[caches.length - length];
		
		ByteBuffer cachesBf = ByteBuffer.wrap(caches, length,caches.length - length);
		cachesBf.get(newCaches,0,caches.length - length);
		
		caches = newCaches;
		
		
		System.out.println("server----->client2" + new String(content));
		
//		BserviceProto.BserviceEntity entity = BserviceProto.BserviceEntity.parseFrom(content);
		JOptionPane.showMessageDialog(null, "服务器推送给你\r\n" + new String(content), "接收到的消息",JOptionPane.ERROR_MESSAGE); 
	}
	
	
}
