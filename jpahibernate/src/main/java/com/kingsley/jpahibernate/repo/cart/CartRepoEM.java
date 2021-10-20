package com.kingsley.jpahibernate.repo.cart;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kingsley.jpahibernate.model.cart.Cart;

@Transactional
@Repository
public class CartRepoEM {

	@Autowired
	EntityManager em;
	
	public Cart createCart(Cart cart) {
		em.persist(cart);
		return cart;
	}
	
}
