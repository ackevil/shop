package com.huituopin.dsze.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DszeComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DszeComment")
public class DszeComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer dszeCommentId;
	private Integer dszeId;
	private Integer userId;
	private String dszeComment;
	private Date dszeCommentIntime;
	private boolean dszeCommentIsDelete;
	private String dszeCommentC1;
	private String dszeCommentC2;
	private Integer commentUserId;
	private String commentUserName;
	private String commentUserAvatar;
	private String userName;
	private String userAvatar;
	private float dszeSupportMoney;

	// Constructors

	/** default constructor */
	public DszeComment() {
	}

	/** minimal constructor */
	public DszeComment(Integer dszeId, Integer userId, Date dszeCommentIntime) {
		this.dszeId = dszeId;
		this.userId = userId;
		this.dszeCommentIntime = dszeCommentIntime;
	}

	/** full constructor */
	public DszeComment(Integer dszeCommentId, Integer dszeId, Integer userId,
			String dszeComment, Date dszeCommentIntime,
			boolean dszeCommentIsDelete, String dszeCommentC1,
			String dszeCommentC2, Integer commentUserId,
			String commentUserName, String commentUserAvatar, String userName,
			String userAvatar, float dszeSupportMoney) {
		this.dszeCommentId = dszeCommentId;
		this.dszeId = dszeId;
		this.userId = userId;
		this.dszeComment = dszeComment;
		this.dszeCommentIntime = dszeCommentIntime;
		this.dszeCommentIsDelete = dszeCommentIsDelete;
		this.dszeCommentC1 = dszeCommentC1;
		this.dszeCommentC2 = dszeCommentC2;
		this.commentUserId = commentUserId;
		this.commentUserName = commentUserName;
		this.commentUserAvatar = commentUserAvatar;
		this.userName = userName;
		this.userAvatar = userAvatar;
		this.dszeSupportMoney = dszeSupportMoney;
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

	@Column(name = "dsze_comment_id")
	public Integer getDszeCommentId() {
		return this.dszeCommentId;
	}

	public void setDszeCommentId(Integer dszeCommentId) {
		this.dszeCommentId = dszeCommentId;
	}

	@Column(name = "dsze_id", nullable = false)
	public Integer getDszeId() {
		return this.dszeId;
	}

	public void setDszeId(Integer dszeId) {
		this.dszeId = dszeId;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "dsze_comment", length = 500)
	public String getDszeComment() {
		return this.dszeComment;
	}

	public void setDszeComment(String dszeComment) {
		this.dszeComment = dszeComment;
	}

	@Column(name = "dsze_comment_intime", nullable = false, length = 19)
	public Date getDszeCommentIntime() {
		return this.dszeCommentIntime;
	}

	public void setDszeCommentIntime(Date dszeCommentIntime) {
		this.dszeCommentIntime = dszeCommentIntime;
	}

	@Column(name = "dsze_comment_is_delete")
	public boolean getDszeCommentIsDelete() {
		return this.dszeCommentIsDelete;
	}

	public void setDszeCommentIsDelete(boolean dszeCommentIsDelete) {
		this.dszeCommentIsDelete = dszeCommentIsDelete;
	}

	@Column(name = "dsze_comment_c1", length = 200)
	public String getDszeCommentC1() {
		return this.dszeCommentC1;
	}

	public void setDszeCommentC1(String dszeCommentC1) {
		this.dszeCommentC1 = dszeCommentC1;
	}

	@Column(name = "dsze_comment_c2", length = 200)
	public String getDszeCommentC2() {
		return this.dszeCommentC2;
	}

	public void setDszeCommentC2(String dszeCommentC2) {
		this.dszeCommentC2 = dszeCommentC2;
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

	@Column(name = "dsze_support_money", precision = 12, scale = 0)
	public float getDszeSupportMoney() {
		return this.dszeSupportMoney;
	}

	public void setDszeSupportMoney(float dszeSupportMoney) {
		this.dszeSupportMoney = dszeSupportMoney;
	}

}