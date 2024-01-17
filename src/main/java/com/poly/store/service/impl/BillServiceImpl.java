package com.poly.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dao.BillDAO;
import com.poly.entity.Account;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.service.BillService;
@Service
public class BillServiceImpl implements BillService  {
	@Autowired 
	BillDAO billdao;
	
	
	@Override
	public List<Bill> findAll() {	
		return billdao.findAll();
	}


	@Override
	public Bill update(Bill bill) {
	
		return billdao.save(bill);
	}


	@Override
	public Bill getOne(Long orderId) {		
		return billdao.getOne(orderId);
	}


	@Override
	public void save(Bill bill) {
		billdao.save(bill);
		
	}


	@Override
	public List<Bill> findByAccount(Account account) {
		
		return billdao.findByAccount(account);
	}


	


	


	

}
