package com.huituopin.dsze.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dsze entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Dsze")
public class Dsze implements java.io.Serializable {

	// Fields

	private Integer dszeId;
	private String dszeC1;
	private String dszeC2;
	private String dszeDetailInfo;
	private Date dszeIntime;
	private short dszeIsDelete;
	private Date dszeLaunchTime;
	private String dszeMainPic;
	private String dszeName;
	private Integer dszePayBound;
	private Integer dszeRaised;
	private short dszeState;
	private Date dszeStopTime;
	private Integer dszeSuportNum;
	private Integer dszeTotalFund;
	private String dszeSummary;
	
	private boolean dszeIsOrder;


	private Date dszeOrderTime;

	// Constructors

	/** default constructor */
	public Dsze() {
	}

	/** minimal constructor */
	public Dsze(Date dszeLaunchTime, String dszeName, short dszeState,
			Date dszeStopTime, Integer dszeTotalFund) {
		this.dszeLaunchTime = dszeLaunchTime;
		this.dszeName = dszeName;
		this.dszeState = dszeState;
		this.dszeStopTime = dszeStopTime;
		this.dszeTotalFund = dszeTotalFund;
	}

	/** full constructor */
	public Dsze(String dszeC1, String dszeC2, String dszeDetailInfo,
			Date dszeIntime, short dszeIsDelete, Date dszeLaunchTime,
			String dszeMainPic, String dszeName, Integer dszePayBound,
			Integer dszeRaised, short dszeState, Date dszeStopTime,
			Integer dszeSuportNum, Integer dszeTotalFund, String dszeSummary) {
		this.dszeC1 = dszeC1;
		this.dszeC2 = dszeC2;
		this.dszeDetailInfo = dszeDetailInfo;
		this.dszeIntime = dszeIntime;
		this.dszeIsDelete = dszeIsDelete;
		this.dszeLaunchTime = dszeLaunchTime;
		this.dszeMainPic = dszeMainPic;
		this.dszeName = dszeName;
		this.dszePayBound = dszePayBound;
		this.dszeRaised = dszeRaised;
		this.dszeState = dszeState;
		this.dszeStopTime = dszeStopTime;
		this.dszeSuportNum = dszeSuportNum;
		this.dszeTotalFund = dszeTotalFund;
		this.dszeSummary = dszeSummary;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dsze_id", unique = true, nullable = false)
	public Integer getDszeId() {
		return this.dszeId;
	}

	public void setDszeId(Integer dszeId) {
		this.dszeId = dszeId;
	}

	@Column(name = "dsze_c1", length = 200)
	public String getDszeC1() {
		return this.dszeC1;
	}

	public void setDszeC1(String dszeC1) {
		this.dszeC1 = dszeC1;
	}

	@Column(name = "dsze_c2", length = 200)
	public String getDszeC2() {
		return this.dszeC2;
	}

	public void setDszeC2(String dszeC2) {
		this.dszeC2 = dszeC2;
	}

	@Column(name = "dsze_detail_info")
	public String getDszeDetailInfo() {
		return this.dszeDetailInfo;
	}

	public void setDszeDetailInfo(String dszeDetailInfo) {
		this.dszeDetailInfo = dszeDetailInfo;
	}

	@Column(name = "dsze_intime", length = 19)
	public Date getDszeIntime() {
		return this.dszeIntime;
	}

	public void setDszeIntime(Date dszeIntime) {
		this.dszeIntime = dszeIntime;
	}

	@Column(name = "dsze_is_delete")
	public short getDszeIsDelete() {
		return this.dszeIsDelete;
	}

	public void setDszeIsDelete(short dszeIsDelete) {
		this.dszeIsDelete = dszeIsDelete;
	}

	@Column(name = "dsze_launch_time", nullable = false, length = 19)
	public Date getDszeLaunchTime() {
		return this.dszeLaunchTime;
	}

	public void setDszeLaunchTime(Date dszeLaunchTime) {
		this.dszeLaunchTime = dszeLaunchTime;
	}

	@Column(name = "dsze_main_pic", length = 200)
	public String getDszeMainPic() {
		return this.dszeMainPic;
	}

	public void setDszeMainPic(String dszeMainPic) {
		this.dszeMainPic = dszeMainPic;
	}

	@Column(name = "dsze_name", nullable = false, length = 200)
	public String getDszeName() {
		return this.dszeName;
	}

	public void setDszeName(String dszeName) {
		this.dszeName = dszeName;
	}

	@Column(name = "dsze_pay_bound")
	public Integer getDszePayBound() {
		return this.dszePayBound;
	}

	public void setDszePayBound(Integer dszePayBound) {
		this.dszePayBound = dszePayBound;
	}

	@Column(name = "dsze_raised")
	public Integer getDszeRaised() {
		return this.dszeRaised;
	}

	public void setDszeRaised(Integer dszeRaised) {
		this.dszeRaised = dszeRaised;
	}

	@Column(name = "dsze_state", nullable = false)
	public short getDszeState() {
		return this.dszeState;
	}

	public void setDszeState(short dszeState) {
		this.dszeState = dszeState;
	}

	@Column(name = "dsze_stop_time", nullable = false, length = 19)
	public Date getDszeStopTime() {
		return this.dszeStopTime;
	}

	public void setDszeStopTime(Date dszeStopTime) {
		this.dszeStopTime = dszeStopTime;
	}

	@Column(name = "dsze_suport_num")
	public Integer getDszeSuportNum() {
		return this.dszeSuportNum;
	}

	public void setDszeSuportNum(Integer dszeSuportNum) {
		this.dszeSuportNum = dszeSuportNum;
	}

	@Column(name = "dsze_total_fund", nullable = false)
	public Integer getDszeTotalFund() {
		return this.dszeTotalFund;
	}

	public void setDszeTotalFund(Integer dszeTotalFund) {
		this.dszeTotalFund = dszeTotalFund;
	}

	@Column(name = "dsze_summary")
	public String getDszeSummary() {
		return this.dszeSummary;
	}

	public void setDszeSummary(String dszeSummary) {
		this.dszeSummary = dszeSummary;
	}
	
	@Column(name = "dsze_is_order")
	public boolean getDszeIsOrder() {
		return dszeIsOrder;
	}

	public void setDszeIsOrder(boolean dszeIsOrder) {
		this.dszeIsOrder = dszeIsOrder;
	}
	@Column(name = "dsze_order_time")
	public Date getDszeOrderTime() {
		return dszeOrderTime;
	}

	public void setDszeOrderTime(Date dszeOrderTime) {
		this.dszeOrderTime = dszeOrderTime;
	}

}