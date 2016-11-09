package com.huituopin.wechat.core.service;

import javax.servlet.http.HttpServletRequest;

import com.huituopin.wechat.core.utils.GenerateXmlUtil;
import com.huituopin.wechat.core.utils.ParseReceiveXml;
import com.huituopin.wechat.entity.ReceiveXmlEntity;
public class MainService {
	
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_TYPE_CLICK = "CLICK";
    public static final String EVENT_TYPE_VIEW = "VIEW";
	
	
		public String coreService(HttpServletRequest request){
			//获取消息实体
			ReceiveXmlEntity receiveEntity= ParseReceiveXml.getMsgEntity(request);
			String returnXmlString="";
			GenerateXmlUtil generateXmlUtil =null ;
			System.out.println(receiveEntity);
			//根据消息类型做出不同响应
			if(receiveEntity!=null){
				String type=receiveEntity.getMsgType();
				generateXmlUtil=new GenerateXmlUtil(receiveEntity);
				
				if(type.equals(REQ_MESSAGE_TYPE_TEXT)){ 						//文本
					returnXmlString=generateXmlUtil.generateTextXml(receiveEntity.getContent());
				}else if(type.equals(REQ_MESSAGE_TYPE_IMAGE)){			//图片
					returnXmlString=generateXmlUtil.generateTextXml("你发送的是一个图片");
				}else if(type.equals(REQ_MESSAGE_TYPE_LINK)){				//连接
					returnXmlString=generateXmlUtil.generateTextXml("你发送了一个连接");
				}else if(type.equals(REQ_MESSAGE_TYPE_LOCATION)){		//位置
					returnXmlString=generateXmlUtil.generateTextXml("你发送的是一个位置");
				}else if(type.equals(REQ_MESSAGE_TYPE_VOICE)){				//声音
					returnXmlString=generateXmlUtil.generateTextXml("你发送了一个声音");
				}else if(type.equals(REQ_MESSAGE_TYPE_EVENT)){			//事件
					String event=receiveEntity.getEvent();
					if(event.equals(EVENT_TYPE_SUBSCRIBE)){						//订阅事件
						returnXmlString=	generateXmlUtil.generateTextXml("欢迎你订阅本公共号");
					}else if(event.equals(EVENT_TYPE_UNSUBSCRIBE)){		//取消订阅
						
					}else if(event.equals(EVENT_TYPE_VIEW)){						// 跳转
					}else if(event.equals(EVENT_TYPE_CLICK)){ 						//点击
						
					}
				}
			}
			return returnXmlString;	
		}
}
