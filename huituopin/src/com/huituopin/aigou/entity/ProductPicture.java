package com.huituopin.aigou.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ProductPicture")
public class ProductPicture implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productPicId;
	private Integer productId;
	private boolean productPicType;
	private String productPicPath;
	private Date productPicIntime;
	private boolean productPicIsDelete;

	// Constructors

	/** default constructor */
	public ProductPicture() {
		this.productPicIntime = new Date();
		this.productPicIsDelete = false;
	}

	/** full constructor */
	public ProductPicture(Integer productId, boolean productPicType,
			String productPicPath, Date productPicIntime,
			boolean productPicIsDelete) {
		this.productId = productId;
		this.productPicType = productPicType;
		this.productPicPath = productPicPath;
		this.productPicIntime = productPicIntime;
		this.productPicIsDelete = productPicIsDelete;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "product_pic_id", unique = true, nullable = false)
	public Integer getProductPicId() {
		return this.productPicId;
	}

	public void setProductPicId(Integer productPicId) {
		this.productPicId = productPicId;
	}

	@Column(name = "product_id", nullable = false)
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "product_pic_type", nullable = false)
	public boolean getProductPicType() {
		return this.productPicType;
	}

	public void setProductPicType(boolean productPicType) {
		this.productPicType = productPicType;
	}

	@Column(name = "product_pic_path", nullable = false, length = 200)
	public String getProductPicPath() {
		return this.productPicPath;
	}

	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}

	@Column(name = "product_pic_intime", nullable = false, length = 0)
	public Date getProductPicIntime() {
		return this.productPicIntime;
	}

	public void setProductPicIntime(Date productPicIntime) {
		this.productPicIntime = productPicIntime;
	}

	@Column(name = "product_pic_is_delete", nullable = false)
	public boolean getProductPicIsDelete() {
		return this.productPicIsDelete;
	}

	public void setProductPicIsDelete(boolean productPicIsDelete) {
		this.productPicIsDelete = productPicIsDelete;
	}

}