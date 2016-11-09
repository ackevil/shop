package com.huituopin.user.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

@Entity(name = "User")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userPhone;
	private String userPwd;
	private String userWcId;
	private String userWcNickname;
	private String userWcAvatar;
	private boolean userWcGender;
	private boolean userType;
	private Date userSigntime;
	private boolean userIsDelete;
	private boolean userIsOnline;
	private String userC1;
	private String userC2;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor 
	 * 没有微信信息
	 * */
	public User(String userPhone, String userPwd, boolean userType) {
		this.userPhone = userPhone;
		this.userPwd = userPwd;
		this.userType = userType;
		this.userSigntime = new Date();
		this.userIsDelete = false;
		this.userIsOnline = false;
	}

	/** full constructor 
	 * 微信用户
	 * */
	public User(String userPhone, String userPwd, String userWcId,
			String userWcNickname, String userWcAvatar, boolean userWcGender,
			boolean userType) {
		this.userPhone = userPhone;
		this.userPwd = userPwd;
		this.userWcId = userWcId;
		this.userWcNickname = userWcNickname;
		this.userWcAvatar = userWcAvatar;
		this.userWcGender = userWcGender;
		this.userType = userType;
		this.userSigntime = new Date();
		this.userIsDelete = false;
		this.userIsOnline = false;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_phone", nullable = false, length = 50)
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "user_pwd", nullable = false, length = 100)
	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Column(name = "user_wc_id", length = 200)
	public String getUserWcId() {
		return this.userWcId;
	}

	public void setUserWcId(String userWcId) {
		this.userWcId = userWcId;
	}

	@Column(name = "user_wc_nickname", length = 200)
	public String getUserWcNickname() {
		return this.userWcNickname;
	}

	public void setUserWcNickname(String userWcNickname) {
		this.userWcNickname = userWcNickname;
	}

	@Column(name = "user_wc_avatar", length = 200)
	public String getUserWcAvatar() {
		return this.userWcAvatar;
	}

	public void setUserWcAvatar(String userWcAvatar) {
		this.userWcAvatar = userWcAvatar;
	}

	@Column(name = "user_wc_gender")
	public boolean getUserWcGender() {
		return this.userWcGender;
	}

	public void setUserWcGender(boolean userWcGender) {
		this.userWcGender = userWcGender;
	}

	@Column(name = "user_type", nullable = false)
	public boolean getUserType() {
		return this.userType;
	}

	public void setUserType(boolean userType) {
		this.userType = userType;
	}

	@Column(name = "user_signtime", nullable = false, length = 0)
	public Date getUserSigntime() {
		return this.userSigntime;
	}

	public void setUserSigntime(Date userSigntime) {
		this.userSigntime = userSigntime;
	}

	@Column(name = "user_is_delete", nullable = false)
	public boolean getUserIsDelete() {
		return this.userIsDelete;
	}

	public void setUserIsDelete(boolean userIsDelete) {
		this.userIsDelete = userIsDelete;
	}

	@Column(name = "user_is_online", nullable = false)
	public boolean getUserIsOnline() {
		return this.userIsOnline;
	}

	public void setUserIsOnline(boolean userIsOnline) {
		this.userIsOnline = userIsOnline;
	}

	@Column(name = "user_c1", length = 200)
	public String getUserC1() {
		return this.userC1;
	}

	public void setUserC1(String userC1) {
		this.userC1 = userC1;
	}

	@Column(name = "user_c2", length = 200)
	public String getUserC2() {
		return this.userC2;
	}

	public void setUserC2(String userC2) {
		this.userC2 = userC2;
	}

}