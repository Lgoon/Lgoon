package com.lgoo.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class HeadEntity {

	public static final int HEAD_LEN = 32;
	
	private byte[] bs = new byte[HEAD_LEN];

	public HeadEntity(){
		
	}
	
	public HeadEntity(byte[] bs) {
		this.bs = bs;
	}

	public String getTag() {
		return (char)bs[0] + "" +  (char)bs[1];
	}
	
	public void setTag(byte a,byte b) {
		bs[0] = a;
		bs[1] = b;
	}
	
	public void setTag(byte[] bs) {
		ByteBuffer.wrap(this.bs, 0, 2).put(bs);
	}
	
	public byte getVersion() {
		return bs[2];
	}
	
	public void setVersion(byte version) {
		bs[2] = version;
	}

	public short getCmdId() {
		return ByteBuffer.wrap(bs, 3, 2).asShortBuffer().get();
	}
	
	public void setCmdId(short cmdId) {
		ByteBuffer.wrap(bs, 3, 2).order(ByteOrder.BIG_ENDIAN).asShortBuffer().put(cmdId);
	}
	
	public void setCmdId(byte[] bs) {
		ByteBuffer.wrap(this.bs, 3, 2).put(bs);
	}

	public int getPkLength() {
		return ByteBuffer.wrap(bs, 5, 4).asIntBuffer().get();
	}
	
	public void setPkLength(int pkLength) {
		ByteBuffer.wrap(bs, 5, 4).order(ByteOrder.BIG_ENDIAN).asIntBuffer().put(pkLength);
	}

	public void setPkLength(byte[] bs) {
		ByteBuffer.wrap(this.bs, 5, 4).put(bs);
	}
	
	public int getSessionId() {
		ByteBuffer byteBuff = ByteBuffer.wrap(bs, 9, 4);
		byte[] b = new byte[4];
		byteBuff.get(b);
		return ByteUtils.getInt(b, true);
	}
	
	public void setSessionId(int sessionId) {
		ByteBuffer.wrap(bs, 9, 4).order(ByteOrder.BIG_ENDIAN).asIntBuffer().put(sessionId);
	}
	
	public void setSessionId(byte[] bs) {
		ByteBuffer.wrap(this.bs, 9, 4).put(bs);
	}

	public long getDestId() {
		ByteBuffer byteBuff = ByteBuffer.wrap(bs, 13, 8);
		byte[] b = new byte[8];
		byteBuff.get(b);
		return ByteUtils.getLong(b, false);
	}
	
	public void setDestId(long destId){
		ByteBuffer.wrap(bs, 13, 8).asLongBuffer().put(destId);
	}
	
	public void setDestId(byte[] bs) {
		ByteBuffer.wrap(this.bs, 13, 8).put(bs);
	}

	public int getReversed() {
		ByteBuffer byteBuff = ByteBuffer.wrap(bs, 21, 4);
		byte[] b = new byte[4];
		byteBuff.get(b);
		return ByteUtils.getInt(b, true);
	}
	
	public void setReversed(int reversed){
		byte[] rs = ByteUtils.getBytes(reversed, false);
		ByteBuffer.wrap(bs, 21, 4).put(rs);
	}
	
	public void setReversed(byte[] rs) {
		ByteBuffer.wrap(bs, 21, 4).put(rs);
	}
	
	public byte[] toBytes() {
		return bs;
	}
    
    public static void main(String[] args) throws Exception {
    	HeadEntity entity = new HeadEntity();
    	byte[] bs = entity.toBytes();
    	entity.setTag(new byte[]{'a','h'});
    	entity.setVersion((byte) 8);
    	entity.setCmdId((short) 44);
    	entity.setDestId(9999999999l);
    	entity.setPkLength(25);
    	entity.setReversed(4);
    	entity.setSessionId(2);
    	
    	System.out.println(entity.getTag());
    	System.out.println(entity.getVersion());
    	System.out.println(entity.getCmdId());
    	System.out.println(entity.getDestId());
    	System.out.println(entity.getPkLength());
    	System.out.println(entity.getReversed());
    	System.out.println(entity.getSessionId());
    	
//    	bs = new byte[4];
    	//ByteBuffer.wrap(bs).order(ByteOrder.BIG_ENDIAN).asIntBuffer().put(1);
    	for (int i = 0; i < bs.length; i++) {
			System.out.print(bs[i] + "-");
		}
    	
    	ByteBuffer.wrap(bs).order(ByteOrder.BIG_ENDIAN).asIntBuffer().put(1);
    	System.out.println();
    	for (int i = 0; i < bs.length; i++) {
			System.out.print(bs[i] + "-");
		}
	}

}
