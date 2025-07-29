
///UserController.java
package com.tvm.controller;

import com.tvm.Config.Jwtutil;
import com.tvm.dto.UserIdNameDTO;
import com.tvm.dto.UserPatchDTO;
import com.tvm.dto.UserRequestDTO;
import com.tvm.dto.UserResponseDTO;
import com.tvm.exception.TokenNotFoundException;
import com.tvm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private Jwtutil jwtutil;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. Create User
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser( @RequestHeader("Authorization") String token ,@RequestBody UserRequestDTO dto) {

       try {
           String username = jwtutil.extractUsername(token.substring(7));


        UserResponseDTO createdUser = userService.createUser(dto,username);
        return ResponseEntity.ok(createdUser);
    }
    catch(Exception ex){
          throw new TokenNotFoundException("please check the token is mismathced");
        }
    }
//    @PostMapping("/adding")
//    public ResponseEntity<UserResponseDTO> createUser( @Valid @RequestBody UserRequestDTO dto) {
////        String username = jwtutil.extractUsername(token.substring(7));
//
//        UserResponseDTO createdUser = userService.createUser(dto);
//        return ResponseEntity.ok(createdUser);
//    }

    // 2. Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // 3. Get All Users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 4. Get User by Email
    @GetMapping("/email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // 5. Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // 7. Get Users by Address
    @GetMapping("/address")
    public ResponseEntity<List<UserResponseDTO>> getUsersByAddress(
            @RequestParam String country,
            @RequestParam String city,
            @RequestParam String village,
            @RequestParam String postalCode
    ) {
        return ResponseEntity.ok(userService.getUsersByAddress(country, city, village, postalCode));
    }
    // 8. Get Users by Country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByCountry(@PathVariable String country) {
        return ResponseEntity.ok(userService.getUsersByCountry(country));
    }

    // 9. Get Users by City
    @GetMapping("/city/{city}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByCity(@PathVariable String city) {
        return ResponseEntity.ok(userService.getUsersByCity(city));
    }
    //10.
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> patchUser(@PathVariable Long id,
                                                     @RequestBody UserPatchDTO patchDTO) {
        return ResponseEntity.ok(userService.patchUser(id, patchDTO));
    }
    // 11.get User by Postalcode
    @GetMapping("/address/{postalcode}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByPostalCode(@PathVariable String postalcode ) {
        return ResponseEntity.ok(userService.getUsersByPostalCode(postalcode));
    }
    // 1. Get full user details by name
    @GetMapping("/name/full/{name}")
    public ResponseEntity<UserResponseDTO> getUserByNameFull(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    // 2. Get only userId and name
    @GetMapping("/name/{name}")
    public ResponseEntity<UserIdNameDTO> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserIdAndNameByName(name));
    }




}