package com.nghoanghenry.microservices.product.repository;

import com.nghoanghenry.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
