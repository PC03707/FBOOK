package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.Author;
import com.poly.entity.Image;

public interface ImageDAO extends JpaRepository<Image, Integer>{

}
