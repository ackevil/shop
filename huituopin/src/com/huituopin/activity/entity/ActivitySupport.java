package com.huituopin.activity.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActivitySupport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ActivitySupport")
public class ActivitySupport implements java.io.Serializable {

	// Fields

	private Integer activitySupId;
	private Integer activityId;
	private String activityName;
	private Integer userId;
	private Integer actSupMoney;
	private String actSupComment;
	private Date actSupTime;
	private Integer actSupIsDel;
	private String actSupOrder;
	private String actSupC1;
	private String actSupC2;

	// Constructors

	/** default constructor */
	public ActivitySupport() {
	}

	/** minimal constructor */
	public ActivitySupport(Integer activityId, Integer userId,
			Integer actSupMoney, String actSupOrder) {
		this.activityId = activityId;
		this.userId = userId;
		this.actSupMoney = actSupMoney;
		this.actSupOrder = actSupOrder;
	}

	/** full constructor */
	public ActivitySupport(Integer activityId, String activityName,
			Integer userId, Integer actSupMoney, String actSupComment,
			Date actSupTime, Integer actSupIsDel, String actSupOrder,
			String actSupC1, String actSupC2) {
		this.activityId = activityId;
		this.activityName = activityName;
		this.userId = userId;
		this.actSupMoney = actSupMoney;
		this.actSupComment = actSupComment;
		this.actSupTime = actSupTime;
		this.actSupIsDel = actSupIsDel;
		this.actSupOrder = actSupOrder;
		this.actSupC1 = actSupC1;
		this.actSupC2 = actSupC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "activity_sup_id", unique = true, nullable = false)
	public Integer getActivitySupId() {
		return this.activitySupId;
	}

	public void setActivitySupId(Integer activitySupId) {
		this.activitySupId = activitySupId;
	}

	@Column(name = "activity_id", nullable = false)
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Column(name = "activity_name")
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "act_sup_money", nullable = false)
	public Integer getActSupMoney() {
		return this.actSupMoney;
	}

	public void setActSupMoney(Integer actSupMoney) {
		this.actSupMoney = actSupMoney;
	}

	@Column(name = "act_sup_comment", length = 500)
	public String getActSupComment() {
		return this.actSupComment;
	}

	public void setActSupComment(String actSupComment) {
		this.actSupComment = actSupComment;
	}

	@Column(name = "act_sup_time", length = 19)
	public Date getActSupTime() {
		return this.actSupTime;
	}

	public void setActSupTime(Date actSupTime) {
		this.actSupTime = actSupTime;
	}

	@Column(name = "act_sup_is_del")
	public Integer getActSupIsDel() {
		return this.actSupIsDel;
	}

	public void setActSupIsDel(Integer actSupIsDel) {
		this.actSupIsDel = actSupIsDel;
	}

	@Column(name = "act_sup_order", nullable = false)
	public String getActSupOrder() {
		return this.actSupOrder;
	}

	public void setActSupOrder(String actSupOrder) {
		this.actSupOrder = actSupOrder;
	}

	@Column(name = "act_sup_c1", length = 200)
	public String getActSupC1() {
		return this.actSupC1;
	}

	public void setActSupC1(String actSupC1) {
		this.actSupC1 = actSupC1;
	}

	@Column(name = "act_sup_c2", length = 200)
	public String getActSupC2() {
		return this.actSupC2;
	}

	public void setActSupC2(String actSupC2) {
		this.actSupC2 = actSupC2;
	}

}