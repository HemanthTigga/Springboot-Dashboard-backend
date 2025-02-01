package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    private int id;          //always unique
    private String name;
    private int age;
    private String email;
    private String image;

    public Customer(int id, String name, int age, String email, String image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.image = image;
    }

    public Customer(){

    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgURL() {
        return image;
    }

    public void setImgURL(String image) {
        this.image = image;
    }
}
