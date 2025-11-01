package com.example.VirtualBookStore.Controller;

import com.example.VirtualBookStore.Entity.CartItem;
import com.example.VirtualBookStore.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItems")
@CrossOrigin(origins = "http://localhost:3000") // for React
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Long userId,
                              @RequestParam Long bookId,
                              @RequestParam int quantity) {
        return cartItemService.addCartItem(userId, bookId, quantity);
    }

    @GetMapping("/user/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartItemService.getCartItemsByUser(userId);
    }

    @PutMapping("/update/{itemId}")
    public CartItem updateQuantity(@RequestParam Long userId,
                                   @PathVariable Long itemId,
                                   @RequestParam int quantity) {
        return cartItemService.updateCartItemQuantity(userId, itemId, quantity);
    }

    @DeleteMapping("/remove/{itemId}")
    public String removeCartItem(@RequestParam Long userId,
                                 @PathVariable Long itemId) {
        cartItemService.removeCartItem(userId, itemId);
        return "Item removed successfully";
    }
}
