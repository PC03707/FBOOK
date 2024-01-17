package com.poly.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Bill;
import java.math.BigDecimal;

public interface BillDAO extends JpaRepository<Bill, Long> {
	@Query("SELECT COUNT(o) FROM Bill o")
	Integer countBill();

	@Query("SELECT COUNT(o) FROM Bill o WHERE o.status.id = ?1")
	long countOrdersWithStatus(Integer id);

	@Query("SELECT b FROM Bill b WHERE CAST(b.createDate AS date) = CAST(GETDATE() AS date)")
	List<Bill> findBillsCreatedToday();

	@Query("SELECT SUM(b.billtotal) FROM Bill b WHERE CAST(b.createDate AS date) = CAST(GETDATE() AS date)")
	BigDecimal getTotalAmountOfOrdersPlacedToday();

	List<Bill> findByAccount(Account account);

}
