package com.huituopin.aigou.entity;

import java.io.Serializable;
import java.util.Date;

public class SelectOption implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer payState ;
	
	private Date orderStartTime;
	private Date orderStopTime;
	private Integer orderState;
	private Integer orderType;
	private Integer payWay;
	private String	keyword;
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public Date getOrderStartTime() {
		return orderStartTime;
	}
	public void setOrderStartTime(Date orderStartTime) {
		this.orderStartTime = orderStartTime;
	}
	public Date getOrderStopTime() {
		return orderStopTime;
	}
	public void setOrderStopTime(Date orderStopTime) {
		this.orderStopTime = orderStopTime;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
