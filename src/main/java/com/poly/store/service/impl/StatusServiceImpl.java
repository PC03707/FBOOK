package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.BillDAO;
import com.poly.dao.StatusDAO;
import com.poly.entity.Bill;
import com.poly.entity.Status;
import com.poly.service.BillService;
import com.poly.service.StatusService;
@Service
public class StatusServiceImpl implements StatusService  {
	@Autowired 
	StatusDAO statudao;
	
	
	@Override
	public List<Status> findAll() {	
		return statudao.findAll();
	}


	@Override
	public Status getOne(Integer newStatusId) {
	
		return statudao.getOne(newStatusId);
	}

}
