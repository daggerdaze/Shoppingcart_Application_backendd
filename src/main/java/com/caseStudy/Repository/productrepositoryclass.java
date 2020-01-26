package com.caseStudy.Repository;

import com.caseStudy.Model.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
@Component
public class productrepositoryclass {
    @Autowired
    productrepository productrepository;

    public boolean addProduct(products products) {
        try {
            System.out.println("Adding A product");
            productrepository.save(products);
            System.out.println("Product has been Added");
            return true;
        } catch (Exception e) {
            return false;
        }
    }


public ArrayList<products> getAllProducts() {
        return (ArrayList<products>) productrepository.findAll();
    }

public Optional<products> getFeilds(int id) {return productrepository.findById(id);}

public ArrayList<products>  getByCategory(String category) {
        return (ArrayList<products>) productrepository.findAllByCategory(category);
}
public ArrayList<products> getBycategoryAndPrice(String cat,double c1,double c2){
        return (ArrayList<products>)productrepository.findAllByCategoryAndUnitPriceBetween(cat,c1,c2);
}
public Optional<products> getById(int id) { return productrepository.findById(id);}
}




























