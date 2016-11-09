package com.huituopin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LovingPeople entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LovingPeople", catalog = "huituopin")
public class LovingPeople implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	private Integer loveId;
	private Integer userId;
	private short loveAge;
	private short loveCredicts;
	private boolean loveState;
	private boolean loveIsDelete;
	private String loveC1;
	private String loveC2;

	// Constructors

	/** default constructor */
	public LovingPeople() {
	}

	/** full constructor */
	public LovingPeople(Integer loveId, Integer userId, short loveAge,
			short loveCredicts, boolean loveState, boolean loveIsDelete) {
		this.loveId = loveId;
		this.userId = userId;
		this.loveAge = loveAge;
		this.loveCredicts = loveCredicts;
		this.loveState = true;
		this.loveIsDelete = false;
	}

	// Property accessors
	@Id
	@Column(name = "love_id", unique = true, nullable = false)
	public Integer getLoveId() {
		return this.loveId;
	}

	public void setLoveId(Integer loveId) {
		this.loveId = loveId;
	}

	@Column(name = "user_id", nullable = false)
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

	@Column(name = "love_state", nullable = false)
	public boolean getLoveState() {
		return this.loveState;
	}

	public void setLoveState(boolean loveState) {
		this.loveState = loveState;
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