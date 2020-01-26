package com.caseStudy.service;

import com.caseStudy.Model.Users;
import com.caseStudy.Model.cart;
import com.caseStudy.Model.orders;
import com.caseStudy.Model.products;
import com.caseStudy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class cartService {

    @Autowired
    usersrepositoryclass usersrepositoryclass;
    @Autowired
    cartrepository cartrepository;
    @Autowired
    productrepository productrepository;
    @Autowired
    usersrepository usersrepository;
    @Autowired
    productrepositoryclass productrepositoryclass;
    @Autowired
    ordersrepository ordersrepository;

    private ArrayList<cart> getCartFromCurrentuser(Principal principal) {
        Users users = usersrepositoryclass.getByUsername(principal.getName()).get();
        ArrayList<cart> cart = cartrepository.findAllByUser(users);
        return cart;
    }

    public ArrayList<cart> getUsername(Principal principal) {
        String username = principal.getName();
        Users users = usersrepositoryclass.getByUsername(username).get();
        return cartrepository.findAllByUser(users);
    }

    public String addItemToCart(Principal principal, int id) {
        Optional<products> items = productrepositoryclass.getById(id);
        Optional<Users> users = usersrepositoryclass.getByUsername(principal.getName());
        ArrayList<cart> car = getCartFromCurrentuser(principal);

        for (int i = 0; i < car.size(); i++) {
            cart cartObject = car.get(i);

            if (cartObject.getItems().equals(items.get())) {
                cartObject.setQuantity(cartObject.getQuantity() + 1);

                cartrepository.save(cartObject);
                return "\"Quantity Increased\"";

            }
        }

        cart cartObject = new cart();
        cartObject.setQuantity(1);
        cartObject.setItems(items.get());
        cartObject.setUser(users.get());
        cartrepository.save(cartObject);
        return "\"items added to cart\"";
    }

    @Transactional
    public String deleteItemFromCart(int itemid, Principal principal) {
        Optional<products> items = productrepositoryclass.getById(itemid);
        Optional<Users> users = usersrepositoryclass.getByUsername(principal.getName());

        cartrepository.deleteByUserAndItems(users, items);

        return "\"deletion Competed\"";
    }

    public String increment(int value, int itemid, Principal principal) {
        ArrayList<cart> cart = getCartFromCurrentuser(principal);
        Optional<products> items = productrepositoryclass.getById(itemid);

        for (int i = 0; i < cart.size(); i++) {
            cart cartObject = cart.get(i);

            if (cartObject.getItems() == items.get()) {
                cartObject.setQuantity(cartObject.getQuantity() + value);
                cartrepository.save(cartObject);

                return "\"Successful\"";
            }
        }
        return "\"Unsuccessful\"";
    }

    public String decrement(int value, int itemid, Principal principal) {
        ArrayList<cart> cart = getCartFromCurrentuser(principal);
        Optional<products> items = productrepositoryclass.getById(itemid);

        for (int i = 0; i < cart.size(); i++) {
            cart cartObject = cart.get(i);

            if (cartObject.getItems().equals(items.get())) {
                int x = cartObject.getQuantity() - value;
                if (x == 1) {
                    cartObject.setQuantity(1);
                    cartrepository.save(cartObject);
                    return "\"Successful\"";
                } else if (x > 1) {
                    cartObject.setQuantity(x);
                    cartrepository.save(cartObject);
                    return "\"Successful\"";
                }
            }
        }
        return "\"Unsuccessful\"";
    }

    public List<orders> checkOut(Principal principal) {
        Users users = usersrepositoryclass.getByUsername(principal.getName()).get();
        ArrayList<cart> cartList = cartrepository.findAllByUser(users);

        for (int i = 0; i < cartList.size(); i++) {
            cart cartObject = cartList.get(i);
            orders order = new orders();
            order.setUserId(cartObject.getUser().getUserId());
            order.setQuantity(cartObject.getQuantity());
            order.setPrice(cartObject.getItems().getUnitPrice());
            order.setItemName(cartObject.getItems().getName());
            order.setDate(new Date());
            cartrepository.delete(cartObject);
            ordersrepository.saveAndFlush(order);
        }
        return ordersrepository.findAllByUserId(users.getUserId());
    }
    public List<orders> orderhist(Principal principal) {
        Users users = usersrepositoryclass.getByUsername(principal.getName()).get();
        ArrayList<cart> cartList = cartrepository.findAllByUser(users);

        for (int i = 0; i < cartList.size(); i++) {
            cart cartObject = cartList.get(i);
            orders order = new orders();
            order.setUserId(cartObject.getUser().getUserId());
            order.setQuantity(cartObject.getQuantity());
            order.setPrice(cartObject.getItems().getUnitPrice());
            order.setItemName(cartObject.getItems().getName());
            order.setDate(new Date());
            ordersrepository.saveAndFlush(order);
        }
        return ordersrepository.findAllByUserId(users.getUserId());
    }
}

