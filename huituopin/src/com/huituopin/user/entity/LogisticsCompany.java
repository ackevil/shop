package com.huituopin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LogisticsCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LogisticsCompany")
public class LogisticsCompany implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short logisticsCompanyId;
	private String logisticsCompanyName;
	private boolean logisticsCompanyIsDelete;
	private String logisticsCompanyC1;
	private String logisticsCompanyC2;

	// Constructors

	/** default constructor */
	public LogisticsCompany() {
	}

	/** minimal constructor */
	public LogisticsCompany(String logisticsCompanyName,
			boolean logisticsCompanyIsDelete) {
		this.logisticsCompanyName = logisticsCompanyName;
		this.logisticsCompanyIsDelete = logisticsCompanyIsDelete;
	}

	/** full constructor */
	public LogisticsCompany(String logisticsCompanyName,
			boolean logisticsCompanyIsDelete, String logisticsCompanyC1,
			String logisticsCompanyC2) {
		this.logisticsCompanyName = logisticsCompanyName;
		this.logisticsCompanyIsDelete = logisticsCompanyIsDelete;
		this.logisticsCompanyC1 = logisticsCompanyC1;
		this.logisticsCompanyC2 = logisticsCompanyC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "logistics_company_id", unique = true, nullable = false)
	public short getLogisticsCompanyId() {
		return this.logisticsCompanyId;
	}

	public void setLogisticsCompanyId(short logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	@Column(name = "logistics_company_name", nullable = false, length = 200)
	public String getLogisticsCompanyName() {
		return this.logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	@Column(name = "logistics_company_is_delete", nullable = false)
	public boolean getLogisticsCompanyIsDelete() {
		return this.logisticsCompanyIsDelete;
	}

	public void setLogisticsCompanyIsDelete(boolean logisticsCompanyIsDelete) {
		this.logisticsCompanyIsDelete = logisticsCompanyIsDelete;
	}

	@Column(name = "logistics_company_c1", length = 200)
	public String getLogisticsCompanyC1() {
		return this.logisticsCompanyC1;
	}

	public void setLogisticsCompanyC1(String logisticsCompanyC1) {
		this.logisticsCompanyC1 = logisticsCompanyC1;
	}

	@Column(name = "logistics_company_c2", length = 200)
	public String getLogisticsCompanyC2() {
		return this.logisticsCompanyC2;
	}

	public void setLogisticsCompanyC2(String logisticsCompanyC2) {
		this.logisticsCompanyC2 = logisticsCompanyC2;
	}

}