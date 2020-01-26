package com.caseStudy.Repository;

import com.caseStudy.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface usersrepository extends JpaRepository<Users, Integer> {
    Boolean existsByName(String name);
    Optional<Users> findByName(String username);
}
