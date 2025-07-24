package com.tvm.Repository;

import com.tvm.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Orderrepo extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
}
