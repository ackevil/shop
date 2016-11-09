package com.huituopin.aigou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductOrderDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ProductOrderDetail")
public class ProductOrderDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer poDetailId;
	private String poNum;
	private Integer productId;
	private short productNum;
	private boolean productIsComment;
	private boolean poDetailIsDelete;

	// Constructors

	/** default constructor */
	public ProductOrderDetail() {
		this.poDetailIsDelete = false;
		this.productIsComment = false;
	}

	/** full constructor */
	public ProductOrderDetail(String poNum, Integer productId,
			short productNum) {
		this.poNum = poNum;
		this.productId = productId;
		this.productNum = productNum;
		this.poDetailIsDelete = false;
		this.productIsComment = false;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "po_detail_id", unique = true, nullable = false)
	public Integer getPoDetailId() {
		return this.poDetailId;
	}

	public void setPoDetailId(Integer poDetailId) {
		this.poDetailId = poDetailId;
	}

	@Column(name = "po_num", length = 200)
	public String getPoNum() {
		return this.poNum;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
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

	@Column(name = "product_is_comment")
	public boolean getProductIsComment() {
		return this.productIsComment;
	}

	public void setProductIsComment(boolean productIsComment) {
		this.productIsComment = productIsComment;
	}

	@Column(name = "po_detail_is_delete")
	public boolean getPoDetailIsDelete() {
		return this.poDetailIsDelete;
	}

	public void setPoDetailIsDelete(boolean poDetailIsDelete) {
		this.poDetailIsDelete = poDetailIsDelete;
	}

}