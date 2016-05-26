package com.lgoo.socket.handler;

import java.io.OutputStream;

public interface ImHandler {
	public Object loginRsp(byte[] args) throws Exception;
	
	public void loginReq(OutputStream os) throws Exception;
}
