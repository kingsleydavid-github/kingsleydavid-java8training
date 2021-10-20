package com.kingsley.slearnoauth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingsley.slearnoauth.models.Order;
import com.kingsley.slearnoauth.repo.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo orderRepo;
	
	@Override
	@Transactional
	public Order getOrderById(Integer orderId) {
		return orderRepo.findById(orderId).get();
	}

}
