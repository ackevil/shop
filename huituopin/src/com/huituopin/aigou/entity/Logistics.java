package com.huituopin.aigou.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Logistics entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Logistics")
public class Logistics implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer logisticsId;
	private String logisticsName;
	private String logisticsDatatime;
	private String logisticsAreas;
	private Integer logisticsFirstcount;
	private Double logisticsFirstprice;
	private Integer logisticsLastconut;
	private Double logisticsLastprice;
	private Boolean logisticsIsDelete;
	private String logisticsC1;
	private String logisticsC2;

	// Constructors

	/** default constructor */
	public Logistics() {
		this.logisticsIsDelete=false;
	}

	/** full constructor */
	public Logistics(String logisticsName, String logisticsDatatime,
			String logisticsAreas, Integer logisticsFirstcount,
			Double logisticsFirstprice, Integer logisticsLastconut,
			Double logisticsLastprice, Boolean logisticsIsDelete,
			String logisticsC1, String logisticsC2) {
		this.logisticsName = logisticsName;
		this.logisticsDatatime = logisticsDatatime;
		this.logisticsAreas = logisticsAreas;
		this.logisticsFirstcount = logisticsFirstcount;
		this.logisticsFirstprice = logisticsFirstprice;
		this.logisticsLastconut = logisticsLastconut;
		this.logisticsLastprice = logisticsLastprice;
		this.logisticsIsDelete = logisticsIsDelete;
		this.logisticsC1 = logisticsC1;
		this.logisticsC2 = logisticsC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "logistics_id", unique = true, nullable = false)
	public Integer getLogisticsId() {
		return this.logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	@Column(name = "logistics_name", length = 50)
	public String getLogisticsName() {
		return this.logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	@Column(name = "logistics_datatime", length = 50)
	public String getLogisticsDatatime() {
		return this.logisticsDatatime;
	}

	public void setLogisticsDatatime(String logisticsDatatime) {
		this.logisticsDatatime = logisticsDatatime;
	}

	@Column(name = "logistics_areas", length = 200)
	public String getLogisticsAreas() {
		return this.logisticsAreas;
	}

	public void setLogisticsAreas(String logisticsAreas) {
		this.logisticsAreas = logisticsAreas;
	}

	@Column(name = "logistics_firstcount")
	public Integer getLogisticsFirstcount() {
		return this.logisticsFirstcount;
	}

	public void setLogisticsFirstcount(Integer logisticsFirstcount) {
		this.logisticsFirstcount = logisticsFirstcount;
	}

	@Column(name = "logistics_firstprice", precision = 22, scale = 0)
	public Double getLogisticsFirstprice() {
		return this.logisticsFirstprice;
	}

	public void setLogisticsFirstprice(Double logisticsFirstprice) {
		this.logisticsFirstprice = logisticsFirstprice;
	}

	@Column(name = "logistics_lastconut")
	public Integer getLogisticsLastconut() {
		return this.logisticsLastconut;
	}

	public void setLogisticsLastconut(Integer logisticsLastconut) {
		this.logisticsLastconut = logisticsLastconut;
	}

	@Column(name = "logistics_lastprice", precision = 22, scale = 0)
	public Double getLogisticsLastprice() {
		return this.logisticsLastprice;
	}

	public void setLogisticsLastprice(Double logisticsLastprice) {
		this.logisticsLastprice = logisticsLastprice;
	}

	@Column(name = "logistics_is_delete")
	public Boolean getLogisticsIsDelete() {
		return this.logisticsIsDelete;
	}

	public void setLogisticsIsDelete(Boolean logisticsIsDelete) {
		this.logisticsIsDelete = logisticsIsDelete;
	}

	@Column(name = "logistics_c1", length = 200)
	public String getLogisticsC1() {
		return this.logisticsC1;
	}

	public void setLogisticsC1(String logisticsC1) {
		this.logisticsC1 = logisticsC1;
	}

	@Column(name = "logistics_c2", length = 200)
	public String getLogisticsC2() {
		return this.logisticsC2;
	}

	public void setLogisticsC2(String logisticsC2) {
		this.logisticsC2 = logisticsC2;
	}

}