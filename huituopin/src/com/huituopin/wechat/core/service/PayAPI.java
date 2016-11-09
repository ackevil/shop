package com.huituopin.wechat.core.service;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.huituopin.wechat.core.utils.MapUtil;
import com.huituopin.wechat.core.utils.SignatureUtil;
import com.huituopin.wechat.entity.Unifiedorder;
import com.huituopin.wechat.entity.UnifiedorderResult;
import com.huituopin.wechat.entity.WxPayReturnData;
import com.huituopin.wechat.http.HttpEngine;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class PayAPI {

	/**
	 * 统一下单
	 * @param unifiedorder unifiedorder
	 * @param key key
	 * @return UnifiedorderResult
	 * @throws Exception 
	 */
	public static UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder,String key) throws Exception{
		//1.1 统一订单转换为map
		Map<String,String> map = MapUtil.objectToMap(unifiedorder);
		if(key != null){
			//1.2 返回 签名
			String sign = SignatureUtil.generateSign(map,key);
			System.out.println("3"+sign);
			unifiedorder.setSign(sign);
		}
		//1.3  统一订单 xml格式 发送微信后台
		XStream xs = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
	    xs.alias("xml", Unifiedorder.class);
	    String unifiedorderXML = xs.toXML(unifiedorder);
	    /*
		String unifiedorderXML = XMLConverUtil.convertToXML(unifiedorder);
		System.out.println("4"+unifiedorderXML);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setUri("https://api.mch.weixin.qq.com/pay/unifiedorder")
										.setEntity(new StringEntity(unifiedorderXML,Charset.forName("utf-8")))
										.build();
		*/
	    System.out.println("4"+unifiedorderXML);
		HttpEngine httpEngine=HttpEngine.getInstance();
		//1.4 返回 统一订单结果 xml
		String resulrXML =httpEngine.sendPostRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", unifiedorderXML);
		System.out.println("5"+resulrXML);
		//UnifiedorderResult reData = new UnifiedorderResult();
		//1.5 将统一订单结果 xml 转化为 对象
        XStream xs1 = new XStream(new DomDriver());
        xs1.alias("xml", UnifiedorderResult.class);
        UnifiedorderResult reData = (UnifiedorderResult) xs1.fromXML(resulrXML);
        return reData;
		//return XMLConverUtil.convertToObject(UnifiedorderResult.class,resulrXML);
	}
}
