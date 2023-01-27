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
	public OrderProduct orderByUserId(OrderProduct order, Long userId) {
		// TODO Auto-generated method stub

		Product prodObj = this.productRepo.findById(order.getProductId()).get();

		// check for quantity if true save then not.

		OrderProduct orderObj = this.orderRepo.save(order);

		prodObj.setQuanity(prodObj.getQuanity() - order.getQuantity());

		this.productRepo.save(prodObj);

		// check for exception

		return orderObj;
	}

	@Override
	public List<Product> getAllOrderfromUserId(Long userId) {
		// TODO Auto-generated method stub
		List<OrderProduct> orderList = this.orderRepo.findAllByUserId(userId);
		List<Product> productList = new ArrayList<>();
		for (OrderProduct o : orderList) {
			productList.add(this.productRepo.findById(o.getProductId()).get());
		}
		return productList;
	}

}
