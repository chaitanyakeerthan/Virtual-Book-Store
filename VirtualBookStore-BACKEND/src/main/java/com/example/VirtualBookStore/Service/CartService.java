package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.Cart;

public interface CartService {
    Cart getCartByUserId(Long userId);
    Cart createCartForUser(Long userId);
    void clearCart(Long userId);
}
