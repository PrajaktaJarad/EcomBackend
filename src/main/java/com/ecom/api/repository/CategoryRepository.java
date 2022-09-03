package com.ecom.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.api.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

	
}
