package com.huituopin.aigou.service;

import java.util.Date;
import java.util.List;

import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductType;
import com.huituopin.common.utils.Page;

public interface IProductService {
	
	/**
	 * 添加产品
	 * @param product
	 * @return
	 */
	public boolean addProduct(Product product); 
	
	
	
	/**
	 * 获取所有的产品类别
	 * @param keyword
	 * @param page
	 * @return
	 */
	public List<ProductType> getProductTypeList(String keyword,Page page);
	
	/**
	 * 添加产品类别
	 * @param pt
	 * @return
	 */
	public boolean insertProductType(ProductType pt);
	
	
	/**
	 * 根据ID获得产品类别
	 * @param id
	 * @return
	 */
	public ProductType getProductTypeById(short id);
	
	/**
	 * 修改产品类别信息
	 * @param pt
	 * @return
	 */
	public boolean updateProductType(ProductType pt);
	
	/**
	 * 根据ID删除该产品配别
	 * @param id
	 * @return
	 */
	public boolean deleteProductTypeById(short id);
	
	/**
	 * 删除选中的产品类型
	 * @param productTypes
	 * @return
	 */
	public boolean deleteProductTypes(String productTypes);



	public List<Product> getProduct(Page page);



	public List<Product> getSellingProduct(Page page);



	public List<Product> getSelledProduct(Page page);



	public List<Product> getSellProduct(Page page);



	public List<Product> getProductByOptions(Page page, Date productLaunchTime,
			Date productStopTime, Integer type, String key);



	public List<Product> getSellingProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key);



	public List<Product> getSelledProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key);



	public List<Product> getSellProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key);



	public Product getProductById(Integer id);



	public void delProduct(Product product);



	public boolean updateProduct(Product product);



	public boolean deleteProducts(String productIds);



	public boolean changeProducts(String productIds);
}
