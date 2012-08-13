package com.yappam.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.UUIDHexGenerator;

public class IDGenerator extends UUIDHexGenerator {
	public static long longFlag = 0;
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		return getId();
	}
	
	public static String getId(){
		StringBuffer buffer = new StringBuffer();
		for(int i=1; i<10; i++)buffer.append("0");
		String strTime = ""+System.currentTimeMillis();//+longFlag+(int)(Math.random()*1000+1000);
		if( longFlag<10){
			strTime = strTime + "000"+longFlag;
		}else if( longFlag<100){
			strTime = strTime + "00"+longFlag;
		}else if( longFlag<1000){
			strTime = strTime + "0"+longFlag;
		}else if( longFlag<10000){
			strTime = strTime + longFlag;
		}
		strTime = strTime + (int)(Math.random()*1000+1000);
		if( strTime.length()>9){
			strTime=strTime.substring(strTime.length()-9);
		}
		buffer.replace(buffer.length()-strTime.length(), buffer.length(), strTime);
		longFlag++;
		if( longFlag>9999){
			longFlag=0;
		}
		return buffer.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(IDGenerator.getId());
//		System.out.println(IDGenerator.getId());
//		System.out.println(IDGenerator.getId());
//		System.out.println(IDGenerator.getId());
//	}
}
