package com.huituopin.aigou.dao;

import java.util.Date;
import java.util.List;

import com.huituopin.aigou.entity.Product;
import com.huituopin.common.utils.Page;

public interface IProductDao {

	public boolean addProduct(Product product);

	public List<Product> getProduct(Page page);

	public List<Product> getSellingProduct(Page page);

	public List<Product> getSelledProduct(Page page);

	public List<Product> getSellProduct(Page page);

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
	//public List<Object> getAllProduct(int type,String keyword,int start,int num);
	public List<Object> getAllProduct(int type,String keyword);
	public Product getProductById(int pId);
	
	//******************pc 端****************************
	/**
	 * 获取指定页数，指定类别的衣物列表
	 * @param pageNo
	 * @param pageSize
	 * @param type
	 * @return
	 */
	public List<Object> getProductByPC(int pageNo,int pageSize,int type);
	
}
