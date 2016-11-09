package com.huituopin.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * PoorPeople entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PoorPeople", catalog = "huituopin")
public class PoorPeople implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer poorId;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="user_id",nullable=false,updatable=false)
	private User user;
	private String poorRealname;
	private short poorAge;
	private String poorSchool;
	private String poorProvince;
	private String poorCity;
	private String poorDistrict;
	private String poorDetailAddress;
	private String poorBackground;
	private boolean poorState;
	private boolean poorIsDelete;
	private String poorC1;
	private String poorC2;

	// Constructors

	/** default constructor */
	public PoorPeople() {
	}

	/** full constructor */
	public PoorPeople(User user, String poorRealname, short poorAge,
			String poorProvince, String poorCity, String poorDistrict,
			String poorDetailAddress, String poorBackground) {
		this.user = user;
		this.poorRealname = poorRealname;
		this.poorAge = poorAge;
		this.poorProvince = poorProvince;
		this.poorCity = poorCity;
		this.poorDistrict = poorDistrict;
		this.poorDetailAddress = poorDetailAddress;
		this.poorBackground = poorBackground;
		this.poorState = false;
		this.poorIsDelete = false;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "poor_id", unique = true, nullable = false)
	public Integer getPoorId() {
		return this.poorId;
	}

	public void setPoorId(Integer poorId) {
		this.poorId = poorId;
	}

	@Column(name = "poor_realname", nullable = false, length = 50)
	public String getPoorRealname() {
		return this.poorRealname;
	}

	public void setPoorRealname(String poorRealname) {
		this.poorRealname = poorRealname;
	}

	@Column(name = "poor_age", nullable = false)
	public short getPoorAge() {
		return this.poorAge;
	}

	public void setPoorAge(short poorAge) {
		this.poorAge = poorAge;
	}

	@Column(name = "poor_school", length = 50)
	public String getPoorSchool() {
		return this.poorSchool;
	}

	public void setPoorSchool(String poorSchool) {
		this.poorSchool = poorSchool;
	}

	@Column(name = "poor_province", nullable = false, length = 50)
	public String getPoorProvince() {
		return this.poorProvince;
	}

	public void setPoorProvince(String poorProvince) {
		this.poorProvince = poorProvince;
	}

	@Column(name = "poor_city", nullable = false, length = 50)
	public String getPoorCity() {
		return this.poorCity;
	}

	public void setPoorCity(String poorCity) {
		this.poorCity = poorCity;
	}

	@Column(name = "poor_district", nullable = false, length = 50)
	public String getPoorDistrict() {
		return this.poorDistrict;
	}

	public void setPoorDistrict(String poorDistrict) {
		this.poorDistrict = poorDistrict;
	}

	@Column(name = "poor_detail_address", nullable = false, length = 100)
	public String getPoorDetailAddress() {
		return this.poorDetailAddress;
	}

	public void setPoorDetailAddress(String poorDetailAddress) {
		this.poorDetailAddress = poorDetailAddress;
	}

	@Column(name = "poor_background", nullable = false, length = 1000)
	public String getPoorBackground() {
		return this.poorBackground;
	}

	public void setPoorBackground(String poorBackground) {
		this.poorBackground = poorBackground;
	}

	@Column(name = "poor_state", nullable = false)
	public boolean getPoorState() {
		return this.poorState;
	}

	public void setPoorState(boolean poorState) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}