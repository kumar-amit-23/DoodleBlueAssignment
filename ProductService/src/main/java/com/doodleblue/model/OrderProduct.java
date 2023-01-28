package com.doodleblue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	private Integer userId;

	private Integer quantity;

	private Integer productId;

	public OrderProduct() {

	}

	public OrderProduct(Integer orderId, Integer userId, Integer quantity, Integer productId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		this.productId = productId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderProduct [orderId=" + orderId + ", userId=" + userId + ", quantity=" + quantity + ", productId="
				+ productId + "]";
	}

}
