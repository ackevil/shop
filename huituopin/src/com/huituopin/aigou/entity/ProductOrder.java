package com.huituopin.aigou.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ProductOrder")
public class ProductOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productOrderId;  
	private String productOrderNum;    //订单hao
	private Integer userId;				//订单用户
	private short productsNum;			//产品数量
	private float productOrderAllCount;	//实付金额
	private Integer productOrderCredictUsed; //使用积分的数量
	private float productOrderPPrice;   //产品原价
	
	private Integer shippingAddId;		
	private short productOrderState;	//订单状态
	private String transactionId;		//微信订单号 作为 交易流水号
	private Date productOrderIntime;	//订单时间
	private Date productOrderPaytime;   //支付时间
	private boolean productOrderIsNeedLogistics;	//是否需要物流
	private short logisticsCompanyId;			//物流公司
	private String logisticsCompanyName;		//物流公司名字
	private String logisticsNum;				//订单号
	private boolean productOrderIsDelete;		//订单是否删除

	// Constructors

	/** default constructor */
	public ProductOrder() {
		this.productOrderIntime = new Date();
		this.productOrderIsDelete = false;
	}

	/** minimal constructor */
	public ProductOrder(String productOrderNum, Integer userId,
			short productsNum, Integer shippingAddId, short productOrderState,
			Date productOrderIntime, boolean productOrderIsDelete) {
		this.productOrderNum = productOrderNum;
		this.userId = userId;
		this.productsNum = productsNum;
		this.shippingAddId = shippingAddId;
		this.productOrderState = productOrderState;
		this.productOrderIntime = productOrderIntime;
		this.productOrderIsDelete = productOrderIsDelete;
	}

	/** full constructor */
	public ProductOrder(String productOrderNum, Integer userId,
			short productsNum, float productOrderAllCount,
			Integer shippingAddId, short productOrderState,
			Date productOrderIntime, boolean productOrderIsNeedLogistics,
			short logisticsCompanyId, String logisticsNum,
			boolean productOrderIsDelete) {
		this.productOrderNum = productOrderNum;
		this.userId = userId;
		this.productsNum = productsNum;
		this.productOrderAllCount = productOrderAllCount;
		this.shippingAddId = shippingAddId;
		this.productOrderState = productOrderState;
		this.productOrderIntime = productOrderIntime;
		this.productOrderIsNeedLogistics = productOrderIsNeedLogistics;
		this.logisticsCompanyId = logisticsCompanyId;
		this.logisticsNum = logisticsNum;
		this.productOrderIsDelete = productOrderIsDelete;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productOrder_id", unique = true, nullable = false)
	public Integer getProductOrderId() {
		return this.productOrderId;
	}

	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
	}

	@Column(name = "productOrder_num", nullable = false, length = 200)
	public String getProductOrderNum() {
		return this.productOrderNum;
	}

	public void setProductOrderNum(String productOrderNum) {
		this.productOrderNum = productOrderNum;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "products_num", nullable = false)
	public short getProductsNum() {
		return this.productsNum;
	}

	public void setProductsNum(short productsNum) {
		this.productsNum = productsNum;
	}

	@Column(name = "product_order_AllCount", precision = 12, scale = 0)
	public float getProductOrderAllCount() {
		return this.productOrderAllCount;
	}

	public void setProductOrderAllCount(float productOrderAllCount) {
		this.productOrderAllCount = productOrderAllCount;
	}

	@Column(name = "product_order_credict_used")
	public int getProductOrderCredictUsed() {
		return productOrderCredictUsed;
	}

	public void setProductOrderCredictUsed(int productOrderCredictUsed) {
		this.productOrderCredictUsed = productOrderCredictUsed;
	}

	@Column(name = "product_order_p_price", precision = 12, scale = 0)
	public float getProductOrderPPrice() {
		return productOrderPPrice;
	}

	public void setProductOrderPPrice(float productOrderPPrice) {
		this.productOrderPPrice = productOrderPPrice;
	}


	@Column(name = "shipping_add_id", nullable = false)
	public Integer getShippingAddId() {
		return this.shippingAddId;
	}

	public void setShippingAddId(Integer shippingAddId) {
		this.shippingAddId = shippingAddId;
	}

	@Column(name = "product_order_state", nullable = false)
	public short getProductOrderState() {
		return this.productOrderState;
	}

	public void setProductOrderState(short productOrderState) {
		this.productOrderState = productOrderState;
	}

	@Column(name = "product_order_intime", nullable = false, length = 0)
	public Date getProductOrderIntime() {
		return this.productOrderIntime;
	}

	public void setProductOrderIntime(Date productOrderIntime) {
		this.productOrderIntime = productOrderIntime;
	}
	
	@Column(name = "product_order_paytime")
	public Date getProductOrderPaytime() {
		return this.productOrderPaytime;
	}

	public void setProductOrderPaytime(Date productOrderPaytime) {
		this.productOrderPaytime = productOrderPaytime;
	}

	@Column(name = "product_order_isNeedLogistics")
	public boolean getProductOrderIsNeedLogistics() {
		return this.productOrderIsNeedLogistics;
	}

	public void setProductOrderIsNeedLogistics(
			boolean productOrderIsNeedLogistics) {
		this.productOrderIsNeedLogistics = productOrderIsNeedLogistics;
	}

	@Column(name = "logistics_company_id")
	public short getLogisticsCompanyId() {
		return this.logisticsCompanyId;
	}

	public void setLogisticsCompanyId(short logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	@Column(name = "logistics_num", length = 500)
	public String getLogisticsNum() {
		return this.logisticsNum;
	}

	public void setLogisticsNum(String logisticsNum) {
		this.logisticsNum = logisticsNum;
	}

	@Column(name = "product_order_is_delete", nullable = false)
	public boolean getProductOrderIsDelete() {
		return this.productOrderIsDelete;
	}

	public void setProductOrderIsDelete(boolean productOrderIsDelete) {
		this.productOrderIsDelete = productOrderIsDelete;
	}
	@Column(name = "transaction_id", length = 500)
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@Column(name = "logistics_company_name", length = 200)
	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

}