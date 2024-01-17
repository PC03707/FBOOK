package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Status;

public interface StatusDAO extends JpaRepository<Status, Integer>{

}
