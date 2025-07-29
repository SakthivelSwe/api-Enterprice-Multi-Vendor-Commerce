package com.tvm.service;

import com.tvm.dto.UserIdNameDTO;
import com.tvm.dto.UserPatchDTO;
import com.tvm.dto.UserRequestDTO;
import com.tvm.dto.UserResponseDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userDto, String username);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserByEmail(String email);

    void deleteUser(Long id);

    @Transactional
    UserResponseDTO patchUser(Long id, UserPatchDTO patchDTO);

    UserResponseDTO updateUser(Long id, UserRequestDTO userDto);

    List<UserResponseDTO> getUsersByAddress(String country, String city, String village, String postalCode);
    List<UserResponseDTO> getUsersByCountry(String country);
    List<UserResponseDTO> getUsersByCity(String city);


    List<UserResponseDTO> getUsersByPostalCode(String postalcode);
    UserResponseDTO getUserByName(String name);
    UserIdNameDTO getUserIdAndNameByName(String name);


}
