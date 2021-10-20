package com.kingsley.orderhistory.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.orderhistory.model.auth.IMUser;

@Repository
public interface IMUserRepo extends CrudRepository<IMUser, Integer> {

	List<IMUser> findByEmail(String email);
	
}
