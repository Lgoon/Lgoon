package com.lgoo.socket.handler;

public interface DispatchHandler {
	public  Object dispatch(short cid,Object... args) throws Exception;
	public  Object dispatch(short sid,short cid,Object... args) throws Exception;
}
