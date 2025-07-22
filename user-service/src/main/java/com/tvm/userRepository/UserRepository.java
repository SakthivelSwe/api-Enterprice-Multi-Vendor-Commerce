package com.tvm.userRepository;

import com.tvm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.addresses a WHERE a.country = :country AND a.city = :city AND a.village = :village AND a.postalCode = :postalCode")
    List<User> findAllByAddress(@Param("country") String country,
                                @Param("city") String city,
                                @Param("village") String village,
                                @Param("postalCode") String postalCode);

    List<User> findByAddresses_CountryIgnoreCase(String country);
    List<User> findByAddresses_CityIgnoreCase(String city);


}
