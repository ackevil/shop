package com.huituopin.yjya.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClothComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ClothComment")
public class ClothComment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer clothComId;
	private Integer clothId;
	private Integer poorId;
	private String clothComContent;
	private short clothComState;
	private Date clothComIntime;
	private boolean clothComIsDelete;
	private String clothComC1;
	private String clothComC2;

	// Constructors

	/** default constructor */
	public ClothComment() {
		this.clothComIntime = new Date();
		this.clothComIsDelete = false;
	}

	/** minimal constructor */
	public ClothComment(Integer clothId, Integer poorId, Date clothComIntime,
			boolean clothComIsDelete) {
		this.clothId = clothId;
		this.poorId = poorId;
		this.clothComIntime = clothComIntime;
		this.clothComIsDelete = clothComIsDelete;
	}

	/** full constructor */
	public ClothComment(Integer clothId, Integer poorId,
			String clothComContent, short clothComState, Date clothComIntime,
			boolean clothComIsDelete, String clothComC1, String clothComC2) {
		this.clothId = clothId;
		this.poorId = poorId;
		this.clothComContent = clothComContent;
		this.clothComState = clothComState;
		this.clothComIntime = clothComIntime;
		this.clothComIsDelete = clothComIsDelete;
		this.clothComC1 = clothComC1;
		this.clothComC2 = clothComC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cloth_com_id", unique = true, nullable = false)
	public Integer getClothComId() {
		return this.clothComId;
	}

	public void setClothComId(Integer clothComId) {
		this.clothComId = clothComId;
	}

	@Column(name = "cloth_id", nullable = false)
	public Integer getClothId() {
		return this.clothId;
	}

	public void setClothId(Integer clothId) {
		this.clothId = clothId;
	}

	@Column(name = "poor_id", nullable = false)
	public Integer getPoorId() {
		return this.poorId;
	}

	public void setPoorId(Integer poorId) {
		this.poorId = poorId;
	}

	@Column(name = "cloth_com_content", length = 500)
	public String getClothComContent() {
		return this.clothComContent;
	}

	public void setClothComContent(String clothComContent) {
		this.clothComContent = clothComContent;
	}

	@Column(name = "cloth_com_state")
	public short getClothComState() {
		return this.clothComState;
	}

	public void setClothComState(short clothComState) {
		this.clothComState = clothComState;
	}

	@Column(name = "cloth_com_intime", nullable = false, length = 0)
	public Date getClothComIntime() {
		return this.clothComIntime;
	}

	public void setClothComIntime(Date clothComIntime) {
		this.clothComIntime = clothComIntime;
	}

	@Column(name = "cloth_com_is_delete", nullable = false)
	public boolean getClothComIsDelete() {
		return this.clothComIsDelete;
	}

	public void setClothComIsDelete(boolean clothComIsDelete) {
		this.clothComIsDelete = clothComIsDelete;
	}

	@Column(name = "cloth_com_c1", length = 200)
	public String getClothComC1() {
		return this.clothComC1;
	}

	public void setClothComC1(String clothComC1) {
		this.clothComC1 = clothComC1;
	}

	@Column(name = "cloth_com_c2", length = 200)
	public String getClothComC2() {
		return this.clothComC2;
	}

	public void setClothComC2(String clothComC2) {
		this.clothComC2 = clothComC2;
	}

}