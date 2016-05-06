package com.lgoo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.lgoo.util.SpringUtil;


public class NettyServer implements Runnable {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }
    
    static Thread nettyServer = null;
    
    
    public void initServer() {
    	System.out.println("nettyServer init~~~~");
    	if (nettyServer == null) { 
    		nettyServer = new Thread(this);
    		nettyServer.start();
    	}
    }

    public void stopServer() {
    	System.out.println("nettyServer stop~~~~~");
    }
    
    public void run() {
        
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // (3)
             .childHandler(new NettyServerInitializer())  //(4)
             .option(ChannelOption.SO_BACKLOG, 128)          // (5)
             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            
    		System.out.println("nettyServer 启动" + this.port);
    		
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync(); // (7)
            // 等待服务 socket 关闭 
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
    		System.out.println("NettyServer 关闭");
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8111;
        }
        
        System.out.println("start");
        SpringUtil.getContext();
       
//      new NettyServer(port).run();

    }
}