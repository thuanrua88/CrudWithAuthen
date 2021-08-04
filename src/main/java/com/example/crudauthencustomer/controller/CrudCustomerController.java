package com.example.crudauthencustomer.controller;

import com.example.crudauthencustomer.Models.Customer;
import com.example.crudauthencustomer.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CrudCustomerController {
    private CustomerService customerService;

    public CrudCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> list() {
        List<Customer> Customers = customerService.listAll();
        return new ResponseEntity<>(Customers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllTodos() {
        List<Customer> todos = customerService.listAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> Create(@RequestBody Customer Customer) {
        Customer theCustomer = customerService.save(Customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("todo", "/api/" + theCustomer.getId().toString());
        return new ResponseEntity<>(theCustomer, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping({"/{CusId}"})
    public ResponseEntity<String> deleteTodo(@PathVariable("CusId") Long CusId) {
        try {
            customerService.deletebyId(CusId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
            catch (Exception e) {
                return new ResponseEntity<>("Not find by id: " + e,HttpStatus.NO_CONTENT);
            }

    }
}
