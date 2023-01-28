package com.doodleblue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doodleblue.model.Product;
import com.doodleblue.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

//	Get the details of the product from the product Id.
	@GetMapping("/{productId}")
	public ResponseEntity<?> findProductById(@PathVariable Long productId) {

		try {
			Product prod = this.productService.findById(productId);
			return new ResponseEntity<>(prod, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

}
