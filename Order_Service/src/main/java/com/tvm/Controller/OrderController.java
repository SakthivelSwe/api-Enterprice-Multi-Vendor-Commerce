package com.tvm.Controller;

import com.tvm.DTO.Orderdto;
import com.tvm.DTO.UserDTO;
import com.tvm.Model.Order;
import com.tvm.Security_config.JwtUtils;
import com.tvm.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired private JwtUtils jwtUtils;


    @PostMapping("/placeorder")
    public ResponseEntity<?> placeOrder(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        String response = orderService.placeOrder(username);
        return ResponseEntity.ok(response);
    }
///  both api are calling for the payment felgin client//
        // OrderController.java
        @PutMapping("/orders/{orderId}/status")
        public ResponseEntity<String> updateOrderStatus(
                @PathVariable Long orderId,
                @RequestParam String status) {
            orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok("Order status updated to " + status);
        }



    @GetMapping("/orderdetails/order/{userid}")
    public ResponseEntity<List<Orderdto>> showAllOrders(@PathVariable Long userid) {
        List<Orderdto> orders = orderService.getAllOrdersDtoByUserId(userid);
        return ResponseEntity.ok(orders);
    }







}
