package com.poly.service;

import java.util.List;

import com.poly.entity.Bill;
import com.poly.entity.Status;



public interface StatusService {
	List<Status> findAll();

	Status getOne(Integer newStatusId);
}
