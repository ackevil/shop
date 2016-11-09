package com.huituopin.yjya.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cloth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Cloth")
public class Cloth implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer clothId;
	private String clothName;
	private short clothType1;
	private short clothType2;
	private short clothType3;
	private String clothDetailinfo;
	private Integer userId;
	private String clothMainpicPath;
	private Date clothIntime;
	private short clothState;
	private boolean clothIsDelete;
	private String clothC1;
	private String clothC2;
	private Integer clothDetailinfoId;
	private boolean clothOrder;
	private Date clothOrderTime;

	// Constructors

	/** default constructor */
	public Cloth() {
		this.clothIntime = new Date();
		this.clothIsDelete = false;
		this.clothOrder = false;
		this.clothOrderTime = new Date();
	}

	/** minimal constructor */
	public Cloth(String clothName, Date clothIntime, boolean clothIsDelete) {
		this.clothName = clothName;
		this.clothIntime = clothIntime;
		this.clothIsDelete = clothIsDelete;
	}

	/** full constructor */
	public Cloth(String clothName, short clothType1, short clothType2,
			short clothType3, String clothDetailinfo, Integer userId,
			String clothMainpicPath, Date clothIntime, short clothState,
			boolean clothIsDelete, String clothC1, String clothC2,
			Integer clothDetailinfoId, boolean clothOrder, Date clothOrderTime) {
		this.clothName = clothName;
		this.clothType1 = clothType1;
		this.clothType2 = clothType2;
		this.clothType3 = clothType3;
		this.clothDetailinfo = clothDetailinfo;
		this.userId = userId;
		this.clothMainpicPath = clothMainpicPath;
		this.clothIntime = clothIntime;
		this.clothState = clothState;
		this.clothIsDelete = clothIsDelete;
		this.clothC1 = clothC1;
		this.clothC2 = clothC2;
		this.clothDetailinfoId = clothDetailinfoId;
		this.clothOrder = clothOrder;
		this.clothOrderTime = clothOrderTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cloth_id", unique = true, nullable = false)
	public Integer getClothId() {
		return this.clothId;
	}

	public void setClothId(Integer clothId) {
		this.clothId = clothId;
	}

	@Column(name = "cloth_name", nullable = false, length = 200)
	public String getClothName() {
		return this.clothName;
	}

	public void setClothName(String clothName) {
		this.clothName = clothName;
	}

	@Column(name = "cloth_type1")
	public short getClothType1() {
		return this.clothType1;
	}

	public void setClothType1(short clothType1) {
		this.clothType1 = clothType1;
	}

	@Column(name = "cloth_type2")
	public short getClothType2() {
		return this.clothType2;
	}

	public void setClothType2(short clothType2) {
		this.clothType2 = clothType2;
	}

	@Column(name = "cloth_type3")
	public short getClothType3() {
		return this.clothType3;
	}

	public void setClothType3(short clothType3) {
		this.clothType3 = clothType3;
	}

	@Column(name = "cloth_detailinfo", length = 16777215)
	public String getClothDetailinfo() {
		return this.clothDetailinfo;
	}

	public void setClothDetailinfo(String clothDetailinfo) {
		this.clothDetailinfo = clothDetailinfo;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "cloth_mainpic_path", length = 200)
	public String getClothMainpicPath() {
		return this.clothMainpicPath;
	}

	public void setClothMainpicPath(String clothMainpicPath) {
		this.clothMainpicPath = clothMainpicPath;
	}

	@Column(name = "cloth_intime", nullable = false, length = 0)
	public Date getClothIntime() {
		return this.clothIntime;
	}

	public void setClothIntime(Date clothIntime) {
		this.clothIntime = clothIntime;
	}

	@Column(name = "cloth_state")
	public short getClothState() {
		return this.clothState;
	}

	public void setClothState(short clothState) {
		this.clothState = clothState;
	}

	@Column(name = "cloth_is_delete", nullable = false)
	public boolean getClothIsDelete() {
		return this.clothIsDelete;
	}

	public void setClothIsDelete(boolean clothIsDelete) {
		this.clothIsDelete = clothIsDelete;
	}

	@Column(name = "cloth_c1", length = 200)
	public String getClothC1() {
		return this.clothC1;
	}

	public void setClothC1(String clothC1) {
		this.clothC1 = clothC1;
	}

	@Column(name = "cloth_c2", length = 200)
	public String getClothC2() {
		return this.clothC2;
	}

	public void setClothC2(String clothC2) {
		this.clothC2 = clothC2;
	}

	@Column(name = "cloth_detailinfo_id")
	public Integer getClothDetailinfoId() {
		return this.clothDetailinfoId;
	}

	public void setClothDetailinfoId(Integer clothDetailinfoId) {
		this.clothDetailinfoId = clothDetailinfoId;
	}

	@Column(name = "cloth_order")
	public boolean getClothOrder() {
		return this.clothOrder;
	}

	public void setClothOrder(boolean clothOrder) {
		this.clothOrder = clothOrder;
	}

	@Column(name = "cloth_order_time", length = 0)
	public Date getClothOrderTime() {
		return this.clothOrderTime;
	}

	public void setClothOrderTime(Date clothOrderTime) {
		this.clothOrderTime = clothOrderTime;
	}

}