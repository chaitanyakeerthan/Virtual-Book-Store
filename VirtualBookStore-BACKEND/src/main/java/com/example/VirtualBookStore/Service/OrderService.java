package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
}
