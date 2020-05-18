package com.blaze.Services;

import com.blaze.DAO.CustomerDAO;
import com.blaze.Entity.Customer;
import com.blaze.common.Generate;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public Page<Customer> getCustomers(int page, int size, String sortBy){
        return customerDAO.getCustomers(page,size,sortBy);
    }

    public Customer createCustomer(Customer customer){
        return customerDAO.createCustomer(customer);
    
    }

    public Customer updateCustomer(Customer customer){
        return customerDAO.updateCustomer(customer);
    }

    public Customer getCustomer(String id){
        return customerDAO.getCustomer(id);
    }

    public List<Customer> getCustomerByFirstName(String FirstName){
        return customerDAO.getCustomerByFirstName(FirstName);
    }

    public void deleteCustomer(String id){
        customerDAO.deleteCustomer(id);
    
    }
    public List<Customer> generateCustomers(Generate generate){
        return customerDAO.generateCustomers(generate);
    }


}
