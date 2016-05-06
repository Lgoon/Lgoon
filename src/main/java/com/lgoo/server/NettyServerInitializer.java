package com.lgoo.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

/**
 * 服务 ChannelInitializer
 */
public class NettyServerInitializer extends
		ChannelInitializer<SocketChannel> {

	@Override
    public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast("decoder", new ByteArrayDecoder());
	    pipeline.addLast("encoder", new ByteArrayEncoder());
        pipeline.addLast("handler", new NettyServerHandler());
        pipeline.addLast("heartbeatHandler", new HeartbeatHandler());
		System.out.println("Client:"+ch.remoteAddress() +"连接");
    }
}
