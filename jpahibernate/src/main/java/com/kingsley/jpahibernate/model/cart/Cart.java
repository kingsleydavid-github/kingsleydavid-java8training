package com.kingsley.jpahibernate.model.cart;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3055961110871646746L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer cartId;
	
	@Column(name = "cart_name")
	private String cartName;

	@JsonManagedReference
	@OneToMany(mappedBy = "cart",fetch = FetchType.EAGER)
	private Set<Item> items;
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getCartName() {
		return cartName;
	}

	public void setCartName(String cartName) {
		this.cartName = cartName;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((cartName == null) ? 0 : cartName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (cartName == null) {
			if (other.cartName != null)
				return false;
		} else if (!cartName.equals(other.cartName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartName=" + cartName + "]";
	}
	
	/*
	 * 
CREATE TABLE `david`.`cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `cart_name` VARCHAR(200) NULL,
  PRIMARY KEY (`cart_id`));
	 * */
}
