package com.caseStudy.Repository;


import com.caseStudy.Model.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ordersrepository extends JpaRepository<orders, Integer> {
    public List<orders> findAllByUserId(int id);
}
