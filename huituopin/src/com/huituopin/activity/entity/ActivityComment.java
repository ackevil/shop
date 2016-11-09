package com.huituopin.activity.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActivityComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ActivityComment")
public class ActivityComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer actCommentId;
	private Integer activityId;
	private Integer userId;
	private String actComment;
	private Date actCommentIntime;
	private boolean actCommentIsDelete;
	private String actCommentC1;
	private String actCommentC2;
	private Integer commentUserId;
	private String commentUserName;
	private String commentUserAvatar;
	private String userName;
	private String userAvatar;

	// Constructors

	/** default constructor */
	public ActivityComment() {
	}

	/** minimal constructor */
	public ActivityComment(Integer activityId, Integer userId) {
		this.activityId = activityId;
		this.userId = userId;
	}

	/** full constructor */
	public ActivityComment(Integer actCommentId, Integer activityId,
			Integer userId, String actComment, Date actCommentIntime,
			boolean actCommentIsDelete, String actCommentC1,
			String actCommentC2, Integer commentUserId, String commentUserName,
			String commentUserAvatar, String userName, String userAvatar) {
		this.actCommentId = actCommentId;
		this.activityId = activityId;
		this.userId = userId;
		this.actComment = actComment;
		this.actCommentIntime = actCommentIntime;
		this.actCommentIsDelete = actCommentIsDelete;
		this.actCommentC1 = actCommentC1;
		this.actCommentC2 = actCommentC2;
		this.commentUserId = commentUserId;
		this.commentUserName = commentUserName;
		this.commentUserAvatar = commentUserAvatar;
		this.userName = userName;
		this.userAvatar = userAvatar;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "act_comment_id")
	public Integer getActCommentId() {
		return this.actCommentId;
	}

	public void setActCommentId(Integer actCommentId) {
		this.actCommentId = actCommentId;
	}

	@Column(name = "activity_id", nullable = false)
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "act_comment", length = 500)
	public String getActComment() {
		return this.actComment;
	}

	public void setActComment(String actComment) {
		this.actComment = actComment;
	}

	@Column(name = "act_comment_intime", length = 19)
	public Date getActCommentIntime() {
		return this.actCommentIntime;
	}

	public void setActCommentIntime(Date actCommentIntime) {
		this.actCommentIntime = actCommentIntime;
	}

	@Column(name = "act_comment_is_delete")
	public boolean getActCommentIsDelete() {
		return this.actCommentIsDelete;
	}

	public void setActCommentIsDelete(boolean actCommentIsDelete) {
		this.actCommentIsDelete = actCommentIsDelete;
	}

	@Column(name = "act_comment_c1", length = 200)
	public String getActCommentC1() {
		return this.actCommentC1;
	}

	public void setActCommentC1(String actCommentC1) {
		this.actCommentC1 = actCommentC1;
	}

	@Column(name = "act_comment_c2", length = 200)
	public String getActCommentC2() {
		return this.actCommentC2;
	}

	public void setActCommentC2(String actCommentC2) {
		this.actCommentC2 = actCommentC2;
	}

	@Column(name = "comment_user_id")
	public Integer getCommentUserId() {
		return this.commentUserId;
	}

	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId = commentUserId;
	}

	@Column(name = "comment_user_name")
	public String getCommentUserName() {
		return this.commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	@Column(name = "comment_user_avatar")
	public String getCommentUserAvatar() {
		return this.commentUserAvatar;
	}

	public void setCommentUserAvatar(String commentUserAvatar) {
		this.commentUserAvatar = commentUserAvatar;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_avatar")
	public String getUserAvatar() {
		return this.userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

}