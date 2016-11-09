package com.huituopin.yjya.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClothPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ClothPicture")
public class ClothPicture implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer clothPicId;
	private Integer clothId;
	private boolean clothPicType;  //图片类型，false：详情图片，true：评价图片
	private String clothPicPath;
	private Date clothPicIntime;
	private boolean clothPicIsDelete;
	private String clothPicC1;
	private String clothPicC2;

	// Constructors

	/** default constructor */
	public ClothPicture() {
		this.clothPicIntime = new Date();
		this.clothPicIsDelete = false;
	}

	/** minimal constructor */
	public ClothPicture(Integer clothId, boolean clothPicType,
			String clothPicPath, Date clothPicIntime, boolean clothPicIsDelete) {
		this.clothId = clothId;
		this.clothPicType = clothPicType;
		this.clothPicPath = clothPicPath;
		this.clothPicIntime = clothPicIntime;
		this.clothPicIsDelete = clothPicIsDelete;
	}

	/** full constructor */
	public ClothPicture(Integer clothId, boolean clothPicType,
			String clothPicPath, Date clothPicIntime, boolean clothPicIsDelete,
			String clothPicC1, String clothPicC2) {
		this.clothId = clothId;
		this.clothPicType = clothPicType;
		this.clothPicPath = clothPicPath;
		this.clothPicIntime = clothPicIntime;
		this.clothPicIsDelete = clothPicIsDelete;
		this.clothPicC1 = clothPicC1;
		this.clothPicC2 = clothPicC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cloth_pic_id", unique = true, nullable = false)
	public Integer getClothPicId() {
		return this.clothPicId;
	}

	public void setClothPicId(Integer clothPicId) {
		this.clothPicId = clothPicId;
	}

	@Column(name = "cloth_id", nullable = false)
	public Integer getClothId() {
		return this.clothId;
	}

	public void setClothId(Integer clothId) {
		this.clothId = clothId;
	}

	@Column(name = "cloth_pic_type", nullable = false)
	public boolean getClothPicType() {
		return this.clothPicType;
	}

	public void setClothPicType(boolean clothPicType) {
		this.clothPicType = clothPicType;
	}

	@Column(name = "cloth_pic_path", nullable = false, length = 200)
	public String getClothPicPath() {
		return this.clothPicPath;
	}

	public void setClothPicPath(String clothPicPath) {
		this.clothPicPath = clothPicPath;
	}

	@Column(name = "cloth_pic_intime", nullable = false, length = 0)
	public Date getClothPicIntime() {
		return this.clothPicIntime;
	}

	public void setClothPicIntime(Date clothPicIntime) {
		this.clothPicIntime = clothPicIntime;
	}

	@Column(name = "cloth_pic_is_delete", nullable = false)
	public boolean getClothPicIsDelete() {
		return this.clothPicIsDelete;
	}

	public void setClothPicIsDelete(boolean clothPicIsDelete) {
		this.clothPicIsDelete = clothPicIsDelete;
	}

	@Column(name = "cloth_pic_c1", length = 200)
	public String getClothPicC1() {
		return this.clothPicC1;
	}

	public void setClothPicC1(String clothPicC1) {
		this.clothPicC1 = clothPicC1;
	}

	@Column(name = "cloth_pic_c2", length = 200)
	public String getClothPicC2() {
		return this.clothPicC2;
	}

	public void setClothPicC2(String clothPicC2) {
		this.clothPicC2 = clothPicC2;
	}

}