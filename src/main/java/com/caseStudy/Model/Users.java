package com.caseStudy.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotBlank(message = "Please enter the password!")
    private String password;
    @NotBlank(message = "Please enter your name")
    private String name;
    private String role;
    private Integer active;
    private String gender;
    private String email;
    private String address;
    private String mobile;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {return gender; }
    public void setGender(String gender) { this.gender = gender;}

    public String getEmail() {return email; }
    public void setEmail(String email) {this.email = email; }

    public String getAddress() {return address; }
    public void setAddress(String address) {this.address = address;}

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) {this.mobile = mobile;}

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}