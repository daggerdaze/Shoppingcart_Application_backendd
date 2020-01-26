package com.caseStudy.service;


import com.caseStudy.Model.Users;
import com.caseStudy.Repository.usersrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CurrentUserService {
    @Autowired
    usersrepository usersrepository;

    public Users getUser(Principal principal){
        Optional<Users>users = usersrepository.findByName(principal.getName());
        return users.get();
    }
}
