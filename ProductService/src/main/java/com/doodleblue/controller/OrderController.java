package com.doodleblue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public OrderProduct orderByUserId(@RequestBody OrderProduct order, @PathVariable Long userId) {
		
		return this.orderService.orderByUserId(order, userId);
		
	}
	
	@GetMapping("/{userId}")
	public List<Product> getAllOrderfromUserId(@PathVariable Long userId){
		return this.orderService.getAllOrderfromUserId(userId);
	}

}
