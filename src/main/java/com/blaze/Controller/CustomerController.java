package com.blaze.Controller;

import java.util.List;
import java.util.Optional;

import com.blaze.Entity.Customer;
import com.blaze.Services.CustomerService;
import com.blaze.common.Generate;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Page<Customer> getCustomers(@RequestParam(name = "page", defaultValue = "120000") int page,@RequestParam(name = "size", defaultValue = "10") int size,
    @RequestParam(name = "sortBy",defaultValue = "id") String sortBy) {
        return customerService.getCustomers(page,size,sortBy);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @GetMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable("id") String id){
        return customerService.getCustomer(id);
    }

    @GetMapping(value = "/search")
    public List<Customer> getCustomerByFirstName(@RequestParam(name = "FirstName") String FirstName){
        return customerService.getCustomerByFirstName(FirstName);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping(value = "/generate")
    public List<Customer> generateCustomers(@RequestBody Generate generate){
        return customerService.generateCustomers(generate);
    }


}
