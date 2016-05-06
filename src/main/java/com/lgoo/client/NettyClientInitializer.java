package com.lgoo.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
 
	@Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast("decoder", new ProtobufDecoder(BserviceProto.BserviceEntity.getDefaultInstance()));
//        pipeline.addLast("encoder", new ProtobufEncoder());
//        pipeline.addLast("decoder2", new io.netty.handler.codec.http.HttpRequestDecoder());
//		pipeline.addLast("encoder2", new io.netty.handler.codec.http.HttpResponseEncoder());
        pipeline.addLast("decoder", new ByteArrayDecoder());
	    pipeline.addLast("encoder", new ByteArrayEncoder());
        pipeline.addLast("handler", new NettyClientHandler());
    }
}
