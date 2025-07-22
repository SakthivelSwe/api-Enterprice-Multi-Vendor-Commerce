package com.tvm.mapper;

import com.tvm.dto.*;
import com.tvm.entity.*;


import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponseDTO toDto( User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .phone(user.getPhone())
                .addresses(
                        (user.getAddresses() != null) ?
                                user.getAddresses()
                                        .stream()
                                        .map(UserMapper::addressToDto)
                                        .collect(Collectors.toList())
                                : List.of()
                )
                .build();
    }


    public static AddressDTO addressToDto(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .country(address.getCountry())
                .state(address.getState())
                .city(address.getCity())
                .village(address.getVillage())
                .postalCode(address.getPostalCode())
                .landmark(address.getLandmark())
                .build();
    }
}
