package com.kingsley.jpahibernate.repo.cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.jpahibernate.model.cart.Item;

@Repository
public interface ItemRepo extends CrudRepository<Item, Integer> {

}
