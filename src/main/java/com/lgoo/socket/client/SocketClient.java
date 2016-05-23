package com.lgoo.socket.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.lgoo.proto.IMLoginAuthenticationProto;
import com.lgoo.proto.IMLoginAuthenticationProto.CustType;
import com.lgoo.util.ByteUtils;
import com.lgoo.util.HeadEntity;

public class SocketClient {
	public static void main(String[] args) throws Exception {
		Socket so = new Socket("127.0.0.1",8111);
		OutputStream os = so.getOutputStream();
		InputStream is = so.getInputStream();

		IMLoginAuthenticationProto.MessageLoginAuthticationReq.Builder req = IMLoginAuthenticationProto.MessageLoginAuthticationReq.newBuilder();
    	req.setResNo("06032000000");
    	req.setUserName("root");
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
    	
		os.write(s);
		
		byte[] b = new byte[1024];
		int i=0;
		while(true){
			if(is.read()> 0){
				is.read(b);
				for (int j = 0; j < b.length; j++) {
					System.out.print(b[j]);
				}
			}
		}
	}
}
