package com.yappam.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
@SuppressWarnings("unchecked")
public class HQLTool {

	/**
	 * 在HQL中夹杂 ？参数与变量别名的 in函数参数，如 where unitid = ? and id in(:idList)
	 * 此时传入的参数需要使用特殊的in处理
	 * @param query
	 * @param params
	 * @return
	 */
	public static boolean setParams(final Query query,final Object[] params){
		String strHQL = query.getQueryString();
		String strHQLtemp = strHQL;
		Object obj=null;
		Type paramType = null;
		boolean ret = true;
		
		for (int i = 0; params!=null && i < params.length; i++) {
			obj = params[i];
			paramType = getHibernateType(obj);
			if( paramType == null ){
				strHQLtemp = strHQLtemp.substring(strHQLtemp.indexOf(":")+1);
				if( strHQLtemp.indexOf(")")<0)return false;
				String paramName =  strHQLtemp.substring(0,strHQLtemp.indexOf(")")).trim();
				
				ret = notNull(strHQL);// Object[] 或者 Collection 对应都因该有sql参数别名
				
				if ( obj instanceof Collection ){
					if( ((Collection)obj).size()>0){
						query.setParameterList(paramName, (Collection)obj, getHibernateType( ((Collection)obj).iterator().next()));
					}
					
				}else if(obj instanceof Object[]){
					if( ((Object[])obj).length>0){
						query.setParameterList(paramName, (Object[])obj, getHibernateType( ((Object[])obj)[0]));
					}
				}
					
			}else{
				query.setParameter(i, params[i], paramType);
			}
		}
		return ret;
	}
	static public Type getHibernateType(Object param){
		if (param instanceof String) {
			return Hibernate.STRING;
			
		} else if (param instanceof Long) {
			return new LongType();

		} else if (param instanceof Integer) {
			return Hibernate.INTEGER;

		} else if (param instanceof Double) {
			return Hibernate.DOUBLE;

		} else if (param instanceof BigDecimal) {
			return Hibernate.BIG_DECIMAL;

		} else if (param instanceof BigInteger) {
			return Hibernate.BIG_INTEGER;

		} else if (param instanceof Byte) {
			return Hibernate.BYTE;

		} else if (param instanceof Calendar) {
			return Hibernate.CALENDAR;
			
		} else if (param instanceof Date) {
			return Hibernate.DATE;
			
		} else if (param instanceof Boolean) {
			return Hibernate.BOOLEAN;
		}
		return null;
	}
	/**
	 * 判断非空 主要用于主键、字符串类型的非空判断 目前只支持Long、String、Integer、BigDecimal类型
	 * 空值：0/0.0/null/NULL 
	 * @param obj 代判断是否空的实例
	 * @return true/false
	 */
	public static boolean notNull(Object obj) {

		if (obj != null) {
			if (obj instanceof String) {
				// 如果 长度>0 并且 <>"null" 返回true，否则，false
				if (obj.toString().trim().length() > 0 && !obj.toString().trim().equalsIgnoreCase("null")) {
					return true;
				} else {
					return false;
				}

			} else if (obj instanceof Long) {
				Long new_name = (Long) obj;
				if (new_name.longValue() > 0l) {
					return true;
				}

			} else if (obj instanceof Integer) {
				Integer new_name = (Integer) obj;
				if (new_name.intValue() > 0) {
					return true;
				}

			}  else if (obj instanceof Double) {
				Double new_name = (Double) obj;
				if (new_name.doubleValue() > 0.0d) {
					return true;
				}

			} else if (obj instanceof BigDecimal) {
				BigDecimal new_name = (BigDecimal) obj;
				if (new_name.doubleValue() > 0.0d) {
					return true;
				}

			} else if (obj instanceof List) {
				return ((List) obj).size() > 0;

			} else if (obj instanceof Set) {
				return ((Set) obj).size() > 0;

			} else if (obj instanceof Object[]) {
				return Array.getLength(obj) > 0;

			}
		}
		return false;
	}
	public static String getStringValue(String str){
		if(str==null)
			return "";
		else
			return str;
	}
}
