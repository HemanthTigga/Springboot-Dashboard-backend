package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    @CrossOrigin(origins = "http://localhost:5173")
    public Customer addCustomer(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            @RequestParam("email") String email,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        String imgURL = null;
        if(image != null && !image.isEmpty()){
            imgURL = customerService.saveImage(image);
            System.out.println("\n Saved image path: " + imgURL);
        }
        Customer customer = new Customer(id,name,age,email,imgURL);
        return customerService.addCustomer(customer);
    }

    @GetMapping("/getCustomer")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    @PostMapping("/updateCustomer")
    @CrossOrigin(origins = "http://localhost:5173")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/deleteCustomer/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public boolean deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }
}
