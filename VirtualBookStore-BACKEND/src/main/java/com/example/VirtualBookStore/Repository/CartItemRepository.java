package com.example.VirtualBookStore.Repository;

import com.example.VirtualBookStore.Entity.Cart;
import com.example.VirtualBookStore.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    List<CartItem> findByCart(Cart cart);

    List<CartItem> findByCartId(Long cartId);

    Optional<CartItem> findByCartIdAndBookId(Long cartId, Long bookId);
}
