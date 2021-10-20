package com.kingsley.slearnoauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.slearnoauth.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
