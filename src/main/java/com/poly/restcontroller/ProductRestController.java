package com.poly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dao.BillDAO;
import com.poly.dao.BookDAO;
import com.poly.entity.Bill;
import com.poly.entity.Book;
import com.poly.service.ProductService;

import java.math.BigDecimal;
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BookDAO productDao;
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	BillDAO billDao;
	
	@GetMapping("detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("book", productService.findById(id));
		return "Product/book-page";
	}
	
	@GetMapping("{id}")
	public Book getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
	@GetMapping()
	public List<Book> getAll() {
		return productService.findAll();
	}
	
	//new
	@PostMapping()
	public Book create(@RequestBody Book book) {
		return productService.create(book);
	}
	@PutMapping("{id}")
	public Book update(@PathVariable("id") Integer id, @RequestBody Book book) {
		return productService.update(book);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.delete(id);
	}
	
	
	
	
	//load dữ liệu admin
	@GetMapping("/demslsp")
	public Integer demslsp() {
		return productDao.countProduct();
	}
	@GetMapping("/demslkh")
	public Integer demslkh() {
		return accountDao.countAccount();
	}
	@GetMapping("/demsldh")
	public Integer demsldh() {
		return billDao.countBill();
	}
	
	@GetMapping("/demslcd")
	public Long demslcd() {
		return billDao.countOrdersWithStatus(1);
	}
	//Đơn đăt hàng hôm nay
	@GetMapping("/ddhanghna")
	public List<Bill> ddhanghna() {
		return billDao.findBillsCreatedToday();
	}
	
	@GetMapping("/tongtienhomnay")
	public BigDecimal tongtiendonhang() {
		return billDao.getTotalAmountOfOrdersPlacedToday();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
