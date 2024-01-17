package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Bill;
import com.poly.entity.BillDetail;

public interface BillDetailDAO extends JpaRepository<BillDetail, Long>{

	@Query("SELECT od FROM BillDetail od JOIN FETCH od.book p WHERE od.bill.id = :orderId")
	List<BillDetail> findByOrderIdWithProducts(Long orderId);
	
	
	List<BillDetail> findByBill (Bill bill);



}
