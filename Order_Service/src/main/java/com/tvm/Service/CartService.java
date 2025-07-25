package com.tvm.Service;

import com.tvm.DTO.Productdto;
import com.tvm.DTO.UserDTO;
import com.tvm.Exception.CartEmptyException;
import com.tvm.Exception.ProductNotFoundException;
import com.tvm.Model.Cart;
import com.tvm.Model.CartItem;
import com.tvm.Repository.CartItemrepo;
import com.tvm.Repository.Cartrepo;
import com.tvm.client.Productfleign;
import com.tvm.client.Userfleign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private Cartrepo cartRepo;
    @Autowired private Productfleign productClient;
    @Autowired private Userfleign userfleign;
    @Autowired private CartItemrepo cartItemrepo;
    public Cart addToCart(String userId, Long productId, int quantity) {
        Productdto product = productClient.getProduct(productId);

        UserDTO user= userfleign.getUser(userId);

        Cart cart = cartRepo.findByusername(user.getUsername()).orElse(new Cart());
        cart.setUserId(user.getUserid());

        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setPrice(product.getPrice() * quantity);
        item.setCart(cart);

        cart.getItems().add(item);
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        cart.setUserId(user.getUserid());
        cart.setUsername(user.getUsername());
        return cartRepo.save(cart);
    }

    public Cart updatequantity(Long id, int quantity, String username) {
        Productdto productdto= productClient.getProduct(id);

        UserDTO user= userfleign.getUser(username);

        Cart cart = cartRepo.findByusername(user.getUsername()).orElseThrow(()->new CartEmptyException("your cart is empty please add the cart"));

        CartItem item=cart.getItems().stream().filter(c1->c1.getProductId().equals(id)).findFirst()
                .orElseThrow(()->new RuntimeException("Product not fount in the correct"));

        item.setQuantity(quantity);
        item.setPrice(productdto.getPrice() * quantity );

        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());

        return cartRepo.save(cart);


    }

    public Cart deletecart(Long cartItemId, String username) {
        UserDTO user = userfleign.getUser(username);

        Cart cart = cartRepo.findByUserId(user.getUserid())
                .orElseThrow(() -> new CartEmptyException("Your cart is empty"));

        // Find CartItem to delete
        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found in cart"));

        // Remove the item from cart
        cart.getItems().remove(itemToRemove);

        // Recalculate total price
        cart.setTotalPrice(
                cart.getItems().stream().mapToDouble(CartItem::getPrice).sum()
        );

        // Save and return updated cart
        return cartRepo.save(cart);
    }


    //schudular task
    public void deleteOldCartItems() {
        Date thresholdDate = new Date(System.currentTimeMillis() - 5L * 24 * 60 * 60 * 1000);

        List<Cart> carts = cartRepo.findAll();

        for (Cart cart : carts) {
            // Remove items older than 5 days
            cart.getItems().removeIf(item ->
                    item.getCreatedAt().before(thresholdDate)
            );

            // Recalculate total
            cart.setTotalPrice(
                    cart.getItems().stream().mapToDouble(CartItem::getPrice).sum()
            );

            // Save updated cart
            cartRepo.save(cart);
        }
    }





}
