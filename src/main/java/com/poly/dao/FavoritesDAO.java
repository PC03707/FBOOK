package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Favorites;

public interface FavoritesDAO extends JpaRepository<Favorites, Long>{

}
