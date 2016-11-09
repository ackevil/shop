package com.huituopin.yjya.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClothOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ClothOrder")
public class ClothOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer clothOrderId;
	private String clothOrderNum;
	private Integer clothId;
	private Integer userId;
	private Integer shippingAddId;
	private short clothOrderState;
	private Date clothOrderIntime;
	private short logisticsCompanyId;
	private String logisticsNum;
	private boolean clothOrderIsDelete;
	private String clothOrderC1;
	private String clothOrderC2;

	// Constructors

	/** default constructor */
	public ClothOrder() {
		this.clothOrderIntime = new Date();
		this.clothOrderIsDelete = false;
	}

	/** minimal constructor */
	public ClothOrder(String clothOrderNum, Integer clothId, Integer userId,
			Integer shippingAddId, short clothOrderState,
			Date clothOrderIntime, boolean clothOrderIsDelete) {
		this.clothOrderNum = clothOrderNum;
		this.clothId = clothId;
		this.userId = userId;
		this.shippingAddId = shippingAddId;
		this.clothOrderState = clothOrderState;
		this.clothOrderIntime = clothOrderIntime;
		this.clothOrderIsDelete = clothOrderIsDelete;
	}

	/** full constructor */
	public ClothOrder(String clothOrderNum, Integer clothId, Integer userId,
			Integer shippingAddId, short clothOrderState,
			Date clothOrderIntime, short logisticsCompanyId,
			String logisticsNum, boolean clothOrderIsDelete,
			String clothOrderC1, String clothOrderC2) {
		this.clothOrderNum = clothOrderNum;
		this.clothId = clothId;
		this.userId = userId;
		this.shippingAddId = shippingAddId;
		this.clothOrderState = clothOrderState;
		this.clothOrderIntime = clothOrderIntime;
		this.logisticsCompanyId = logisticsCompanyId;
		this.logisticsNum = logisticsNum;
		this.clothOrderIsDelete = clothOrderIsDelete;
		this.clothOrderC1 = clothOrderC1;
		this.clothOrderC2 = clothOrderC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "clothOrder_id", unique = true, nullable = false)
	public Integer getClothOrderId() {
		return this.clothOrderId;
	}

	public void setClothOrderId(Integer clothOrderId) {
		this.clothOrderId = clothOrderId;
	}

	@Column(name = "clothOrder_num", nullable = false, length = 200)
	public String getClothOrderNum() {
		return this.clothOrderNum;
	}

	public void setClothOrderNum(String clothOrderNum) {
		this.clothOrderNum = clothOrderNum;
	}

	@Column(name = "cloth_id", nullable = false)
	public Integer getClothId() {
		return this.clothId;
	}

	public void setClothId(Integer clothId) {
		this.clothId = clothId;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "shipping_add_id", nullable = false)
	public Integer getShippingAddId() {
		return this.shippingAddId;
	}

	public void setShippingAddId(Integer shippingAddId) {
		this.shippingAddId = shippingAddId;
	}

	@Column(name = "cloth_order_state", nullable = false)
	public short getClothOrderState() {
		return this.clothOrderState;
	}

	public void setClothOrderState(short clothOrderState) {
		this.clothOrderState = clothOrderState;
	}

	@Column(name = "cloth_order_intime", nullable = false, length = 0)
	public Date getClothOrderIntime() {
		return this.clothOrderIntime;
	}

	public void setClothOrderIntime(Date clothOrderIntime) {
		this.clothOrderIntime = clothOrderIntime;
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

	@Column(name = "cloth_order_is_delete", nullable = false)
	public boolean getClothOrderIsDelete() {
		return this.clothOrderIsDelete;
	}

	public void setClothOrderIsDelete(boolean clothOrderIsDelete) {
		this.clothOrderIsDelete = clothOrderIsDelete;
	}

	@Column(name = "cloth_order_c1", length = 200)
	public String getClothOrderC1() {
		return this.clothOrderC1;
	}

	public void setClothOrderC1(String clothOrderC1) {
		this.clothOrderC1 = clothOrderC1;
	}

	@Column(name = "cloth_order_c2", length = 200)
	public String getClothOrderC2() {
		return this.clothOrderC2;
	}

	public void setClothOrderC2(String clothOrderC2) {
		this.clothOrderC2 = clothOrderC2;
	}

}