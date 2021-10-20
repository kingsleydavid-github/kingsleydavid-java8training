package com.mphasis.kingsley.mphasisbookskingsley.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mphasis.kingsley.mphasisbookskingsley.model.Book;

@Repository
public interface BookRepo extends PagingAndSortingRepository<Book, Long>  {
	public List<Book> findAllByTitleLike(String query);
}
