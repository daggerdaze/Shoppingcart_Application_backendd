package com.caseStudy.Repository;

import com.caseStudy.Model.products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface productrepository  extends JpaRepository<products,Integer> {
    public List<products> findAllByCategory(String cat);
    public List<products> findAllByCategoryAndUnitPriceBetween(String cat,Double c1,Double c2);
    public List<products> findAllByUnitPriceBetween(Double c1, Double c2);
    public List<products> findAllByCode(String n);
//    public List<products> findAllByBrand(String b);
//    public List<products> findAllByCategoryAndBrand(String cat,String b);
   // public List<products> findByName(String n);

}
