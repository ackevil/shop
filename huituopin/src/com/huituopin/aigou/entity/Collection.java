package com.huituopin.aigou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Collection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Collection")
public class Collection implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4929505284728557234L;
	private Integer collectionId;
	private Integer userId;
	private Integer productId;
	private String collectionDate;
	private boolean collectionIsDelete;
	private String collectionC1;
	private String collectionC2;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** full constructor */
	public Collection(Integer userId, Integer productId, String collectionDate,
			boolean collectionIsDelete, String collectionC1, String collectionC2) {
		this.userId = userId;
		this.productId = productId;
		this.collectionDate = collectionDate;
		this.collectionIsDelete = collectionIsDelete;
		this.collectionC1 = collectionC1;
		this.collectionC2 = collectionC2;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "collection_id", unique = true, nullable = false)
	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "product_Id")
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "collection_date", length = 50)
	public String getCollectionDate() {
		return this.collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

	@Column(name = "collection_is_delete")
	public boolean getCollectionIsDelete() {
		return this.collectionIsDelete;
	}

	public void setCollectionIsDelete(boolean collectionIsDelete) {
		this.collectionIsDelete = collectionIsDelete;
	}

	@Column(name = "collection_c1", length = 200)
	public String getCollectionC1() {
		return this.collectionC1;
	}

	public void setCollectionC1(String collectionC1) {
		this.collectionC1 = collectionC1;
	}

	@Column(name = "collection_c2", length = 200)
	public String getCollectionC2() {
		return this.collectionC2;
	}

	public void setCollectionC2(String collectionC2) {
		this.collectionC2 = collectionC2;
	}

}