package com.huituopin.aigou.dao;

import java.util.List;

import com.huituopin.aigou.entity.ProductType;
import com.huituopin.common.utils.Page;

public interface IProductTypeDao {
	/**
	 * 插入一条产品类型
	 * @param pt
	 * @return
	 */
	public boolean insertPT(ProductType pt);
	
	/**
	 * 根据产品类型ID获取该ProductType
	 * @param ptId
	 * @return
	 */
	public ProductType getPTByPTId(short ptId);
	
	/**
	 * 根据该产品类型的ID删除该类型
	 * @param ptId
	 * @return
	 */
	public boolean deletePTByPTId(short ptId);
	
	/**
	 * 修改pt
	 * @param pt
	 * @return
	 */
	public boolean updatePT(ProductType pt);
	
	/**
	 * 获取所有的产品类别
	 * @param keyword
	 * @param page
	 * @return
	 */
	public List<ProductType> getAllProductType(String keyword,Page page);
	
	/**
	 * 获取现存所有的产品类别
	 * @return
	 */
	public List<ProductType> getAllProductType();

}
