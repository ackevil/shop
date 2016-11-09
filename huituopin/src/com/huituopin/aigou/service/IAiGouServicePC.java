package com.huituopin.aigou.service;

import java.util.List;

import com.huituopin.aigou.entity.Product;

public interface IAiGouServicePC {
	
	/**
	 * 获取爱购所有产品总页数
	 * 默认每页展示6件商品
	 * @return
	 */
	public int getProductPage(int pageSize);
	
	/**
	 * 获取指定页数，指定页的商品列表
	 * @param pageNo
	 * @param pageSize
	 * @param type
	 * @return
	 */
	public List<Object> getProductList(int pageNo,int pageSize,int type);
	
	/**
	 * 获取指定类型的商品总数
	 * @param type
	 * @return
	 */
	public int getProductNum(String type);

}
