package com.huituopin.wechat.core.utils;


import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;


public class SignatureUtil {

	/**
	 * 生成 package 字符串
	 * @param map map
	 * @param paternerKey paternerKey
	 * @return package_str
	 */
	public static String generatePackage(Map<String, String> map,String paternerKey){
		String sign = generateSign(map,paternerKey);
		Map<String,String> tmap = MapUtil.order(map);
		String s2 = MapUtil.mapJoin(tmap,false,true);
		return s2+"&sign="+sign;
	}

	/**
	 * 生成sign MD5 加密 toUpperCase
	 * @param map map
	 * @param paternerKey paternerKey
	 * @return sign
	 */
	public static String generateSign(Map<String, String> map,String paternerKey){
		Map<String, String> tmap = MapUtil.order(map);
		if(tmap.containsKey("sign")){
			tmap.remove("sign");
		}
		
		String str = MapUtil.mapJoin(tmap, false, false);
		System.out.println(str);
		return DigestUtils.md5Hex(str+"&key="+paternerKey).toUpperCase();
	}

	
}
