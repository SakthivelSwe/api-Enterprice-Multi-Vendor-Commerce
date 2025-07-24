package com.tvm.Repository;

import com.tvm.Model.CartItem;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemrepo extends JpaRepository<CartItem,Long> {

}
