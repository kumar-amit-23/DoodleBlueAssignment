package com.doodleblue.service;

import org.springframework.stereotype.Service;

import com.doodleblue.model.Product;

@Service
public interface ProductService {
	Product findById(Integer id) throws Exception;
}
