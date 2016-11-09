package com.huituopin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PoorPeople entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PoorPeople", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class PoorPeople implements java.io.Serializable {

	// Fields

	private Integer poorId;   			//贫困人士详细信息ID  
	private Integer userId;				//贫困人士详细信息对应的用户表ID
	private String poorAge;		 		//贫困人士详细信息出生年月
	private String poorAddress;			//贫困人士详细信息地址
	private String poorDetailAddress;	//贫困人士详细信息详细地址
	private String poorNation;			//贫困人士详细信息民族
	private String poorPostal;			//贫困人士详细信息邮政编码
	private int poorState;			    //贫困人士详细信息审核状态   1：未审核，2：审核通过，3：不通过
	private boolean poorIsDelete;		//贫困人士详细信息删除标记
	private String poorC1;
	private String poorC2;

	// Constructors

	/** default constructor */
	public PoorPeople() {
	}

	/** minimal constructor */
	public PoorPeople(Integer userId, String poorAge, String poorAddress,
			String poorDetailAddress, String poorNation, String poorPostal,
			int poorState, boolean poorIsDelete) {
		this.userId = userId;
		this.poorAge = poorAge;
		this.poorAddress = poorAddress;
		this.poorDetailAddress = poorDetailAddress;
		this.poorNation = poorNation;
		this.poorPostal = poorPostal;
		this.poorState = poorState;
		this.poorIsDelete = poorIsDelete;
	}

	/** full constructor */
	public PoorPeople(Integer userId, String poorAge, String poorAddress,
			String poorDetailAddress, String poorNation, String poorPostal,
			int poorState, boolean poorIsDelete, String poorC1,
			String poorC2) {
		this.userId = userId;
		this.poorAge = poorAge;
		this.poorAddress = poorAddress;
		this.poorDetailAddress = poorDetailAddress;
		this.poorNation = poorNation;
		this.poorPostal = poorPostal;
		this.poorState = poorState;
		this.poorIsDelete = poorIsDelete;
		this.poorC1 = poorC1;
		this.poorC2 = poorC2;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "poor_id", unique = true, nullable = false)
	public Integer getPoorId() {
		return this.poorId;
	}

	public void setPoorId(Integer poorId) {
		this.poorId = poorId;
	}

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "poor_Age", nullable = false, length = 20)
	public String getPoorAge() {
		return this.poorAge;
	}

	public void setPoorAge(String poorAge) {
		this.poorAge = poorAge;
	}

	@Column(name = "poor_address", nullable = false, length = 50)
	public String getPoorAddress() {
		return this.poorAddress;
	}

	public void setPoorAddress(String poorAddress) {
		this.poorAddress = poorAddress;
	}

	@Column(name = "poor_detail_address", nullable = false, length = 100)
	public String getPoorDetailAddress() {
		return this.poorDetailAddress;
	}

	public void setPoorDetailAddress(String poorDetailAddress) {
		this.poorDetailAddress = poorDetailAddress;
	}

	@Column(name = "poor_nation", nullable = false, length = 20)
	public String getPoorNation() {
		return this.poorNation;
	}

	public void setPoorNation(String poorNation) {
		this.poorNation = poorNation;
	}

	@Column(name = "poor_postal", nullable = false, length = 50)
	public String getPoorPostal() {
		return this.poorPostal;
	}

	public void setPoorPostal(String poorPostal) {
		this.poorPostal = poorPostal;
	}

	@Column(name = "poor_state", nullable = false)
	public int getPoorState() {
		return this.poorState;
	}

	public void setPoorState(int poorState) {
		this.poorState = poorState;
	}

	@Column(name = "poor_is_delete", nullable = false)
	public boolean getPoorIsDelete() {
		return this.poorIsDelete;
	}

	public void setPoorIsDelete(boolean poorIsDelete) {
		this.poorIsDelete = poorIsDelete;
	}

	@Column(name = "poor_c1", length = 200)
	public String getPoorC1() {
		return this.poorC1;
	}

	public void setPoorC1(String poorC1) {
		this.poorC1 = poorC1;
	}

	@Column(name = "poor_c2", length = 200)
	public String getPoorC2() {
		return this.poorC2;
	}

	public void setPoorC2(String poorC2) {
		this.poorC2 = poorC2;
	}

}