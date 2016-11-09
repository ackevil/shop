package com.huituopin.wechat.core.utils;

import java.util.Calendar;

import com.huituopin.wechat.entity.ReceiveXmlEntity;


public class GenerateXmlUtil {
	private ReceiveXmlEntity receiveXmlEntity =null ;
	private Long createTime = Calendar.getInstance().getTimeInMillis();
	public GenerateXmlUtil(ReceiveXmlEntity receiveXmlEntity ){
		this.receiveXmlEntity=receiveXmlEntity;	
	}
	public String generateTextXml(String content){
		StringBuffer str = new StringBuffer();
		str.append("<xml>");
		str.append("<ToUserName><![CDATA[" + receiveXmlEntity.getFromUserName() + "]]></ToUserName>");
		str.append("<FromUserName><![CDATA[" + receiveXmlEntity.getToUserName() + "]]></FromUserName>");
		str.append("<CreateTime>" + createTime + "</CreateTime>");
		str.append("<MsgType><![CDATA[" + "text"+ "]]></MsgType>");
		str.append("<Content><![CDATA[" + content+ "]]></Content>");
		str.append("</xml>");
		return str.toString();
	}
}
