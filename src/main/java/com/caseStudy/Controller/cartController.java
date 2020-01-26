package com.caseStudy.Controller;

import com.caseStudy.Model.cart;
import com.caseStudy.Model.orders;
import com.caseStudy.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")
public class cartController {

    @Autowired
    cartService cartService;

    @GetMapping(path="/showcart")
    public ArrayList<cart> getCart(Principal principal){
        return cartService.getUsername(principal);
    }

    @GetMapping(path="/additem/{pid}")
    public String addItemToCart(@PathVariable("pid") int pid,Principal principal){
    return cartService.addItemToCart(principal,pid);
    }

    @GetMapping(path="/deleteitem/{pid}")
    public String deleteItemFromCart(@PathVariable("pid") int pid,Principal principal){
        return cartService.deleteItemFromCart(pid,principal);
    }

    @GetMapping(path="/increment/{value}/{pid}")
    public String increment(@PathVariable("value") int value, @PathVariable("pid") int pid, Principal principal){
        return cartService.increment(value,pid,principal);
    }

    @GetMapping(path="/decrement/{value}/{pid}")
    public String decrement(@PathVariable("value") int value, @PathVariable("pid") int pid, Principal principal){
        return cartService.decrement(value,pid,principal);
    }

    @GetMapping(value="/checkout",produces = "application/json")
    public List<orders> checkoutFromCart(Principal principal){
    return cartService.checkOut(principal);
    }

    @GetMapping(value = "/orderhist", produces = "application/json")
    public List<orders> ohhist(Principal principal) {

        return cartService.orderhist(principal);
    }
}
