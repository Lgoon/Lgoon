package com.lgoo.socket.handler.impl;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.lgoo.proto.IM.Message.IMMessage.LoginReq;
import com.lgoo.proto.IM.Message.IMMessage.LoginRes;
import com.lgoo.socket.handler.ImHandler;
import com.lgoo.util.ByteUtils;
import com.lgoo.util.Cmd;
import com.lgoo.util.HeadEntity;
import com.lgoo.util.SocketService;

//防止 cid 可能重复的时候  可以用到
@SocketService (sid = 0x0003)
public class ImHandlerImpl implements ImHandler {

	
	//登陆
	@Cmd(cid = 0x3019)
	@Override
	public void loginReq(OutputStream os) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入custId/token/resNo");
		String input = scanner.next();
		
		String[] args = input.split("/");
		String cstId = args[0];
		String token = args[1];
		String resNo = args[2];
		
		LoginReq req = LoginReq.newBuilder().
							setCstId(cstId).setLoginToken(token).
							setChannelType(1).
							setEntResNo(resNo).
							setStatus(1).
							setForceLogin(false).
							setDeviceType(1).
							setDeviceToken("11111").build();
		
		byte[] bs = req.toByteArray();
		
		HeadEntity head = new HeadEntity();
		head.setCmdId((short)0x3019);
		head.setTag(new byte[]{'h','s'});
		head.setPkLength(bs.length + 32);
		
		DataOutputStream output = new DataOutputStream(os);
		
		output.write(new byte[] { 'h', 's' });
		output.writeByte(1);
		output.writeShort(0x3019);
		output.writeInt(bs.length + 32);
		output.writeInt(111);
		output.writeLong(56565);
		output.writeInt(11);
		output.write(new byte[] { '0', '0', '0','0', '0', '0','0' });
		
		output.write(bs);
		//0603200000000000000/eb678b4eaa081c6b60dcd476bc97a8d528b7a3fae8cdcdb129a03673f063d094/06032000000
		//token=e464c1ac39704d94ee4f22a899fe61954c793c4be2e10fc32df3bf4c7e5f1e9a; 
		//custId=0603200000000000000; pointNo=06032000000; entCustId=0603200000020160416; custName=0000; custEntName=%u674E%u667A%u670D%u52A1%u516C%u53F8060416; entResNo=06032000000; countryCode=156; provinceCode=44; cityCode=440104; entResType=4; operName=%u7CFB%u7EDF%u64CD%u4F5C%u5458; lastLoginDate=2016-05-25%2016%3A52%3A07; lastLoginIp=null; cookieBusinessType=0; isLoadLoginDtail=true; reserveInfo=; tradPwd=true; entCustType=4; expireDate=2017-05-15%2000%3A00%3A00; entName=%u674E%u667A%u670D%u52A1%u516C%u53F8060416; userName=0000; createDate=2016-04-16; openDate=2016-04-16%2009%3A40%3A14
		//token=eb678b4eaa081c6b60dcd476bc97a8d528b7a3fae8cdcdb129a03673f063d094; custId=0603200000000000000; pointNo=06032000000; entCustId=0603200000020160416; custName=0000; custEntName=%u674E%u667A%u670D%u52A1%u516C%u53F8060416; entResNo=06032000000; countryCode=156; provinceCode=44; cityCode=440104; entResType=4; operName=%u7CFB%u7EDF%u64CD%u4F5C%u5458; lastLoginDate=2016-05-25%2016%3A55%3A54; lastLoginIp=null; cookieBusinessType=0; isLoadLoginDtail=true; reserveInfo=; tradPwd=true; entCustType=4; expireDate=2017-05-15%2000%3A00%3A00; entName=%u674E%u667A%u670D%u52A1%u516C%u53F8060416; userName=0000; createDate=2016-04-16; openDate=2016-04-16%2009%3A40%3A14
		
//		byte[] sends = ByteUtils.byteMerger(head.toBytes(), bs);
//		os.write(sends);
		
	}

	//登陆返回
	@Cmd(cid = 0x301a)
	@Override
	public Object loginRsp(byte[] args) throws Exception {
		LoginRes res = LoginRes.parseFrom(args);
		System.out.print(new String(args));
		return null;
	}

}
