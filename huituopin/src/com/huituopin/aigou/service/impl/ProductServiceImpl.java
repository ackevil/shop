package com.huituopin.aigou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.aigou.dao.IProductDao;
import com.huituopin.aigou.dao.IProductTypeDao;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductType;
import com.huituopin.aigou.service.IProductService;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.entity.ClothType;

@Transactional(readOnly=false)
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	@Autowired
	private IProductTypeDao productTypeDao;
	
	@Override
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public List<ProductType> getProductTypeList(String keyword, Page page) {
		return productTypeDao.getAllProductType(keyword,page);
	}

	@Override
	public boolean insertProductType(ProductType pt) {
		return productTypeDao.insertPT(pt);
	}

	@Override
	public ProductType getProductTypeById(short id) {
		return productTypeDao.getPTByPTId(id);
	}

	@Override
	public boolean updateProductType(ProductType pt) {
		return productTypeDao.updatePT(pt);
	}

	@Override
	public boolean deleteProductTypeById(short id) {
		return productTypeDao.deletePTByPTId(id);
	}

	@Override
	public boolean deleteProductTypes(String productTypes) {
		boolean result=true;
		String[] productTypeList = productTypes.split(";");
		int i=0;
		if(productTypeList[0]=="全选" || productTypeList[0]=="取消"){
			i=1;
		}
		for(;i<productTypeList.length;i++){
			ProductType pt = productTypeDao.getPTByPTId(Short.valueOf(productTypeList[i]));
			pt.setProductTypeIsDelete(true);
			if(!productTypeDao.updatePT(pt)){
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public List<Product> getProduct(Page page) {
		return productDao.getProduct(page);
	}

	@Override
	public List<Product> getSellingProduct(Page page) {
		return productDao.getSellingProduct(page);
	}

	@Override
	public List<Product> getSelledProduct(Page page) {
		// TODO Auto-generated method stub
		return productDao.getSelledProduct(page);
	}

	@Override
	public List<Product> getSellProduct(Page page) {
		// TODO Auto-generated method stub
		return productDao.getSellProduct(page);
	}

	@Override
	public List<Product> getProductByOptions(Page page, Date productLaunchTime,
			Date productLanchTime, Integer type, String key) {
				return null;
		// TODO Auto-generated method stub
	}

	@Override
	public List<Product> getSellingProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key) {
		
		return productDao.getSellingProductByOptions(page,productLaunchTime,productStopTime,type,key);
	}

	@Override
	public List<Product> getSelledProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key) {
		// TODO Auto-generated method stub
		return productDao.getSelledProductByOptions(page,productLaunchTime,productStopTime,type,key);
	}

	@Override
	public List<Product> getSellProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key) {
		// TODO Auto-generated method stub
		return productDao.getSellProductByOptions(page,productLaunchTime,productStopTime,type,key);
	}

	@Override
	public Product getProductById(Integer id) {
		return productDao.getProductById(id);
	}

	@Override
	public void delProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.delProduct(product);
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product);
	}

	@Override
	public boolean deleteProducts(String productIds) {
		boolean result=true;
		String[] productList = productIds.split(";");
		int i=0;
		if(productList[0]=="全选" || productList[0]=="取消"){
			i=1;
		}
		for(;i<productList.length;i++){
			Product pt = productDao.getProductById(Short.valueOf(productList[i]));
			try {
				productDao.delProduct(pt);
			} catch (Exception e) {
				// TODO: handle exception
				result=false;
				break;
			}
			
			//pt.setProductTypeIsDelete(true);
			//if(!productTypeDao.updatePT(pt)){
				//result = false;
				//break;
			//}
		}
		return result;
	}

	@Override
	public boolean changeProducts(String productIds) {
		// TODO Auto-generated method stub
		boolean result=true;
		String[] productList = productIds.split(";");
		int i=1;
		/*
		if(productList[0]=="全选" || productList[0]=="取消"){
			i=1;
		}
		*/
		for(;i<productList.length;i++){
			Product pt = productDao.getProductById(Integer.parseInt(productList[i]));
			try {
				if(pt.getProductOnsaleTimeType()==1){
					pt.setProductOnsaleTimeType((short)3);
				}else if(pt.getProductOnsaleTimeType()==3){
					pt.setProductOnsaleTimeType((short)1);
				}
				result=productDao.updateProduct(pt);
			} catch (Exception e) {
				// TODO: handle exception
				result=false;
				break;
			}
			
			//pt.setProductTypeIsDelete(true);
			//if(!productTypeDao.updatePT(pt)){
				//result = false;
				//break;
			//}
		}
		return result;
	}

}
