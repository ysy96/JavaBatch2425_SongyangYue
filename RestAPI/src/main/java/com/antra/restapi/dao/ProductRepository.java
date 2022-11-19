package com.antra.restapi.dao;

import com.antra.restapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

    Product findByName(String name);
}
