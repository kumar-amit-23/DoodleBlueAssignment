package com.doodleblue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doodleblue.model.OrderProduct;
import com.doodleblue.model.Product;

@Service
public interface OrderService {

	OrderProduct orderByUserId(OrderProduct order, Long userId) throws Exception;

	List<Product> getAllOrderfromUserId(Long userId);

}
