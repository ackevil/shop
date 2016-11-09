package com.huituopin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * LovingPeople entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LovingPeople", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class LovingPeople implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8117992038644014324L;
	private Integer loveId;   			//爱心人士id
	private Integer userId;				//爱心人士对应的userId
	private short loveAge;				//爱心人士年龄
	private short loveCredicts;			//爱心人士积分
	private String loveAddress;			//爱心人士地址
	private String loveDetailAddress;	//爱心人士详细地址
	private boolean loveIsDelete;		//爱心人士删除标记
	private String loveC1;
	private String loveC2;

	// Constructors

	/** default constructor */
	public LovingPeople() {
	}

	/** minimal constructor */
	public LovingPeople(Integer userId, boolean loveIsDelete) {
		this.userId = userId;
		this.loveIsDelete = loveIsDelete;
	}

	/** full constructor */
	public LovingPeople(Integer userId, short loveAge, short loveCredicts,
			String loveAddress, String loveDetailAddress, boolean loveIsDelete,
			String loveC1, String loveC2) {
		this.userId = userId;
		this.loveAge = loveAge;
		this.loveCredicts = loveCredicts;
		this.loveAddress = loveAddress;
		this.loveDetailAddress = loveDetailAddress;
		this.loveIsDelete = loveIsDelete;
		this.loveC1 = loveC1;
		this.loveC2 = loveC2;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "love_id", unique = true, nullable = false)
	public Integer getLoveId() {
		return this.loveId;
	}

	public void setLoveId(Integer loveId) {
		this.loveId = loveId;
	}

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "love_age")
	public short getLoveAge() {
		return this.loveAge;
	}

	public void setLoveAge(short loveAge) {
		this.loveAge = loveAge;
	}

	@Column(name = "love_credicts")
	public short getLoveCredicts() {
		return this.loveCredicts;
	}

	public void setLoveCredicts(short loveCredicts) {
		this.loveCredicts = loveCredicts;
	}

	@Column(name = "love_address", length = 100)
	public String getLoveAddress() {
		return this.loveAddress;
	}

	public void setLoveAddress(String loveAddress) {
		this.loveAddress = loveAddress;
	}

	@Column(name = "love_detail_address", length = 100)
	public String getLoveDetailAddress() {
		return this.loveDetailAddress;
	}

	public void setLoveDetailAddress(String loveDetailAddress) {
		this.loveDetailAddress = loveDetailAddress;
	}

	@Column(name = "love_is_delete", nullable = false)
	public boolean getLoveIsDelete() {
		return this.loveIsDelete;
	}

	public void setLoveIsDelete(boolean loveIsDelete) {
		this.loveIsDelete = loveIsDelete;
	}

	@Column(name = "love_c1", length = 200)
	public String getLoveC1() {
		return this.loveC1;
	}

	public void setLoveC1(String loveC1) {
		this.loveC1 = loveC1;
	}

	@Column(name = "love_c2", length = 200)
	public String getLoveC2() {
		return this.loveC2;
	}

	public void setLoveC2(String loveC2) {
		this.loveC2 = loveC2;
	}

}