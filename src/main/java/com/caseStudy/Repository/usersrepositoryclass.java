package com.caseStudy.Repository;


import com.caseStudy.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class usersrepositoryclass {

    @Autowired
    usersrepository usersrepository;

    public void add(Users users){
        System.out.println("Adding a value");
        usersrepository.save(users);
    }

    public Optional<Users> getByUsername(String username) {
        System.out.println("getting bu username");
        return usersrepository.findByName(username);
    }

    public Optional<Users> getById(int id) {
        return  usersrepository.findById(id);
    }
}
