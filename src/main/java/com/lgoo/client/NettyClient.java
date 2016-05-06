package com.lgoo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgoo.proto.IMLoginAuthenticationProto;
import com.lgoo.proto.IMLoginAuthenticationProto.CustType;
import com.lgoo.util.ByteUtils;
import com.lgoo.util.HeadEntity;


public class NettyClient implements Runnable {
	
	private static ThreadPoolExecutor threadPool = null;
	
	public static ThreadPoolExecutor getThreadPool() {
		threadPool = new ThreadPoolExecutor(1, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
		return threadPool;
	}
	
	
    public static void main(String[] args) throws Exception{
//        new SimpleClient("localhost", 8080).run();
    }

    private final String host;
    private final int port;
    private Channel channel;

    public NettyClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientInitializer());
            channel = bootstrap.connect(host, port).sync().channel();

            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    private static final Logger log = LoggerFactory
			.getLogger(NettyClient.class);
    
    
    public void sendMsg(String msg) throws Exception{
    	
    	IMLoginAuthenticationProto.MessageLoginAuthticationReq.Builder req = IMLoginAuthenticationProto.MessageLoginAuthticationReq.newBuilder();
    	req.setResNo("06032000000");
    	req.setUserName(msg);
    	req.setLoginIp("127.0.0.1");
    	req.setCustType(CustType.CUST_TYPE_4);
    	req.setRandomToken("123");
    	req.setLoginPwd("666666");
    	req.setPwdVer("3");
    	
    	byte[] rb = req.build().toByteArray();
    	
    	HeadEntity head = new HeadEntity();
    	head.setTag((byte)'h', (byte)'s');
    	head.setCmdId((short)21);
    	head.setPkLength(25 + rb.length);
    	
    	byte[] s = ByteUtils.byteMerger(head.toBytes(),rb);
    	
    	System.out.println(req.getLoginPwd());
    	
    	JOptionPane.showMessageDialog(null, "开始发送消息::" + msg, "开始发送消息" ,JOptionPane.ERROR_MESSAGE);
    	if (channel != null) {
	    	channel.writeAndFlush(s);
    	}

    }
    
    
  
    
}
