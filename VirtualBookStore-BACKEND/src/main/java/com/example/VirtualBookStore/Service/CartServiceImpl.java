package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.Cart;
import com.example.VirtualBookStore.Entity.CartItem;
import com.example.VirtualBookStore.Entity.User;
import com.example.VirtualBookStore.Repository.CartRepository;
import com.example.VirtualBookStore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> createCartForUser(userId));
    }

    @Override
    public Cart createCartForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalPrice(0.0);
        cart.setCartItems(new ArrayList<>());
        return cartRepository.save(cart);
    }


    @Override
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }
}
