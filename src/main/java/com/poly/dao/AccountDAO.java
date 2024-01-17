package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{
	
	@Query("SELECT COUNT(o) FROM Account o")
	Integer countAccount();

}
