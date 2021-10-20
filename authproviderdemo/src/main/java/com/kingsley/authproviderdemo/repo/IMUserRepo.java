package com.kingsley.authproviderdemo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.authproviderdemo.model.user.IMUser;

@Repository
public interface IMUserRepo extends CrudRepository<IMUser, Integer> {

	List<IMUser> findByEmail(String email);
	
}
