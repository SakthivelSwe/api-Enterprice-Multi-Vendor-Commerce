package com.tvm.productrepository;

import com.tvm.productEntity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository  extends JpaRepository<Vendor ,Long> {
}
