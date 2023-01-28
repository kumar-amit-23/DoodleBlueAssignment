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
	public OrderProduct orderByUserId(OrderProduct order, Integer userId) throws Exception {

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

//	return ALL products associated with orderId
	@Override
	public List<Product> getAllOrderfromUserId(Integer userId) {

		List<OrderProduct> orderList = this.orderRepo.findAllByUserId(userId);
		List<Product> productList = new ArrayList<>();
		for (OrderProduct o : orderList) {
			productList.add(this.productRepo.findById(o.getProductId()).get());
		}
		return productList;
	}

//	Enter Dummy Values into DB.
	@Override
	public void saveDummyValues() {

		List<OrderProduct> list = new ArrayList<>();

		OrderProduct obj1 = new OrderProduct(1, 1, 100, 1);
		OrderProduct obj2 = new OrderProduct(2, 2, 50, 1);
		OrderProduct obj3 = new OrderProduct(3, 3, 40, 2);
		OrderProduct obj4 = new OrderProduct(4, 1, 200, 3);

		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);

		this.orderRepo.saveAll(list);

		List<Product> prodList = new ArrayList<>();

		Product prod1 = new Product(1, "Table", 300);
		Product prod2 = new Product(2, "Chair", 200);
		Product prod3 = new Product(3, "Laptop", 500);

		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);

		this.productRepo.saveAll(prodList);
	}

}
