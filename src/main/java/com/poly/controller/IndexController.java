package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.CategoryDAO;
import com.poly.entity.Book;
import com.poly.service.ProductService;


@Controller
public class IndexController {
	@Autowired 
	ProductService productService;
	
	@Autowired 
	CategoryDAO categoryDAo;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "User/index";
	}

	@RequestMapping("/checkout")
	public String checkout(Model model) {
		return "User/checkout";
	}
	@RequestMapping("/oder")
	public String oder(Model model) {
		return "User/oder";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model,@PathVariable("id") Integer id) {
		Book item=productService.findById(id);
		model.addAttribute("item",item);
		return "User/book-page";
	}
	
}
