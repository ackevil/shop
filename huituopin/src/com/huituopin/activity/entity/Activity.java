package com.huituopin.activity.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Activity")
public class Activity implements java.io.Serializable {

	// Fields

	private Integer activityId;
	private String activityC1;
	private String activityC2;
	private String activityDetailInfo;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date activityIntime;
	private short activityIsDelete;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date activityLaunchTime;
	private String activityMainPic;
	private String activityName;
	private Integer activityPayBound;
	private Integer activityRaised;
	private short activityState;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date activityStopTime;
	private String activitySummary;
	private Integer activitySuportNum;
	private Integer activityTotalFund;
	
	private boolean activityIsOrder;
	private Date activityOrderTime;

	// Constructors

	/** default constructor */
	public Activity() {

	}

	/** minimal constructor */
	public Activity(Date activityLaunchTime, String activityName,
			Date activityStopTime, Integer activityTotalFund) {
		this.activityLaunchTime = activityLaunchTime;
		this.activityName = activityName;
		this.activityStopTime = activityStopTime;
		this.activityTotalFund = activityTotalFund;
	}

	/** full constructor */
	public Activity(String activityC1, String activityC2,
			String activityDetailInfo, Date activityIntime,
			short activityIsDelete, Date activityLaunchTime,
			String activityMainPic, String activityName,
			Integer activityPayBound, Integer activityRaised,
			short activityState, Date activityStopTime, String activitySummary,
			Integer activitySuportNum, Integer activityTotalFund) {
		this.activityC1 = activityC1;
		this.activityC2 = activityC2;
		this.activityDetailInfo = activityDetailInfo;
		this.activityIntime = activityIntime;
		this.activityIsDelete = activityIsDelete;
		this.activityLaunchTime = activityLaunchTime;
		this.activityMainPic = activityMainPic;
		this.activityName = activityName;
		this.activityPayBound = activityPayBound;
		this.activityRaised = activityRaised;
		this.activityState = activityState;
		this.activityStopTime = activityStopTime;
		this.activitySummary = activitySummary;
		this.activitySuportNum = activitySuportNum;
		this.activityTotalFund = activityTotalFund;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "activity_id", unique = true, nullable = false)
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Column(name = "activity_c1", length = 200)
	public String getActivityC1() {
		return this.activityC1;
	}

	public void setActivityC1(String activityC1) {
		this.activityC1 = activityC1;
	}

	@Column(name = "activity_c2", length = 200)
	public String getActivityC2() {
		return this.activityC2;
	}

	public void setActivityC2(String activityC2) {
		this.activityC2 = activityC2;
	}

	@Column(name = "activity_detail_info")
	public String getActivityDetailInfo() {
		return this.activityDetailInfo;
	}

	public void setActivityDetailInfo(String activityDetailInfo) {
		this.activityDetailInfo = activityDetailInfo;
	}

	@Column(name = "activity_intime", length = 19)
	public Date getActivityIntime() {
		return this.activityIntime;
	}

	public void setActivityIntime(Date activityIntime) {
		this.activityIntime = activityIntime;
	}

	@Column(name = "activity_is_delete")
	public short getActivityIsDelete() {
		return this.activityIsDelete;
	}

	public void setActivityIsDelete(short activityIsDelete) {
		this.activityIsDelete = activityIsDelete;
	}

	@Column(name = "activity_launch_time", nullable = false, length = 19)
	public Date getActivityLaunchTime() {
		return this.activityLaunchTime;
	}

	public void setActivityLaunchTime(Date activityLaunchTime) {
		this.activityLaunchTime = activityLaunchTime;
	}

	@Column(name = "activity_main_pic", length = 200)
	public String getActivityMainPic() {
		return this.activityMainPic;
	}

	public void setActivityMainPic(String activityMainPic) {
		this.activityMainPic = activityMainPic;
	}

	@Column(name = "activity_name", nullable = false, length = 200)
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Column(name = "activity_pay_bound")
	public Integer getActivityPayBound() {
		return this.activityPayBound;
	}

	public void setActivityPayBound(Integer activityPayBound) {
		this.activityPayBound = activityPayBound;
	}

	@Column(name = "activity_raised")
	public Integer getActivityRaised() {
		return this.activityRaised;
	}

	public void setActivityRaised(Integer activityRaised) {
		this.activityRaised = activityRaised;
	}

	@Column(name = "activity_state")
	public short getActivityState() {
		return this.activityState;
	}

	public void setActivityState(short activityState) {
		this.activityState = activityState;
	}

	@Column(name = "activity_stop_time", nullable = false, length = 19)
	public Date getActivityStopTime() {
		return this.activityStopTime;
	}

	public void setActivityStopTime(Date activityStopTime) {
		this.activityStopTime = activityStopTime;
	}

	@Column(name = "activity_summary")
	public String getActivitySummary() {
		return this.activitySummary;
	}

	public void setActivitySummary(String activitySummary) {
		this.activitySummary = activitySummary;
	}

	@Column(name = "activity_suport_num")
	public Integer getActivitySuportNum() {
		return this.activitySuportNum;
	}

	public void setActivitySuportNum(Integer activitySuportNum) {
		this.activitySuportNum = activitySuportNum;
	}

	@Column(name = "activity_total_fund", nullable = false)
	public Integer getActivityTotalFund() {
		return this.activityTotalFund;
	}

	public void setActivityTotalFund(Integer activityTotalFund) {
		this.activityTotalFund = activityTotalFund;
	}
	
	@Column(name = "activity_is_order")
	public boolean getActivityIsOrder() {
		return activityIsOrder;
	}

	public void setActivityIsOrder(boolean activityIsOrder) {
		this.activityIsOrder = activityIsOrder;
	}
	@Column(name = "activity_order_time")
	public Date getActivityOrderTime() {
		return activityOrderTime;
	}

	public void setActivityOrderTime(Date activityOrderTime) {
		this.activityOrderTime = activityOrderTime;
	}


}