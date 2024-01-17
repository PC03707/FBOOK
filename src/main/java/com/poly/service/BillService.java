package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Account;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;



public interface BillService {
	List<Bill> findAll();

	Bill update(Bill bill);

	Bill getOne(Long orderId);

	void save(Bill bill);
	
	List<Bill> findByAccount(Account account);
	
}
