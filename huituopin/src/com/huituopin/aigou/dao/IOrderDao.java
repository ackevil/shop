package com.huituopin.aigou.dao;

import java.util.List;

import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.SelectOption;
import com.huituopin.common.utils.Page;

public interface IOrderDao {

	List<ProductOrder> getAllOrder(SelectOption option, Page page);

	List<Object> getAllOrderProductByOrderNum(String orderNum);

	ProductOrder getProductOrderById(Integer id);

	void update(ProductOrder productOrder);
	
}
