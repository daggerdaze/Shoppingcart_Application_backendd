package com.caseStudy.Repository;

import com.caseStudy.Model.Users;
import com.caseStudy.Model.cart;
import com.caseStudy.Model.products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface cartrepository extends JpaRepository<cart, Integer> {
    ArrayList<cart> findAllByUser(Users users);

    void deleteByUserAndItems(Optional<Users> users, Optional<products> products);
}
