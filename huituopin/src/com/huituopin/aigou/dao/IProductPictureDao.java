package com.huituopin.aigou.dao;

import java.util.List;

import com.huituopin.aigou.entity.ProductPicture;

public interface IProductPictureDao {
	
	/**
	 * 插入一张产品图片
	 * @param pp
	 * @return
	 */
	public boolean insertPP(ProductPicture pp);
	
	/**
	 * 根据产品图片ID获取该ProductPicture
	 * @param ppId
	 * @return
	 */
	public ProductPicture getPPByPPId(int ppId);
	
	/**
	 * 根据该产品图片的ID删除该图片
	 * @param ppId
	 * @return
	 */
	public boolean deletePPByPPId(int ppId);
	
	/**
	 * 修改pp
	 * @param pp
	 * @return
	 */
	public boolean updatePP(ProductPicture pp);
	
	/**
	 * 获取某一个评价的所有图片
	 * @param podId
	 * @return
	 */
	public List<ProductPicture> getByPODId(int pId);
}
