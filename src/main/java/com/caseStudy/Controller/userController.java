package com.caseStudy.Controller;

import com.caseStudy.Model.Users;
import com.caseStudy.Repository.usersrepository;
import com.caseStudy.service.CurrentUserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/Users")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders ="*")

public class userController {
    @Autowired
    usersrepository usersrepository;
    private Users usersuse;

    @Autowired
    CurrentUserService currentUserService;

    @PostMapping("/addusers")
    public Users addusers(@Valid @RequestBody Users usersuse) {
        usersuse.setActive(1);
        usersuse.setRole("Customer");
        usersrepository.save(usersuse);
        return usersuse;
    }

    @GetMapping("/getUsers")
    public List<Users> getUsers() {
        return usersrepository.findAll();
    }

    @GetMapping("/validate")
    public String valUser() {
        return "\"You are valid authenticated User\"";
    }

    @GetMapping("/logUser")
    public Users logUser(Principal principal) {
        return currentUserService.getUser(principal);
    }

    @PutMapping("/update")
    public Users update(@Valid @RequestBody Users users){
        users.setActive(1);
        users.setRole("Customer");
        usersrepository.save(users);
        return users;
    }

}