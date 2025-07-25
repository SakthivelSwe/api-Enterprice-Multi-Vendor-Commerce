package com.tvm.userRepository;

import com.tvm.entity.Address;
import com.tvm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUser(User user);

    List<Address> findByCountryAndCityAndVillageAndPostalCode(
            String country,
            String city,
            String village,
            String postalCode
    );
}
