package com.kingsley.jpahibernate.repo.cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.jpahibernate.model.cart.Cart;

@Repository
public interface CartRepo extends CrudRepository<Cart, Integer> {

}
