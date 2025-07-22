package com.tvm.controller;


import com.tvm.dto.VendorDTO;
import com.tvm.entity.Vendor;
import com.tvm.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @PostMapping("/register")
    public VendorDTO register(@RequestBody Vendor vendor) {
        return vendorService.registerVendor(vendor);
    }


    @PostMapping("/login")
    public VendorDTO login(@RequestParam String email, @RequestParam String password) {
        return vendorService.login(email, password);
    }


    @PutMapping("/{id}/update")
    public VendorDTO update(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateProfile(id, vendor);
    }


    @GetMapping("/{id}/status")
    public boolean isApproved(@PathVariable Long id) {
        return vendorService.isVendorApproved(id);
    }


    @PutMapping("/{id}/approve")
    public VendorDTO approveVendor(@PathVariable Long id) {
        return vendorService.approveVendor(id);
    }


    @PutMapping("/{id}/reject")
    public VendorDTO rejectVendor(@PathVariable Long id) {
        return vendorService.rejectVendor(id);
    }


    @GetMapping("/all")
    public List<VendorDTO> getAllVendors() {
        return vendorService.getAllVendors();
    }


    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return "Vendor with ID " + id + " deleted successfully.";
    }
}
