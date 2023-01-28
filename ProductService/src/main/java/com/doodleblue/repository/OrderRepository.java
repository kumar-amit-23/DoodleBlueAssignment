package com.doodleblue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doodleblue.model.OrderProduct;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, Integer> {

	List<OrderProduct> findAllByUserId(Integer userId);

}
