package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.CartItem;
import java.util.List;

public interface CartItemService {
    CartItem addCartItem(Long userId, Long bookId, int quantity);
    List<CartItem> getCartItemsByUser(Long userId);
    void removeCartItem(Long userId, Long itemId);
    CartItem updateCartItemQuantity(Long userId, Long itemId, int quantity);
}
