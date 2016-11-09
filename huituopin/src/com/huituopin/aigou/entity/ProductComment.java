package com.huituopin.aigou.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ProductComment")
public class ProductComment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productComId;
	private Integer podId;
	private Integer productId;
	private Integer userId;
	private String productComContent;
	private Date productComIntime;
	private boolean productComIsDelete;

	// Constructors

	/** default constructor */
	public ProductComment() {
		this.productComIntime = new Date();
		this.productComIsDelete = false;
	}

	/** minimal constructor */
	public ProductComment(Integer podId, Integer productId, Integer userId,
			Date productComIntime, boolean productComIsDelete) {
		this.podId = podId;
		this.productId = productId;
		this.userId = userId;
		this.productComIntime = productComIntime;
		this.productComIsDelete = productComIsDelete;
	}

	/** full constructor */
	public ProductComment(Integer podId, Integer productId, Integer userId,
			String productComContent, Date productComIntime,
			boolean productComIsDelete) {
		this.podId = podId;
		this.productId = productId;
		this.userId = userId;
		this.productComContent = productComContent;
		this.productComIntime = productComIntime;
		this.productComIsDelete = productComIsDelete;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "product_com_id", unique = true, nullable = false)
	public Integer getProductComId() {
		return this.productComId;
	}

	public void setProductComId(Integer productComId) {
		this.productComId = productComId;
	}

	@Column(name = "pod_id", nullable = false)
	public Integer getPodId() {
		return this.podId;
	}

	public void setPodId(Integer podId) {
		this.podId = podId;
	}

	@Column(name = "product_id", nullable = false)
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "product_com_content", length = 500)
	public String getProductComContent() {
		return this.productComContent;
	}

	public void setProductComContent(String productComContent) {
		this.productComContent = productComContent;
	}

	@Column(name = "product_com_intime", nullable = false, length = 0)
	public Date getProductComIntime() {
		return this.productComIntime;
	}

	public void setProductComIntime(Date productComIntime) {
		this.productComIntime = productComIntime;
	}

	@Column(name = "product_com_is_delete", nullable = false)
	public boolean getProductComIsDelete() {
		return this.productComIsDelete;
	}

	public void setProductComIsDelete(boolean productComIsDelete) {
		this.productComIsDelete = productComIsDelete;
	}

}