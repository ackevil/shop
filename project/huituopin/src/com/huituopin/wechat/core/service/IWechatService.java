package com.huituopin.wechat.core.service;

public interface IWechatService {
	/**
	 * 获取微信授权code
	 * @return code
	 */
	public String getAccessToken(String code);
	public String getUserInfo(String openID,String accessToken);

}
