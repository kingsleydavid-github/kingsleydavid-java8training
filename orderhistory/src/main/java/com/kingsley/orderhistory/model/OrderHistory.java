package com.kingsley.orderhistory.model;

import javax.persistence.Entity;

//@Entity
public class OrderHistory {

	private String orderId;
	private String userId;
	private String orderDetails;
	
	public OrderHistory() {}
	
	public OrderHistory(String orderId, String userId, String orderDetails) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDetails = orderDetails;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
}
