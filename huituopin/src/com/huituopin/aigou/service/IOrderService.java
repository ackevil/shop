package com.huituopin.aigou.service;

import java.util.List;

import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.SelectOption;
import com.huituopin.common.utils.Page;

public interface IOrderService {
	//获取所有订单
	public List<ProductOrder> getAllOrder(SelectOption option ,Page page);
	public List<ProductOrder> getAllOrder(Page page);
	public List<ProductOrder> getAllOrder();
	//待付款订单
	public void getWaitPayOrder(SelectOption option, Page page2);
	//待发货订单
	public void getWaitReleaseOrder(SelectOption option, Page page3);
	//已发货订单
	public void getReleasedOrder(SelectOption option, Page page4);
	//已完成订单
	public void getCompletedOrder(SelectOption option, Page page5);
	//申请退款订单
	public void getBackPayOrder(SelectOption option, Page page6);
	public List<Object> getAllOrderProductByOrderNum(String orderNum);
	public ProductOrder getProductOrderById(Integer id);
	public boolean update(ProductOrder productOrder);
}
