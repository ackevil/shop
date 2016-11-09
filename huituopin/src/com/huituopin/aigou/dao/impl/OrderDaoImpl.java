package com.huituopin.aigou.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IOrderDao;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.SelectOption;
import com.huituopin.common.constants.ProductOrderStatus;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<ProductOrder, String> implements IOrderDao {

	@Override
	public List<ProductOrder> getAllOrder(SelectOption option, Page page) {
		
		Date orderStartTime=null;
		Date orderStopTime=null;
		Integer orderState=null;
		//Integer payWay;
		String	keyword=null;
		if(option!=null){
			orderStartTime=option.getOrderStartTime();
			orderStopTime=option.getOrderStopTime();
			orderState=option.getOrderState();
			//payWay=option.getPayWay();
			keyword=option.getKeyword();
		}
		
		String sql="from ProductOrder where  productOrderIsDelete = 0 ";
		String sqlCount="select count(*) from ProductOrder where product_order_is_delete = 0 ";
		
		if(orderState!=null){
			sql+=" and productOrderState = "+  orderState;
			sqlCount+=" and product_order_state = "+  orderState;
		}
		
		if(orderStartTime!=null&&orderStopTime!=null){
				sql+=" and productOrderIntime between ? and ? ";
				sqlCount+=" and product_order_intime between ? and ? ";
		}
		sql+=" order by productOrderIntime desc";
		sqlCount+=" order by product_order_intime desc";
		/*
		if(payWay!=null){
			sql+=" and productOnsaleTimeType="+type;
			sqlCount+=" and product_onsale_time_type="+type;
		}
		*/
		/*
		if(keyword!=null){
			sql+=" and productName like '%"+keyword+"%'";
			sqlCount+=" and product_name like '%"+keyword+"%'";
		}
		*/
		
		System.out.println(sql);
		Query query=createQuery(sql);
		if(orderStartTime!=null&&orderStopTime!=null)
		{
			query.setDate(0, orderStartTime);
			query.setDate(1, orderStopTime);
		}

		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		
		Query queryCount=createSQLQuery(sqlCount);
		if(orderStartTime!=null&&orderStopTime!=null)
		{
			queryCount.setDate(0, orderStartTime);
			queryCount.setDate(1, orderStopTime);
		}
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<ProductOrder> list= (List<ProductOrder>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
		
	}

	@Override
	public List<Object> getAllOrderProductByOrderNum(String orderNum) {
		String sql = "select p.product_Id,pod.product_num,p.product_mainpic_path,p.product_name,p.product_price from ProductOrderDetail pod left join Product p on p.product_Id = pod.product_id where pod.po_num = "+orderNum;
		Query query = createSQLQuery(sql.toString());
		List<Object> list = query.list();
		return list;
	}

	@Override
	public ProductOrder getProductOrderById(Integer id) {
		List<ProductOrder> ls = findBy("productOrderId",id);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

}
