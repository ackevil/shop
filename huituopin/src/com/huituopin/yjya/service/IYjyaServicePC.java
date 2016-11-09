package com.huituopin.yjya.service;

import java.util.List;

import com.huituopin.yjya.entity.Cloth;



public interface IYjyaServicePC {
	/**
	 * 获取Cloth的总数
	 * @param pageSize
	 * @return
	 */
	public int getClothPage(int pageSize);
	
	/**
	 * 获取指定页数指定类别的衣物信息
	 * @param pageNo
	 * @param ct1
	 * @param ct2
	 * @param ct3
	 * @return
	 */
	public List<Cloth> getClothList(int pageNo, int pageSize, int ct1, int ct2, int ct3);
	
	/**
	 * 获取所选类别衣物的总数量
	 * @param ct1
	 * @param ct2
	 * @param ct3
	 * @return
	 */
	public int getClothNum( String ct1, String ct2, String ct3);
}
