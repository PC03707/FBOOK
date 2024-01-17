package com.poly.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Book;

public interface BookDAO extends JpaRepository<Book, Integer>{	
	@Query("SELECT COUNT(o) FROM Book o")
	Integer countProduct();
	
	@Query("SELECT b FROM Book b WHERE b.category.id=?1")
	public List<Book> findByCategoryId (Integer cid);
}
