package com.huituopin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Adminuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Adminuser", uniqueConstraints = @UniqueConstraint(columnNames = "adminuser_name"))
public class Adminuser implements java.io.Serializable {

	// Fields

	private Integer adminuserId; 	//管理员ID
	private String adminuserName;	//管理员登录名
	private String adminuserPwd;	//管理员密码

	// Constructors

	/** default constructor */
	public Adminuser() {
	}

	/** full constructor */
	public Adminuser(String adminuserName, String adminuserPwd) {
		this.adminuserName = adminuserName;
		this.adminuserPwd = adminuserPwd;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "adminuser_id", unique = true, nullable = false)
	public Integer getAdminuserId() {
		return this.adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	@Column(name = "adminuser_name", unique = true, nullable = false, length = 50)
	public String getAdminuserName() {
		return this.adminuserName;
	}

	public void setAdminuserName(String adminuserName) {
		this.adminuserName = adminuserName;
	}

	@Column(name = "adminuser_pwd", nullable = false, length = 100)
	public String getAdminuserPwd() {
		return this.adminuserPwd;
	}

	public void setAdminuserPwd(String adminuserPwd) {
		this.adminuserPwd = adminuserPwd;
	}

}