package com.blaze.DAO;

import java.util.List;

import com.blaze.Entity.Customer;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer findByid(String id);
    List<Customer> findByFirstName(String FirstName);
}
