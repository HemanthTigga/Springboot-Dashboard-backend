package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

//    add Customer details
    public Customer addCustomer(Customer customer) {
        System.out.println("New customer details added");
        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getAge());
        System.out.println(customer.getEmail());
        System.out.println(customer.getImgURL());
        return customerRepo.save(customer);
    }
    
    public String saveImage(MultipartFile image){
        try{
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if(!Files.exists(uploadPath)){
                Files.createDirectory(uploadPath);
                System.out.println("Directory created"+uploadPath.toAbsolutePath());
            }else{
                System.out.println("Directory already exists"+uploadPath.toAbsolutePath());
            }
//            saving file
            String originalFile = StringUtils.cleanPath(image.getOriginalFilename());
            String fileName = System.currentTimeMillis() + "_" + originalFile;
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(image.getInputStream(),filePath);
            
            return "/images/" + fileName;
        } catch (IOException e){
            throw new RuntimeException("Failed to save image", e);
        }
    }

//    show Customer details
    public List<Customer> getCustomer(){
        return customerRepo.findAll();
    }

//    Update Customer details
    public Customer updateCustomer(Customer updatedcustomer){
        Optional<Customer> ct = customerRepo.findById(updatedcustomer.getId());
        Customer oldcustomer = ct.get();

        System.out.println("Old Details");
        System.out.println(oldcustomer.getId());
        System.out.println(oldcustomer.getName());
        System.out.println(oldcustomer.getAge());
        System.out.println(oldcustomer.getEmail());
//        System.out.println(oldcustomer.getImgURL()+"\n");

//        updating customer details
        oldcustomer.setAge(updatedcustomer.getAge());
        oldcustomer.setEmail(updatedcustomer.getEmail());
        oldcustomer.setName(updatedcustomer.getName());
//        oldcustomer.setImgURL(updatedcustomer.getImgURL());

        System.out.println("New Details");
        System.out.println(oldcustomer.getId());
        System.out.println(oldcustomer.getName());
        System.out.println(oldcustomer.getAge());
        System.out.println(oldcustomer.getEmail());
//        System.out.println(oldcustomer.getImgURL());
        return customerRepo.save(oldcustomer);
    }

//    delete Customer details

    public boolean deleteCustomer(int id){
        customerRepo.deleteById(id);
        System.out.println("Customer details deleted");
        return true;
    }
}
