package com.huituopin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShippingAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ShippingAddress")
public class ShippingAddress implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer shippingAddId;
	private Integer userId;
	private String shippingAddName;
	private String shippingAddPhone;
	private String shippingAddProvince;
	private String shippingAddCity;
	private String shippingAddDistrict;
	private String shippingAddDetail;
	private boolean shippingAddIsMain;
	private boolean shippingAddIsDelete;
	private String shippingAddC1;
	private String shippingAddC2;

	// Constructors

	/** default constructor */
	public ShippingAddress() {
	}

	/** minimal constructor */
	public ShippingAddress(Integer userId, String shippingAddName,
			String shippingAddPhone, String shippingAddProvince,
			String shippingAddCity, String shippingAddDistrict,
			String shippingAddDetail, boolean shippingAddIsMain,
			boolean shippingAddIsDelete) {
		this.userId = userId;
		this.shippingAddName = shippingAddName;
		this.shippingAddPhone = shippingAddPhone;
		this.shippingAddProvince = shippingAddProvince;
		this.shippingAddCity = shippingAddCity;
		this.shippingAddDistrict = shippingAddDistrict;
		this.shippingAddDetail = shippingAddDetail;
		this.shippingAddIsMain = shippingAddIsMain;
		this.shippingAddIsDelete = shippingAddIsDelete;
	}

	/** full constructor */
	public ShippingAddress(Integer userId, String shippingAddName,
			String shippingAddPhone, String shippingAddProvince,
			String shippingAddCity, String shippingAddDistrict,
			String shippingAddDetail, boolean shippingAddIsMain,
			boolean shippingAddIsDelete, String shippingAddC1,
			String shippingAddC2) {
		this.userId = userId;
		this.shippingAddName = shippingAddName;
		this.shippingAddPhone = shippingAddPhone;
		this.shippingAddProvince = shippingAddProvince;
		this.shippingAddCity = shippingAddCity;
		this.shippingAddDistrict = shippingAddDistrict;
		this.shippingAddDetail = shippingAddDetail;
		this.shippingAddIsMain = shippingAddIsMain;
		this.shippingAddIsDelete = shippingAddIsDelete;
		this.shippingAddC1 = shippingAddC1;
		this.shippingAddC2 = shippingAddC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "shipping_add_id", unique = true, nullable = false)
	public Integer getShippingAddId() {
		return this.shippingAddId;
	}

	public void setShippingAddId(Integer shippingAddId) {
		this.shippingAddId = shippingAddId;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "shipping_add_name", nullable = false, length = 100)
	public String getShippingAddName() {
		return this.shippingAddName;
	}

	public void setShippingAddName(String shippingAddName) {
		this.shippingAddName = shippingAddName;
	}

	@Column(name = "shipping_add_phone", nullable = false, length = 100)
	public String getShippingAddPhone() {
		return this.shippingAddPhone;
	}

	public void setShippingAddPhone(String shippingAddPhone) {
		this.shippingAddPhone = shippingAddPhone;
	}

	@Column(name = "shipping_add_province", nullable = false, length = 100)
	public String getShippingAddProvince() {
		return this.shippingAddProvince;
	}

	public void setShippingAddProvince(String shippingAddProvince) {
		this.shippingAddProvince = shippingAddProvince;
	}

	@Column(name = "shipping_add_city", nullable = false, length = 100)
	public String getShippingAddCity() {
		return this.shippingAddCity;
	}

	public void setShippingAddCity(String shippingAddCity) {
		this.shippingAddCity = shippingAddCity;
	}

	@Column(name = "shipping_add_district", nullable = false, length = 100)
	public String getShippingAddDistrict() {
		return this.shippingAddDistrict;
	}

	public void setShippingAddDistrict(String shippingAddDistrict) {
		this.shippingAddDistrict = shippingAddDistrict;
	}

	@Column(name = "shipping_add_detail", nullable = false, length = 200)
	public String getShippingAddDetail() {
		return this.shippingAddDetail;
	}

	public void setShippingAddDetail(String shippingAddDetail) {
		this.shippingAddDetail = shippingAddDetail;
	}

	@Column(name = "shipping_add_is_main", nullable = false)
	public boolean getShippingAddIsMain() {
		return this.shippingAddIsMain;
	}

	public void setShippingAddIsMain(boolean shippingAddIsMain) {
		this.shippingAddIsMain = shippingAddIsMain;
	}

	@Column(name = "shipping_add_is_delete", nullable = false)
	public boolean getShippingAddIsDelete() {
		return this.shippingAddIsDelete;
	}

	public void setShippingAddIsDelete(boolean shippingAddIsDelete) {
		this.shippingAddIsDelete = shippingAddIsDelete;
	}

	@Column(name = "shipping_add_c1", length = 200)
	public String getShippingAddC1() {
		return this.shippingAddC1;
	}

	public void setShippingAddC1(String shippingAddC1) {
		this.shippingAddC1 = shippingAddC1;
	}

	@Column(name = "shipping_add_c2", length = 200)
	public String getShippingAddC2() {
		return this.shippingAddC2;
	}

	public void setShippingAddC2(String shippingAddC2) {
		this.shippingAddC2 = shippingAddC2;
	}

	@Override
	public String toString() {
		return "ShippingAddress [shippingAddId=" + shippingAddId + ", userId="
				+ userId + ", shippingAddName=" + shippingAddName
				+ ", shippingAddPhone=" + shippingAddPhone
				+ ", shippingAddProvince=" + shippingAddProvince
				+ ", shippingAddCity=" + shippingAddCity
				+ ", shippingAddDistrict=" + shippingAddDistrict
				+ ", shippingAddDetail=" + shippingAddDetail
				+ ", shippingAddIsMain=" + shippingAddIsMain
				+ ", shippingAddIsDelete=" + shippingAddIsDelete
				+ ", shippingAddC1=" + shippingAddC1 + ", shippingAddC2="
				+ shippingAddC2 + "]";
	}

	
	
}