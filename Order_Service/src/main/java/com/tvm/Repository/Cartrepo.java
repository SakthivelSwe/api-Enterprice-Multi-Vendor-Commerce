package com.tvm.Repository;

import com.tvm.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Cartrepo extends JpaRepository<Cart,Long> {



    Optional<Cart> findByusername(String username);

    Optional<Cart> findByUserId(Long userid);
}
