package com.tvm.controller;



import com.tvm.dto.LoginRequestDTO;
import com.tvm.dto.VendorDTO;
import com.tvm.entity.Vendor;
import com.tvm.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/register")
    public VendorDTO register(@RequestBody Vendor vendor) {
        return vendorService.registerVendor(vendor);
    }

    @PostMapping("/login")
    public VendorDTO login(@RequestBody LoginRequestDTO request) {
        return vendorService.login(request.getEmail(), request.getPassword());
    }


    @PutMapping("/{id}/update")
    public VendorDTO update(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateProfile(id, vendor);
    }

    @GetMapping("/{id}/is-approved")
    public boolean isApproved(@PathVariable Long id) {
        return vendorService.isVendorApproved(id);
    }
    @PutMapping("/approve/{id}")
    public VendorDTO approveVendor(@PathVariable Long id) {
        return vendorService.approveVendor(id);
    }



}
