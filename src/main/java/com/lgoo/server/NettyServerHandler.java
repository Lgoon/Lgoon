package com.lgoo.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lgoo.proto.BserviceProto;
import com.lgoo.proto.IMLoginAuthenticationProto;
import com.lgoo.proto.IMLoginAuthenticationProto.CustType;
import com.lgoo.proto.IMLoginAuthenticationProto.MessageLoginAuthticationReq;
import com.lgoo.service.DispatchService;
import com.lgoo.service.impl.DispatchServiceImpl;
import com.lgoo.util.ByteUtils;
import com.lgoo.util.HeadEntity;

/**
 * 服务 channel
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> { // (1)

	
	Logger Log = Logger.getLogger(NettyServerHandler.class);
	/**
	 * A thread-safe Set Using ChannelGroup, you can categorize Channels into a
	 * meaningful group. A closed Channel is automatically removed from the
	 * collection,
	 */
	public static ChannelGroup channels = new DefaultChannelGroup(
			GlobalEventExecutor.INSTANCE);

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		Channel incoming = ctx.channel();

		// Broadcast a message to multiple Channels
		String msg = "[SERVER] - " + incoming.remoteAddress() + " 登陆成功\n";
		System.out.println(msg);
		BserviceProto.BserviceEntity.Builder builder = BserviceProto.BserviceEntity
				.newBuilder();
		builder.setImSystem(msg);

		byte[] s = builder.build().toByteArray();
		byte[] scount = new byte[25];
		s = ByteUtils.byteMerger(scount, s);

		//incoming.writeAndFlush(s);
		channels.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		Channel incoming = ctx.channel();
		// Broadcast a message to multiple Channels
		System.out.println("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
		// A closed Channel is automatically removed from ChannelGroup,
		// so there is no need to do "channels.remove(ctx.channel());"
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress()
				+ "在线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress()
				+ "掉线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress()
				+ "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}

	@Autowired
	DispatchService IDisplaceService;

	private byte[] caches = new byte[0];
	
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object o)
			{
		// TODO Auto-generated method stub
		System.out.println("server~~ in");
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
		
		DispatchService dispatch = new DispatchServiceImpl();
		byte[] rep = null;
		try {
			Log.error("dispatch:" + dispatch);
			Log.error("CmdId:" + head.getCmdId());
			Log.error("content:" + content);
			rep = (byte[]) dispatch.dispatch(head.getCmdId(), content);
		} catch (Exception e) {
			e.printStackTrace();
			
			byte[] cb = ("error:"+ e.getMessage()).getBytes();
			head.setTag((byte)'h', (byte)'s');
			head.setCmdId((short)0);
			head.setPkLength(25 + cb.length);
			rep = ByteUtils.byteMerger(head.toBytes(), cb);
		}
		
//		if (headEntity.getCmdId() == ((short) 0x3001)) {
			
		Channel incoming = ctx.channel();
		incoming.writeAndFlush(rep);

	}

	public static void main(String[] args) {

//		AsOperatorLoginInfo loginInfo = new AsOperatorLoginInfo();
//		loginInfo.setResNo("06010010000");
//		loginInfo.setUserName("0000");
//		loginInfo.setLoginPwd("666666");
//		loginInfo.setChannelType(ChannelTypeEnum.SYS);
//		loginInfo.setRandomToken("asdf");
//		loginInfo.setCustType("4");
//
//		IUCLoginService loginService = (IUCLoginService) SpringUtil
//				.getBean("IUCLoginService");
//
//		AsOperatorLoginResult result = loginService.operatorLogin(loginInfo);

		// AsOperatorLoginInfo loginInfo = new AsOperatorLoginInfo();
		// loginInfo.setResNo("123");
		// loginInfo.setUserName("ccc");
		// loginInfo.setLoginPwd("22");
		// loginInfo.setChannelType(ChannelTypeEnum.SYS);
		// loginInfo.setRandomToken("asdf");
		// loginInfo.setCustType("4");
		// String result =JsonUtil.createJsonString(loginInfo);
		// System.out.println(result);
	}

}