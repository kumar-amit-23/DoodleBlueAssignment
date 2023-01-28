package com.doodleblue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doodleblue.model.OrderProduct;
import com.doodleblue.model.Product;
import com.doodleblue.repository.OrderRepository;
import com.doodleblue.repository.ProductRepository;
import com.doodleblue.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductRepository productRepo;

	@Override
	@Transactional
	public OrderProduct orderByUserId(OrderProduct order, Long userId) throws Exception {

		Product prodObj = this.productRepo.findById(order.getProductId()).get();

//		check if the product exist in the table or not and if we have enough stock or not
		if (prodObj != null && prodObj.getQuanity() >= order.getQuantity()) {
			OrderProduct orderObj = this.orderRepo.save(order);
			prodObj.setQuanity(prodObj.getQuanity() - order.getQuantity());
			this.productRepo.save(prodObj);
			return orderObj;
		} else {
			throw new Exception("Out of Stock");
		}

	}

	@Override
	public List<Product> getAllOrderfromUserId(Long userId) {

		List<OrderProduct> orderList = this.orderRepo.findAllByUserId(userId);
		List<Product> productList = new ArrayList<>();
		for (OrderProduct o : orderList) {
			productList.add(this.productRepo.findById(o.getProductId()).get());
		}
		return productList;
	}

}
