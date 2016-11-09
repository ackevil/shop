package com.huituopin.aigou.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductComment;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.ProductOrderDetail;
import com.huituopin.aigou.entity.ProductPicture;
import com.huituopin.aigou.entity.ProductType;

public interface IAiGouService {
	/**
	 * 获取所有的商品类别
	 * @return
	 */
	public List<ProductType> getAllProductType();
	
	/**
	 * 获取所有的商品
	 * @return
	 */
	//public List<Object> getAllProduct(int type,String keyword,int start,int num);
	public List<Object> getAllProduct(int type,String keyword);
	/**
	 * 根据产品Id获得该产品
	 * @param pId
	 * @return
	 */
	public Product getProductById(int pId);
	
	/**
	 * 插入一条购物车记录
	 * 首先检测该产品是否已经添加到购物车，如果已经添加过，则直接更细个数即可，否则把该产品添加到购物车
	 * @param userId
	 * @param pId
	 * @param pNum
	 * @return
	 */
	public boolean insertShoppingCart(int userId,int pId,int pNum);

	
	/**
	 * 产生一个订单
	 * @param userId
	 * @param saId
	 * @param pId
	 * @param pNum
	 * @return
	 */
	public Integer insertProductOrder(int userId,String saId,String pId,String pNum,String pCount,String resource);
	public ProductOrder getProductOrderById(Integer id);
	/**
	 * 获取某一用户某一状态的所有订单信息
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<HashMap<String,Object>> getPOByUserIdAndStatus(int userId,short status);
	
	/**
	 * 获取某一用户没评价的订单
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Object> getPODByUserIdAndStatus(int userId,short status);

	/**
	 * 终止一个订单
	 * @param poId
	 * @return
	 */
	public boolean cancelOrder(int poId);
	
	/**
	 * 获取一个用户的购物车记录
	 * @param userId
	 * @return
	 */
	public List<Object> getSCByUserId(int userId);
	
	/**
	 * 根据ID删除一条购物车记录
	 * @param scId
	 * @return
	 */
	public boolean deleteSCBySCId(int scId);
	
	/**
	 * 根据产品Id和产品数量生成相应的订单信息
	 * @param pId
	 * @param pNum
	 * @return
	 */
	public List<HashMap<String,Object>> generatePOByPIdAndPNum(String pId,String pNum);
	
	/**
	 * 根据订单详情ID获取该订单详情信息
	 * @param podId
	 * @return
	 */
	public List<Object> getPODInfoByPODId(int podId);
	
	/**
	 * 插入一条产品评价记录
	 * 1.先插入一条productComment记录
	 * 2.修改对应ProductOrderDetail中的评价标记
	 * @param pId
	 * @param podId
	 * @param detailInfo
	 * @param picPaths
	 * @return
	 */
	public boolean insertProductComment(int userId,String pId,String podId,String detailInfo,String picPaths);
	
	/**
	 * 插入一条产品图片
	 * @param pp
	 * @return
	 */
	public boolean insertProductPicture(ProductPicture pp);
	
	/**
	 * 获取某一用户的所有评价
	 * @param userId
	 * @return
	 */
	public List<HashMap<String,Object>> getProductCommentByUserId(int userId);
	
	/**
	 * 获取某一产品的所有
	 * @param pId
	 * @return
	 */
	public List<HashMap<String,Object>> getProductCommentByPId(int pId);
	
	/**
	 * 获取某一订单的所有商品
	 * @param poId
	 * @return
	 */
	public List<ProductOrderDetail> getByPOId(int poId);

	public void updateProductOrder(ProductOrder productOrder);
	
	/**
	 * 更改某个订单的状态
	 * @param poId
	 * @param status
	 */
	public boolean updateProductOrderStatus(int poId,short status);
	
	/**
	 * 获得一个用户的所有消费记录
	 * @param userId
	 * @return
	 */
	public List<ProductOrder> getPayedByUserId(int userId);
	
	/**
	 * 获取一个订单的所有产品
	 * @param poId
	 * @return
	 */
	public List<Product> getProductsByPOId(int poId);
	
	
	/**
	 * 根据订单号获取该订单的所有订单详情
	 * @param poNum
	 * @return
	 */
	public List<ProductOrderDetail> getPODByPONum(String poNum);
	
	/**
	 * 获取一个订单中的库存信息
	 * @param poId
	 * @return
	 */
	public String getStockInfoByPOId(int poId);
	
	/**
	 * 爱心人士兑换商品
	 * @param userId
	 * @param saId
	 * @param pId
	 * @param pCredict
	 * @return
	 */
	public int duihuan(int userId,String saId,String pId,String pPrice,String pRealCount,String credictUsed);
	
	/**
	 * 兑换支付被取消或者失败后，对产品数进行还原
	 * @param pId
	 * @return
	 */
	public String duihuan_paycancel(int pId);
	
	/**
	 * 购买支付给取消后失败后，对产品数进行还原
	 * @param pId
	 * @param pNum
	 * @return
	 */
	public String payment_cancel(String pId,String pNum);

	/**
	 * 爱心人士在我的订单，继续支付，然后取消支付或支付失败
	 * @param poId
	 * @return
	 */
	public String payment_order_cancel(int poId);
}
