package com.huituopin.yjya.dao;

import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.yjya.entity.ClothPicture;

public interface IClothPictureDao extends BaseDao<ClothPicture, String>{
	
	/**
	 * 插入一条ClothPicture记录
	 * @return
	 */
	public boolean insertClothPicture(ClothPicture clothPicture);
	/**
	 * 根据clothId获取这件衣服的详情图片
	 * @param clothId
	 * @return
	 */
	public List<ClothPicture> getDetailPicsByClothId(int clothId);
	
	/**
	 * 根据clothId获取这件衣服的评论图片
	 * @param clothId
	 * @return
	 */
	public List<ClothPicture> getCommentPicsByClothId(int clothId);
}
