package com.huituopin.aigou.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShoppingCart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer scId;
	private Integer userId;
	private Integer productId;
	private short productNum;
	private float productPrice;
	private Date scIntime;
	private boolean scIsDelete;

	// Constructors

	/** default constructor */
	public ShoppingCart() {
		this.scIsDelete = false;
		this.scIntime = new Date();
	}

	/** full constructor */
	public ShoppingCart(Integer userId, Integer productId, short productNum,
			float productPrice) {
		this.userId = userId;
		this.productId = productId;
		this.productNum = productNum;
		this.productPrice = productPrice;
		this.scIsDelete = false;
		this.scIntime = new Date();
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sc_id", unique = true, nullable = false)
	public Integer getScId() {
		return this.scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "product_id")
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "product_num")
	public short getProductNum() {
		return this.productNum;
	}

	public void setProductNum(short productNum) {
		this.productNum = productNum;
	}

	@Column(name = "product_price", precision = 12, scale = 0)
	public float getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "sc_intime", length = 0)
	public Date getScIntime() {
		return this.scIntime;
	}

	public void setScIntime(Date scIntime) {
		this.scIntime = scIntime;
	}

	@Column(name = "sc_is_delete")
	public boolean getScIsDelete() {
		return this.scIsDelete;
	}

	public void setScIsDelete(boolean scIsDelete) {
		this.scIsDelete = scIsDelete;
	}

}