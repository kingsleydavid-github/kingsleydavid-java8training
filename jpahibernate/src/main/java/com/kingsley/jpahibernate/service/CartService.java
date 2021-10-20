package com.kingsley.jpahibernate.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingsley.jpahibernate.model.cart.Cart;
import com.kingsley.jpahibernate.repo.cart.CartRepo;
import com.kingsley.jpahibernate.repo.cart.CartRepoEM;

@Service
public class CartService {

	@Autowired
	CartRepo cartRepo;

	@Autowired
	CartRepoEM cartRepoEm;
	
	public Optional<Cart> getCart(Integer cartId) {
		return cartRepo.findById(cartId);
	}
	
	public Optional<Cart> createCart(Cart cart){		
		Cart c = cartRepo.save(cart);
		return Optional.of(c);
	}
	
}
