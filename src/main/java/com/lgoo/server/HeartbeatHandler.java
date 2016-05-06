package com.lgoo.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartbeatHandler extends ChannelHandlerAdapter {
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.READER_IDLE) {
				ctx.close();
				System.out.println("READER_IDLE 读超时");
			} else if (e.state() == IdleState.WRITER_IDLE) {
				ByteBuf buff = ctx.alloc().buffer();
				buff.writeBytes("dida".getBytes());
				ctx.writeAndFlush(buff);
				System.out.println("WRITER_IDLE 写超时");
			}
		}
	}
}
