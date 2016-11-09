package com.huituopin.aigou.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ProductType")
public class ProductType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private short productTypeId;
	private String productTypeName;
	private Integer productTypeNum;
	private Date productTypeIntime;
	private boolean productTypeIsDelete;
	private String productTypeC1;
	private String productTypeC2;

	// Constructors

	/** default constructor */
	public ProductType() {
	}

	/** minimal constructor */
	public ProductType(String productTypeName) {
		this.productTypeName = productTypeName;
		this.productTypeNum = 0;
		this.productTypeIntime = new Date();
		this.productTypeIsDelete = false;
	}

	/** full constructor */
	public ProductType(String productTypeName, Integer productTypeNum,
			Date productTypeIntime, boolean productTypeIsDelete,
			String productTypeC1, String productTypeC2) {
		this.productTypeName = productTypeName;
		this.productTypeNum = productTypeNum;
		this.productTypeIntime = productTypeIntime;
		this.productTypeIsDelete = productTypeIsDelete;
		this.productTypeC1 = productTypeC1;
		this.productTypeC2 = productTypeC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "product_type_id", unique = true, nullable = false)
	public short getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(short productTypeId) {
		this.productTypeId = productTypeId;
	}

	@Column(name = "product_type_name", nullable = false, length = 200)
	public String getProductTypeName() {
		return this.productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	@Column(name = "product_type_num")
	public Integer getProductTypeNum() {
		return this.productTypeNum;
	}

	public void setProductTypeNum(Integer productTypeNum) {
		this.productTypeNum = productTypeNum;
	}

	@Column(name = "product_type_intime", nullable = false, length = 0)
	public Date getProductTypeIntime() {
		return this.productTypeIntime;
	}

	public void setProductTypeIntime(Date productTypeIntime) {
		this.productTypeIntime = productTypeIntime;
	}

	@Column(name = "product_type_is_delete", nullable = false)
	public boolean getProductTypeIsDelete() {
		return this.productTypeIsDelete;
	}

	public void setProductTypeIsDelete(boolean productTypeIsDelete) {
		this.productTypeIsDelete = productTypeIsDelete;
	}

	@Column(name = "product_type_c1", length = 200)
	public String getProductTypeC1() {
		return this.productTypeC1;
	}

	public void setProductTypeC1(String productTypeC1) {
		this.productTypeC1 = productTypeC1;
	}

	@Column(name = "product_type_c2", length = 200)
	public String getProductTypeC2() {
		return this.productTypeC2;
	}

	public void setProductTypeC2(String productTypeC2) {
		this.productTypeC2 = productTypeC2;
	}
	
	@Override
	public String toString() {
		return "ProductType [productTypeId=" + productTypeId
				+ ", productTypeName=" + productTypeName + ", productTypeNum="
				+ productTypeNum + ", productTypeIntime=" + productTypeIntime
				+ ", productTypeIsDelete=" + productTypeIsDelete + "]";
	}


}