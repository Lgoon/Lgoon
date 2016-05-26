package com.lgoo.service.impl;

import com.gy.hsxt.common.utils.JsonUtil;
import com.gy.hsxt.uc.as.api.common.IUCAsTokenService;
import com.gy.hsxt.uc.as.api.common.IUCLoginService;
import com.gy.hsxt.uc.as.bean.common.AsOperatorLoginInfo;
import com.gy.hsxt.uc.as.bean.enumerate.ChannelTypeEnum;
import com.gy.hsxt.uc.as.bean.result.AsOperatorLoginResult;
import com.lgoo.proto.IMLoginAuthenticationProto;
import com.lgoo.proto.IMLoginAuthenticationProto.CustType;
import com.lgoo.service.LoginService;
import com.lgoo.util.ByteUtils;
import com.lgoo.util.Cmd;
import com.lgoo.util.HeadEntity;
import com.lgoo.util.SocketService;
import com.lgoo.util.SpringUtil;
import com.lgoo.util.StringEncrypt;

@SocketService (sid = 2)
public class LoginServiceImpl implements LoginService {
	
	@Cmd(cid = (short)21)
	@Override
	public byte[] login(byte[] orgs) throws Exception {
		
		IMLoginAuthenticationProto.MessageLoginAuthticationReq req = IMLoginAuthenticationProto.MessageLoginAuthticationReq.parseFrom(orgs);
		
		String resNo = req.getResNo();
		String userName = req.getUserName();
		String loginPwd = req.getLoginPwd();
		String pwdVer = req.getPwdVer();
		String randomToken = req.getRandomToken();
		String loginIp =req.getLoginIp();
		CustType custType = req.getCustType();
		
		HeadEntity head = new HeadEntity();
		
		IMLoginAuthenticationProto.MessageLoginAuthticationRsp.Builder builder = IMLoginAuthenticationProto.MessageLoginAuthticationRsp
				.newBuilder();

		AsOperatorLoginInfo loginInfo = new AsOperatorLoginInfo();
		loginInfo.setResNo(resNo);
		loginInfo.setUserName(userName);
		loginInfo.setLoginPwd(loginPwd);
		loginInfo.setPwdVer(pwdVer);
		loginInfo.setRandomToken(resNo+userName);
		loginInfo.setLoginIp(loginIp);
		loginInfo.setCustType(custType.getNumber() + "");
		loginInfo.setChannelType(ChannelTypeEnum.MOBILE);
		loginInfo.setRandomToken(randomToken);
		
		String plantPwd = "666666";
		IUCAsTokenService iucAsTokenService = (IUCAsTokenService) SpringUtil.getBean("iUCAsTokenService");
		
		String token = iucAsTokenService.getRandomToken(req.getResNo() + userName);
		loginInfo.setRandomToken(token);
		plantPwd = StringEncrypt.encrypt(loginPwd, token);
		loginInfo.setLoginPwd(plantPwd);
		
		
		System.out.println("resNo==>" + loginInfo.getResNo() + "\nuserName==>" + loginInfo.getUserName() + "\nloginPwd==>" + loginInfo.getLoginPwd());
		System.out.println("pwdVer==>" + loginInfo.getPwdVer() + "\nrandomToken==>" + loginInfo.getRandomToken());
		System.out.println("loginIp==>" + loginInfo.getLoginIp() + "\ncustType==>" + loginInfo.getCustType());
		
		try {
			
			IUCLoginService IUCLoginService = (IUCLoginService) SpringUtil.getBean("iUCLoginService");
			
			AsOperatorLoginResult result = IUCLoginService.operatorLogin(loginInfo);
			
			
			String resultStr = 
					"{" +
					"\"resultcode\":0," +
						"\"error\":\"\"," +
						"\"entity\": " +
						JsonUtil.createJsonString(result) +
					"}";
			
			builder.setResultJson(resultStr);
			
		} catch (Exception e) {
			e.printStackTrace();
			String resultStr = 
					"{" +
					"\"resultcode\":400," +
						"\"error\":\"" + e.getMessage()  + "\"," +
						"\"entity\": {}" +
						
					"}";
			builder.setResultJson(resultStr);
		}
		System.out.println(builder.getResultJson());
		
		byte[] cb = builder.build().toByteArray();
		head.setTag((byte)'h', (byte)'s');
		head.setCmdId((short)IMLoginAuthenticationProto.LoginCmdID.CID_Login_Authentication_Rsp_VALUE);
		head.setPkLength(25 + cb.length);
		byte[] rep = ByteUtils.byteMerger(head.toBytes(), cb);
		return rep;
		
	}

}
