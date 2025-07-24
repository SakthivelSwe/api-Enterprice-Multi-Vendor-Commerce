package com.tvm.Controller;

import com.tvm.Model.Cart;
import com.tvm.Security_config.JwtUtils;
import com.tvm.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired private JwtUtils jwtUtils;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestHeader("Authorization") String token,
                                       @RequestParam Long productId,
                                       @RequestParam int qty) {
        String username = jwtUtils.extractUsername(token.substring(7));

        Cart cart = cartService.addToCart(username, productId, qty);
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/update//{id}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token,@PathVariable Long id, @RequestParam int quantity){
        String username= jwtUtils.extractUsername(token.substring(7));

        Cart cart= cartService.updatequantity(id, quantity, username);
        return ResponseEntity.ok(cart);

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable Long id){
        String username=jwtUtils.extractUsername(token.substring(7));
        Cart cart=cartService.deletecart(id, username);
        return ResponseEntity.ok(cart);
    }
}

