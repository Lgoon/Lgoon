package com.lgoo.service.impl;

import com.lgoo.proto.IMLoginAuthenticationProto;
import com.lgoo.service.TestService;
import com.lgoo.util.ByteUtils;
import com.lgoo.util.Cmd;
import com.lgoo.util.HeadEntity;
import com.lgoo.util.NettyService;

@NettyService(sid = 1)
public class TestServiceImpl implements TestService {
	
	@Cmd(cid = 11)
	public byte[] cmd11(byte[] args) {
		System.out.println("cmd11");
		
		byte[] cb = "execute: cmd11".getBytes();
		HeadEntity head = new HeadEntity();
		head.setTag((byte)'h', (byte)'s');
		head.setCmdId((short)11);
		head.setPkLength(25 + cb.length);
		byte[] rep = ByteUtils.byteMerger(head.toBytes(), cb);
		return rep;
	}
	
	@Cmd(cid = 12)
	public void cmd2(byte[] args){
		System.out.println("cmd2");
	}
	
	public static void main(String[] args) {
	}
}
