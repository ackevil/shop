package com.huituopin.yjya.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Rule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Rule")
public class Rule implements java.io.Serializable {

	// Fields

	private Integer ruleId;
	private String ruleName;
	private Integer getCredictNum;
	private Date ruleIntime;

	// Constructors

	/** default constructor */
	public Rule() {
	}

	/** full constructor */
	public Rule(String ruleName, Integer getCredictNum, Date ruleIntime) {
		this.ruleName = ruleName;
		this.getCredictNum = getCredictNum;
		this.ruleIntime = ruleIntime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rule_id", unique = true, nullable = false)
	public Integer getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	@Column(name = "rule_name", length = 500)
	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Column(name = "get_credict_num")
	public Integer getGetCredictNum() {
		return this.getCredictNum;
	}

	public void setGetCredictNum(Integer getCredictNum) {
		this.getCredictNum = getCredictNum;
	}

	@Column(name = "rule_intime", length = 0)
	public Date getRuleIntime() {
		return this.ruleIntime;
	}

	public void setRuleIntime(Date ruleIntime) {
		this.ruleIntime = ruleIntime;
	}

}