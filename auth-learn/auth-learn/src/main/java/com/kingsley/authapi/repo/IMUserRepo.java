package com.kingsley.authapi.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.authapi.model.users.IMUser;

@Repository
public interface IMUserRepo extends CrudRepository<IMUser, Integer> {

	List<IMUser> findByEmail(String email);
	
}
