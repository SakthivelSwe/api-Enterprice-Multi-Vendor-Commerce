package com.tvm.mapper;

import com.tvm.dto.*;
import com.tvm.entity.*;


import java.util.List;
import java.util.stream.Collectors;
public class UserMapper {

    public static UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getPhone(),
                (user.getAddresses() != null) ?
                        user.getAddresses()
                                .stream()
                                .map(UserMapper::addressToDto)
                                .collect(Collectors.toList())
                        : List.of()
        );
    }

    public static AddressDTO addressToDto(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getCountry(),
                address.getState(),
                address.getCity(),
                address.getVillage(),
                address.getPostalCode(),
                address.getLandmark()
        );
    }
}

