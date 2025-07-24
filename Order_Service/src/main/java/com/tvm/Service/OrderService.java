package com.tvm.Service;

import com.tvm.DTO.Orderdto;
import com.tvm.DTO.UserDTO;
import com.tvm.Exception.CartEmptyException;
import com.tvm.Exception.OrderNotFoundException;
import com.tvm.Model.Cart;
import com.tvm.Model.Order;
import com.tvm.Repository.CartItemrepo;
import com.tvm.Repository.Cartrepo;
import com.tvm.Repository.Orderrepo;
import com.tvm.client.Userfleign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired private Orderrepo orderRepo;
    @Autowired private Cartrepo cartRepo;
    @Autowired private CartItemrepo cartItemRepo;
    @Autowired private Userfleign userfleign;

    public String placeOrder(String username) {
        // 1. Get the cart
        Cart cart = cartRepo.findByusername(username)
                .orElseThrow(() -> new CartEmptyException("Cart not found"));

        // 2. Check if cart is empty
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty. Please add items before placing order.");
        }

        // 3. Get user info from User service
        UserDTO user = userfleign.getUser(username);

        // 4. Create new order
        Order order = new Order();
        order.setUserId(user.getUserid());
        order.setOrderDate(new Date());
        order.setOrderStatus("PENNDING");
        order.setTotalAmount(cart.getTotalPrice());
        order.setUsername(username);

        // 5. Save order
        orderRepo.save(order);

        // 6. Return address confirmation string
        String fullAddress = user.getUsername() + ", " +
                user.getLandmok() +
                user.getCity() +
                user.getState() +
                user.getCountry() +
                user.getPincode() +
                "Mobile: " + user.getMobileno();

        return "please confirm the the address for deleviery \nShipping To:\n" + fullAddress;
    }
    
    
    // payment service to access feign client
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setOrderStatus(status);
        orderRepo.save(order);
    }

    public List<Orderdto> getAllOrdersDtoByUserId(Long userId) {
        List<Order> orders = orderRepo.findByUserId(userId);

        return orders.stream()
                .map(order -> new Orderdto(
                        order.getId(),             // orderId
                        order.getTotalAmount(),    // totalAmount
                        order.getOrderStatus(),    // orderStatus
                        order.getUserId(),         // userId
                        order.getUsername()       // username (assuming your Order entity has this field)
                ))
                .toList(); // c
    }
}

