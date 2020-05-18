package com.blaze.DAO;

import java.util.ArrayList;
import java.util.List;

import com.blaze.Entity.Customer;
import com.blaze.common.Generate;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;

    private final MongoTemplate mongoTemplate;

    public CustomerDAO(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Customer> getCustomers(int page, int size, String sortBy){
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return customerRepository.findAll(pageable);
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.insert(customer);
    
    }

    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomer(String id){
        return customerRepository.findByid(id);
    }

    public void deleteCustomer(String id){
        customerRepository.delete(customerRepository.findByid(id));
    }

    public List<Customer> generateCustomers(Generate generate){
        List<Customer> customers = new ArrayList<>();

        Faker faker = new Faker();
        
        for (int i = 1; i <= generate.getNumber(); ++i) {
            Customer customer = new Customer();
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setPhoneNumber(faker.phoneNumber().cellPhone());
            customer.setEmail(faker.name().firstName() + "@" + faker.name().lastName() + ".com");
            customers.add(customer);
        }
        
        return customerRepository.insert(customers);
    }

    public List<Customer> getCustomerByFirstName(String FirstName){

        Query query = new Query()
                        .addCriteria(Criteria.where("FirstName").regex(".*" + FirstName + ".*", "i"));

        return mongoTemplate.find(query, Customer.class);
        
    }

}
