package com.huituopin.bank.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Credicts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Credicts")
public class Credicts implements java.io.Serializable {

	// Fields

	private Integer credictsId;                //ID
	private Integer userId;                    //用户ID
	private Integer productId;                 //物品或商品ID
	private Integer credictsType;              //积分类型  1：支出   0 ：收入
	private String credictsImg;                //图片 （如果是物品 则统一用默认图片，如果是商品则用商品主图）
	private String credictsName;               //名称  （如果是物品 则统一用默认名称，如果是商品则用商品名称）
	private String credictsChanges;            //变化的积分值
	private Date credictsDate;                 //产生日期
	private String credictsRemark;             //备注				
	private boolean credictsIsDelete;          //删除标记 1(true)
	private String credictsC1;
	private String credictsC2;

	// Constructors

	/** default constructor */
	public Credicts() {
	}

	/** full constructor */
	public Credicts(Integer userId, Integer productId, Integer credictsType,
			String credictsImg, String credictsName, String credictsChanges,
			Date credictsDate, String credictsRemark, boolean credictsIsDelete,
			String credictsC1, String credictsC2) {
		this.userId = userId;
		this.productId = productId;
		this.credictsType = credictsType;
		this.credictsImg = credictsImg;
		this.credictsName = credictsName;
		this.credictsChanges = credictsChanges;
		this.credictsDate = credictsDate;
		this.credictsRemark = credictsRemark;
		this.credictsIsDelete = credictsIsDelete;
		this.credictsC1 = credictsC1;
		this.credictsC2 = credictsC2;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "credicts_id", unique = true, nullable = false)
	public Integer getCredictsId() {
		return this.credictsId;
	}

	public void setCredictsId(Integer credictsId) {
		this.credictsId = credictsId;
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

	@Column(name = "credicts_type")
	public Integer getCredictsType() {
		return this.credictsType;
	}

	public void setCredictsType(Integer credictsType) {
		this.credictsType = credictsType;
	}

	@Column(name = "credicts_img", length = 200)
	public String getCredictsImg() {
		return this.credictsImg;
	}

	public void setCredictsImg(String credictsImg) {
		this.credictsImg = credictsImg;
	}

	@Column(name = "credicts_name", length = 200)
	public String getCredictsName() {
		return this.credictsName;
	}

	public void setCredictsName(String credictsName) {
		this.credictsName = credictsName;
	}

	@Column(name = "credicts_changes", length = 50)
	public String getCredictsChanges() {
		return this.credictsChanges;
	}

	public void setCredictsChanges(String credictsChanges) {
		this.credictsChanges = credictsChanges;
	}

	@Column(name = "credicts_date", length = 0)
	public Date getCredictsDate() {
		return this.credictsDate;
	}

	public void setCredictsDate(Date credictsDate) {
		this.credictsDate = credictsDate;
	}

	@Column(name = "credicts_remark", length = 100)
	public String getCredictsRemark() {
		return this.credictsRemark;
	}

	public void setCredictsRemark(String credictsRemark) {
		this.credictsRemark = credictsRemark;
	}

	@Column(name = "credicts_is_delete")
	public boolean getCredictsIsDelete() {
		return this.credictsIsDelete;
	}

	public void setCredictsIsDelete(boolean credictsIsDelete) {
		this.credictsIsDelete = credictsIsDelete;
	}

	@Column(name = "credicts_c1", length = 200)
	public String getCredictsC1() {
		return this.credictsC1;
	}

	public void setCredictsC1(String credictsC1) {
		this.credictsC1 = credictsC1;
	}

	@Column(name = "credicts_c2", length = 200)
	public String getCredictsC2() {
		return this.credictsC2;
	}

	public void setCredictsC2(String credictsC2) {
		this.credictsC2 = credictsC2;
	}

}