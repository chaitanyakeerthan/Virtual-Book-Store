package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.Book;
import com.example.VirtualBookStore.Entity.Cart;
import com.example.VirtualBookStore.Entity.CartItem;
import com.example.VirtualBookStore.Entity.User;
import com.example.VirtualBookStore.Repository.BookRepository;
import com.example.VirtualBookStore.Repository.CartItemRepository;
import com.example.VirtualBookStore.Repository.CartRepository;
import com.example.VirtualBookStore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CartItem addCartItem(Long userId, Long bookId, int quantity) {
        // Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find or create cart for that user
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalPrice(0.0);
                    return cartRepository.save(newCart);
                });

        // Find book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if item already exists in this user's cart
        CartItem existingItem = cartItemRepository.findByCart(cart)
                .stream()
                .filter(item -> Objects.equals(item.getBook().getId(), bookId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setBook(book);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }

        // Recalculate total price
        double total = cartItemRepository.findByCart(cart)
                .stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();

        cart.setTotalPrice(total);
        cartRepository.save(cart);

        // Return the most recent cart item (the one added/updated)
        return cartItemRepository.findByCart(cart)
                .stream()
                .filter(item -> Objects.equals(item.getBook().getId(), bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found after update"));
    }

    @Override
    public List<CartItem> getCartItemsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cartItemRepository.findByCart(cart);
    }

    @Override
    public void removeCartItem(Long userId, Long itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Verify the item belongs to this user's cart
        if (!Objects.equals(item.getCart().getId(), cart.getId())) {
            throw new RuntimeException("This item does not belong to the user's cart");
        }

        cartItemRepository.delete(item);

        // Recalculate total price after removing item
        double total = cartItemRepository.findByCart(cart)
                .stream()
                .mapToDouble(ci -> ci.getBook().getPrice() * ci.getQuantity())
                .sum();

        cart.setTotalPrice(total);
        cartRepository.save(cart);
    }

    @Override
    public CartItem updateCartItemQuantity(Long userId, Long itemId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Verify item belongs to user's cart
        if (!Objects.equals(item.getCart().getId(), cart.getId())) {
            throw new RuntimeException("This item does not belong to the user's cart");
        }

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        // Update total price again
        double total = cartItemRepository.findByCart(cart)
                .stream()
                .mapToDouble(ci -> ci.getBook().getPrice() * ci.getQuantity())
                .sum();

        cart.setTotalPrice(total);
        cartRepository.save(cart);

        return item;
    }
}


