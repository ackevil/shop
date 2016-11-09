package com.huituopin.yjya.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClothType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ClothType")
public class ClothType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short clothTypeId;
	private String clothTypeName;
	private Integer clothTypeNum;
	private Date clothTypeIntime;
	private boolean clothTypeIsDelete;
	private String clothTypeC1;
	private String clothTypeC2;

	// Constructors

	/** default constructor */
	public ClothType() {
		this.clothTypeNum = 0;
		this.clothTypeIntime = new Date();
		this.clothTypeIsDelete = false;
	}

	/** minimal constructor */
	public ClothType(String clothTypeName) {
		this.clothTypeName = clothTypeName;
		this.clothTypeNum = 0;
		this.clothTypeIntime = new Date();
		this.clothTypeIsDelete = false;
	}

	/** full constructor */
	public ClothType(String clothTypeName, Integer clothTypeNum,
			Date clothTypeIntime, boolean clothTypeIsDelete) {
		this.clothTypeName = clothTypeName;
		this.clothTypeNum = clothTypeNum;
		this.clothTypeIntime = new Date();
		this.clothTypeIsDelete = false;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cloth_type_id", unique = true, nullable = false)
	public short getClothTypeId() {
		return this.clothTypeId;
	}

	public void setClothTypeId(short clothTypeId) {
		this.clothTypeId = clothTypeId;
	}

	@Column(name = "cloth_type_name", nullable = false, length = 200)
	public String getClothTypeName() {
		return this.clothTypeName;
	}

	public void setClothTypeName(String clothTypeName) {
		this.clothTypeName = clothTypeName;
	}

	@Column(name = "cloth_type_num")
	public Integer getClothTypeNum() {
		return this.clothTypeNum;
	}

	public void setClothTypeNum(Integer clothTypeNum) {
		this.clothTypeNum = clothTypeNum;
	}

	@Column(name = "cloth_type_intime", nullable = false, length = 0)
	public Date getClothTypeIntime() {
		return this.clothTypeIntime;
	}

	public void setClothTypeIntime(Date clothTypeIntime) {
		this.clothTypeIntime = clothTypeIntime;
	}

	@Column(name = "cloth_type_is_delete", nullable = false)
	public boolean getClothTypeIsDelete() {
		return this.clothTypeIsDelete;
	}

	public void setClothTypeIsDelete(boolean clothTypeIsDelete) {
		this.clothTypeIsDelete = clothTypeIsDelete;
	}

	@Column(name = "cloth_type_c1", length = 200)
	public String getClothTypeC1() {
		return this.clothTypeC1;
	}

	public void setClothTypeC1(String clothTypeC1) {
		this.clothTypeC1 = clothTypeC1;
	}

	@Column(name = "cloth_type_c2", length = 200)
	public String getClothTypeC2() {
		return this.clothTypeC2;
	}

	public void setClothTypeC2(String clothTypeC2) {
		this.clothTypeC2 = clothTypeC2;
	}

}