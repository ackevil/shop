package com.huituopin.wechat.core.utils;


import java.util.Map;
import java.util.UUID;

import com.huituopin.wechat.entity.PayJsRequest;



public class PayUtil {




	/**
	 * (MCH)生成支付JS请求对象
	 * @param prepay_id	预支付订单号
	 * @param appId appId
	 * @param key 商户支付密钥
	 * @return json
	 */
	public static String generateMchPayJsRequestJson(String prepay_id,String appId,String key){
		// 2. 支付请求对象  PayJsRequest
		String package_ = "prepay_id="+prepay_id;
		PayJsRequest payJsRequest = new PayJsRequest();
		payJsRequest.setAppId(appId);
		payJsRequest.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
		payJsRequest.setPackage_(package_);
		payJsRequest.setSignType("MD5");
		payJsRequest.setTimeStamp(System.currentTimeMillis()/1000+"");
		Map<String, String> mapS = MapUtil.objectToMap(payJsRequest);
		
		String paySign = SignatureUtil.generateSign(mapS,key);
		System.out.println("paySign"+paySign);
		payJsRequest.setPaySign(paySign);
		return JsonUtil.toJSONString(payJsRequest);
	}


}