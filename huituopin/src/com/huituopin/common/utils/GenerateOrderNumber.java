package com.huituopin.common.utils;

import java.util.Date;

public class GenerateOrderNumber {
	@SuppressWarnings("deprecation")
	public static String Generate(int pId,int userId){
		StringBuilder orderNumber = new StringBuilder();
		Date date = new Date();
		orderNumber.append(date.getYear());
		orderNumber.append(date.getMonth());
		orderNumber.append(date.getDay());
		orderNumber.append(date.getHours());
		orderNumber.append(date.getMinutes());
		orderNumber.append(date.getSeconds());
		if(pId!=0){
			orderNumber.append(pId);
		}
		orderNumber.append(userId);
		return orderNumber.toString();
	}

}
