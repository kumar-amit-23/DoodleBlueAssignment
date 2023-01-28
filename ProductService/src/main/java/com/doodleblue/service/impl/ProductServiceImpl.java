package com.doodleblue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doodleblue.model.Product;
import com.doodleblue.repository.ProductRepository;
import com.doodleblue.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		try {
			return productRepo.findById(id).get();
		} catch (Exception e) {
			throw new Exception("No product Exist");
		}

	}

}
