package com.tvm.userRepository;

import com.tvm.entity.Address;
import com.tvm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUser(User user);

    List<Address> findByCountryAndCityAndVillageAndPostalCode(
            String country,
            String city,
            String village,
            String postalCode
    );
}
