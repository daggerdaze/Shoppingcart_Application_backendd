package com.caseStudy.Controller;

import com.caseStudy.Exception.ResourceNotFoundException;
import com.caseStudy.Model.products;
import com.caseStudy.Repository.productrepository;
import com.caseStudy.service.CurrentUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders ="*")
public class prductController {

    @Autowired
    productrepository product;
    @Autowired
    CurrentUserService userService;

    @GetMapping("/products")
    public List<products> getAllItems() {
        return product.findAll();
    }
    @PostMapping("/additems")
    public products insert(@Valid @RequestBody products p) {
        return product.saveAndFlush(p);
    }

    @GetMapping("/items/{id}")
    public products getProductById(@PathVariable(value = "id") Integer productId) {
        return product.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }
    @GetMapping("/search/{code}")
    public List<products> getSearch(@PathVariable(value = "code") String name) {
        return product.findAllByCode(name);
    }

    @GetMapping("/category/{type}")
    public List<products> getProductByCategory(@PathVariable(value = "type") String productCategory) {
        return product.findAllByCategory(productCategory);
    }
//    @GetMapping("/{cod}")
//    public List<products> getProductByCode(@PathVariable(value = "cod") String productCode) {
//        return product.findAllByCode(productCode);
//    }

    @GetMapping("/category")
    @ResponseBody
    public List<products> getProduct(){
        return product.findAll();
    }

//    @GetMapping("/{b}")
//    public List<products> getBrand(@PathVariable(value = "b") String b) {
//        return product.findAllByBrand(b);
//    }
//    @GetMapping("/category/brand/{cat}/{b}")
//    public List<products> getCategoryAndBrand(@PathVariable(value = "cat") String cat,@PathVariable(value = "b") String b) {
//        return product.findAllByCategoryAndBrand(cat,b);
//    }


    @GetMapping("/{cat}/{c1}/{c2}")
    public List<products> getCategoryWithPrice(@PathVariable(value = "cat") String cat, @PathVariable(value = "c1") Double c1, @PathVariable(value = "c2") Double c2) {
        return product.findAllByCategoryAndUnitPriceBetween(cat, c1, c2);
    }
@GetMapping("{c1}/{c2}")
    public List<products> getWithPrice(@PathVariable(value = "c1")Double c1,@PathVariable(value = "c2")Double c2){
        return product.findAllByUnitPriceBetween(c1,c2);
}

}
