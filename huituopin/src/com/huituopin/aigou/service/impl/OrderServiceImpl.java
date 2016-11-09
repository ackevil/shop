package com.huituopin.aigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.aigou.dao.IOrderDao;
import com.huituopin.aigou.dao.IProductDao;
import com.huituopin.aigou.dao.IProductOrderDetailDao;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.SelectOption;
import com.huituopin.aigou.service.IOrderService;
import com.huituopin.common.utils.Page;

@Service
@Transactional(readOnly=false)
public class OrderServiceImpl implements IOrderService {

	
	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IProductOrderDetailDao productOrderDetailDao;
	
	@Override
	public List<ProductOrder> getAllOrder(SelectOption option, Page page) {
		return orderDao.getAllOrder(option,page);
	}

	@Override
	public List<ProductOrder> getAllOrder(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOrder> getAllOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getWaitPayOrder(SelectOption option, Page page2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getWaitReleaseOrder(SelectOption option, Page page3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getReleasedOrder(SelectOption option, Page page4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCompletedOrder(SelectOption option, Page page5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getBackPayOrder(SelectOption option, Page page6) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> getAllOrderProductByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		return orderDao.getAllOrderProductByOrderNum(orderNum);
	}

	@Override
	public ProductOrder getProductOrderById(Integer id) {
		// TODO Auto-generated method stub
		return orderDao.getProductOrderById(id);
	}

	@Override
	public boolean update(ProductOrder productOrder) {
		boolean flag=false;
		try {
			orderDao.update(productOrder);
			flag=true;
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
			return flag;
		}
	}

}
