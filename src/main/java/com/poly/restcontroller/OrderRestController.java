package com.poly.restcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.BillDAO;
import com.poly.entity.Account;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.entity.Cart;
import com.poly.entity.CartDetail;
import com.poly.entity.Status;
import com.poly.service.AccountService;
import com.poly.service.BillService;
import com.poly.service.StatusService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {
	@Autowired
	BillService billservice;

	@Autowired
	BillDAO billDao;

	@Autowired
	StatusService statusservice;
	
	@Autowired
	AccountService accountService;
	

	@GetMapping()
	public List<Bill> getAll() {
		return billservice.findAll();
	}
	
	@GetMapping("{id}")
	public List<Bill> getbillid(@PathVariable("id") String id) {
		Account account = accountService.findById(id);
		return billservice.findByAccount(account);
	}
	
	@GetMapping("/index")
	public List<Long> index(Model model) {
		long choXacNhan = billDao.countOrdersWithStatus(1);
		long dangGiaoHang = billDao.countOrdersWithStatus(2);
		long huyDonHang = billDao.countOrdersWithStatus(4);
		
		long daNhanHang = billDao.countOrdersWithStatus(3);

		List<Long> list = new ArrayList<>();
		list.add(choXacNhan);
		list.add(dangGiaoHang);
		list.add(daNhanHang);
		list.add(huyDonHang);
		return list;
	}



	@PutMapping("/{orderId}/status")
	public ResponseEntity<String> changeOrderStatus(@PathVariable Long orderId, @RequestParam Integer newStatusId) {
		Bill bill = billservice.getOne(orderId);
		if (bill == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
		}

		Status newStatus = statusservice.getOne(newStatusId);
		if (newStatus == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid new status");
		}

		bill.setStatus(newStatus);
		billservice.save(bill);

		return ResponseEntity.ok("Order status updated successfully");
	}

}
