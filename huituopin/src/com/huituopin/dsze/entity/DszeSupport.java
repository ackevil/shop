package com.huituopin.dsze.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DszeSupport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DszeSupport")
public class DszeSupport implements java.io.Serializable {

	// Fields

	private Integer dszeSupId;
	private Integer dszeId;
	private String dszeName;
	private Integer userId;
	private Integer dszeSupMoney;
	private String dszeSupComment;
	private Date dszeSupTime;
	private Integer dszeSupIsDel;
	private String dszeSupOrder;
	private String dszeSupC1;
	private String dszeSupC2;

	// Constructors

	/** default constructor */
	public DszeSupport() {
		this.dszeSupIsDel=0;
		this.dszeSupTime=new Date();
	}

	/** minimal constructor */
	public DszeSupport(Integer dszeId, Integer userId, Integer dszeSupMoney,
			Integer dszeSupIsDel, String dszeSupOrder) {
		this.dszeId = dszeId;
		this.userId = userId;
		this.dszeSupMoney = dszeSupMoney;
		this.dszeSupIsDel = dszeSupIsDel;
		this.dszeSupOrder = dszeSupOrder;
	}

	/** full constructor */
	public DszeSupport(Integer dszeId, String dszeName, Integer userId,
			Integer dszeSupMoney, String dszeSupComment, Date dszeSupTime,
			Integer dszeSupIsDel, String dszeSupOrder, String dszeSupC1,
			String dszeSupC2) {
		this.dszeId = dszeId;
		this.dszeName = dszeName;
		this.userId = userId;
		this.dszeSupMoney = dszeSupMoney;
		this.dszeSupComment = dszeSupComment;
		this.dszeSupTime = dszeSupTime;
		this.dszeSupIsDel = dszeSupIsDel;
		this.dszeSupOrder = dszeSupOrder;
		this.dszeSupC1 = dszeSupC1;
		this.dszeSupC2 = dszeSupC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dsze_sup_id", unique = true, nullable = false)
	public Integer getDszeSupId() {
		return this.dszeSupId;
	}

	public void setDszeSupId(Integer dszeSupId) {
		this.dszeSupId = dszeSupId;
	}

	@Column(name = "dsze_id", nullable = false)
	public Integer getDszeId() {
		return this.dszeId;
	}

	public void setDszeId(Integer dszeId) {
		this.dszeId = dszeId;
	}

	@Column(name = "dsze_name")
	public String getDszeName() {
		return this.dszeName;
	}

	public void setDszeName(String dszeName) {
		this.dszeName = dszeName;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "dsze_sup_money", nullable = false)
	public Integer getDszeSupMoney() {
		return this.dszeSupMoney;
	}

	public void setDszeSupMoney(Integer dszeSupMoney) {
		this.dszeSupMoney = dszeSupMoney;
	}

	@Column(name = "dsze_sup_comment", length = 500)
	public String getDszeSupComment() {
		return this.dszeSupComment;
	}

	public void setDszeSupComment(String dszeSupComment) {
		this.dszeSupComment = dszeSupComment;
	}

	@Column(name = "dsze_sup_time", length = 19)
	public Date getDszeSupTime() {
		return this.dszeSupTime;
	}

	public void setDszeSupTime(Date dszeSupTime) {
		this.dszeSupTime = dszeSupTime;
	}

	@Column(name = "dsze_sup_is_del", nullable = false)
	public Integer getDszeSupIsDel() {
		return this.dszeSupIsDel;
	}

	public void setDszeSupIsDel(Integer dszeSupIsDel) {
		this.dszeSupIsDel = dszeSupIsDel;
	}

	@Column(name = "dsze_sup_order", nullable = false)
	public String getDszeSupOrder() {
		return this.dszeSupOrder;
	}

	public void setDszeSupOrder(String dszeSupOrder) {
		this.dszeSupOrder = dszeSupOrder;
	}

	@Column(name = "dsze_sup_c1", length = 200)
	public String getDszeSupC1() {
		return this.dszeSupC1;
	}

	public void setDszeSupC1(String dszeSupC1) {
		this.dszeSupC1 = dszeSupC1;
	}

	@Column(name = "dsze_sup_c2", length = 200)
	public String getDszeSupC2() {
		return this.dszeSupC2;
	}

	public void setDszeSupC2(String dszeSupC2) {
		this.dszeSupC2 = dszeSupC2;
	}

}