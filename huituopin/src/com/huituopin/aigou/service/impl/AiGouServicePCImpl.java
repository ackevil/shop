package com.huituopin.aigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.aigou.dao.IProductDao;
import com.huituopin.aigou.service.IAiGouServicePC;

@Transactional(readOnly=false)
@Service
public class AiGouServicePCImpl implements IAiGouServicePC{

	@Autowired
	private IProductDao productDao;
	@SuppressWarnings("null")
	@Override
	public int getProductPage(int pageSize) {
		int num=0;
		List<Object> ol = productDao.getAllProduct(0, null);
		if(ol != null){
			num = (int) Math.ceil(ol.size()*1.0/pageSize);
		}
		return num;
	}
	@Override
	public List<Object> getProductList(int pageNo, int pageSize, int type) {
		return productDao.getProductByPC(pageNo, pageSize, type);
	}
	@Override
	public int getProductNum(String type) {
		int num = 0;
		List<Object> productList = productDao.getAllProduct(Integer.valueOf(type), null);
		if(productList != null){
			num = productList.size();
		}
		return num;
	}

}
