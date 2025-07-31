package com.tvm.UserServiceImpl;

import com.tvm.dto.*;
import com.tvm.entity.Address;
import com.tvm.entity.User;
//import com.tvm.mapper.UserMapper;
import com.tvm.exception.UserAlreadyExistsException;
import com.tvm.mapper.UserMapper;
import com.tvm.service.UserService;
import com.tvm.userRepository.AddressRepository;
import com.tvm.userRepository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class  UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto, String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }

        if (userRepository.findByName(username).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        User user = new User();
        user.setName(username);
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
//        user.setPassword(dto.getPassword());
        user.setGender(dto.getGender());


        List<Address> addresses = dto.getAddresses().stream().map(addressDTO -> {
            Address address = new Address();
            address.setCountry(addressDTO.getCountry());
            address.setState(addressDTO.getState());
            address.setCity(addressDTO.getCity());
            address.setVillage(addressDTO.getVillage());
            address.setPostalCode(addressDTO.getPostalCode());
            address.setLandmark(addressDTO.getLandmark());
            address.setUser(user); // set back reference
            return address;
        }).collect(Collectors.toList());

        user.setAddresses(addresses);

        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        userRepository.delete(user);
    }
    @Transactional
    @Override
    public UserResponseDTO patchUser(Long id, UserPatchDTO patchDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        if (patchDTO.getName() != null) user.setName(patchDTO.getName());
        if (patchDTO.getEmail() != null) user.setEmail(patchDTO.getEmail());
        if (patchDTO.getPhone() != null) user.setPhone(patchDTO.getPhone());
        if (patchDTO.getGender() != null) user.setGender(patchDTO.getGender());

        // Update address(es)
        if (patchDTO.getAddresses() != null) {
            for (AddressPatchDTO addressDTO : patchDTO.getAddresses()) {
                if (addressDTO.getId() == null) {
                    throw new IllegalArgumentException("Address ID must be provided for patching");
                }

                Address address = user.getAddresses().stream()
                        .filter(a -> Objects.equals(a.getId(), addressDTO.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressDTO.getId()));

                if (addressDTO.getCountry() != null) address.setCountry(addressDTO.getCountry());
                if (addressDTO.getState() != null) address.setState(addressDTO.getState());
                if (addressDTO.getCity() != null) address.setCity(addressDTO.getCity());
                if (addressDTO.getVillage() != null) address.setVillage(addressDTO.getVillage());
                if (addressDTO.getPostalCode() != null) address.setPostalCode(addressDTO.getPostalCode());
                if (addressDTO.getLandmark() != null) address.setLandmark(addressDTO.getLandmark());
            }
        }

        user.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(user);

        return UserMapper.toDto(updatedUser);
    }



    @Transactional
    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        user.setEmail(dto.getEmail());
//        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
        user.setUpdatedAt(LocalDateTime.now());


        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getUsersByAddress(String country, String city, String village, String postalCode) {
        List<User> users = userRepository.findAllByAddress(country, city, village, postalCode);
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public List<UserResponseDTO> getUsersByCountry(String country) {
        List<User> users = userRepository.findByAddresses_CountryIgnoreCase(country);
        return users.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public List<UserResponseDTO> getUsersByCity(String city) {
        List<User> users = userRepository.findByAddresses_CityIgnoreCase(city);
        return users.stream().map(UserMapper::toDto).toList();
    }
    @Override
    public List<UserResponseDTO> getUsersByPostalCode(String postalcode){
        List<User> users = userRepository.findByAddresses_PostalCode(postalcode);
        return users.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserResponseDTO getUserByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found with this name : " + name));
        return UserMapper.toDto(user);
    }

    @Override
    public UserIdNameDTO getUserIdAndNameByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found with this name: " + name));
        return new UserIdNameDTO(user.getId(), user.getName());
    }



}
