package com.tvm.REPOSITROY;

import com.tvm.MODEL.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepo extends JpaRepository<User, Long> {
    User findByUsername(String username);  // ✅ No static, no body!
}