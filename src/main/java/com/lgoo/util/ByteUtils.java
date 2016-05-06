package com.lgoo.util;

import java.nio.ByteBuffer;

public class ByteUtils {
	
	public static byte[] getHeader(byte[] bs) {
		ByteBuffer byteBuf = ByteBuffer.wrap(bs);
		byte[] head = new byte[25];
		byteBuf.get(head, 0, 25);
		return head;
	}

	public static byte[] getContent(byte[] bs) {
		ByteBuffer byteBuf = ByteBuffer.wrap((byte[]) bs, 25, bs.length - 25);
		byte[] content = new byte[bs.length - 25];
		byteBuf.get(content, 0, bs.length - 25);
		return content;
	}
	
	public static byte[] getContent(byte[] bs,int length) {
		ByteBuffer byteBuf = ByteBuffer.wrap((byte[]) bs, 25, length - 25);
		byte[] content = new byte[length - 25];
		byteBuf.get(content, 0, length - 25);
		return content;
	}
	
			
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
        return byte_3;  
    } 
	
	public final static byte[] getBytes(short s, boolean asc) {
		byte[] buf = new byte[2];
		if (asc)
			for (int i = buf.length - 1; i >= 0; i--) {
				buf[i] = (byte) (s & 0x00ff);
				s >>= 8;
			}
		else
			for (int i = 0; i < buf.length; i++) {
				buf[i] = (byte) (s & 0x00ff);
				s >>= 8;
			}
		return buf;
	}

	public final static byte[] getBytes(int s, boolean asc) {
		byte[] buf = new byte[4];
		if (asc)
			for (int i = buf.length - 1; i >= 0; i--) {
				buf[i] = (byte) (s & 0x000000ff);
				s >>= 8;
			}
		else
			for (int i = 0; i < buf.length; i++) {
				buf[i] = (byte) (s & 0x000000ff);
				s >>= 8;
			}
		return buf;
	}

	public final static byte[] getBytes(long s, boolean asc) {
		byte[] buf = new byte[8];
		if (asc)
			for (int i = buf.length - 1; i >= 0; i--) {
				buf[i] = (byte) (s & 0x00000000000000ff);
				s >>= 8;
			}
		else
			for (int i = 0; i < buf.length; i++) {
				buf[i] = (byte) (s & 0x00000000000000ff);
				s >>= 8;
			}
		return buf;
	}

	public final static short getShort(byte[] buf, boolean asc) {
		if (buf == null) {
			throw new IllegalArgumentException("byte array is null!");
		}
		if (buf.length > 2) {
			throw new IllegalArgumentException("byte array size > 2 !");
		}
		short r = 0;
		if (asc)
			for (int i = buf.length - 1; i >= 0; i--) {
				r <<= 8;
				r |= (buf[i] & 0x00ff);
			}
		else
			for (int i = 0; i < buf.length; i++) {
				r <<= 8;
				r |= (buf[i] & 0x00ff);
			}
		return r;
	}

	public final static int getInt(byte[] buf, boolean asc) {
		if (buf == null) {
			throw new IllegalArgumentException("byte array is null!");
		}
		if (buf.length > 4) {
			throw new IllegalArgumentException("byte array size > 4 !");
		}
		int r = 0;
		if (asc)
			for (int i = buf.length - 1; i >= 0; i--) {
				r <<= 8;
				r |= (buf[i] & 0x000000ff);
			}
		else
			for (int i = 0; i < buf.length; i++) {
				r <<= 8;
				r |= (buf[i] & 0x000000ff);
			}
		return r;
	}

	public final static long getLong(byte[] buf, boolean asc) {
		if (buf == null) {
			throw new IllegalArgumentException("byte array is null!");
		}
		if (buf.length > 8) {
			throw new IllegalArgumentException("byte array size > 8 !");
		}
		long r = 0;
		if (asc)
			for (int i = buf.length - 1; i >= 0; i--) {
				r <<= 8;
				r |= (buf[i] & 0x00000000000000ff);
			}
		else
			for (int i = 0; i < buf.length; i++) {
				r <<= 8;
				r |= (buf[i] & 0x00000000000000ff);
			}
		return r;
	}
	
	public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }


    public static char byteToChar(byte[] b) {
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }
}
