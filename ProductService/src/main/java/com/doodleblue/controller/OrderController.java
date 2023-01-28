package com.doodleblue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doodleblue.model.OrderProduct;
import com.doodleblue.model.Product;
import com.doodleblue.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

//	order by order id	
	@PostMapping("/{userId}")
	public ResponseEntity<?> orderByUserId(@RequestBody OrderProduct order, @PathVariable Long userId) {
		try {
			OrderProduct orderObj = this.orderService.orderByUserId(order, userId);
			return new ResponseEntity<>(orderObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

//	Fetch product details from the product table based on UserId inserted into the order table.
	@GetMapping("/{userId}")
	public List<Product> getAllOrderfromUserId(@PathVariable Long userId) {
		return this.orderService.getAllOrderfromUserId(userId);
	}

}
