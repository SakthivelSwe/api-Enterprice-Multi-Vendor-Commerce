package com.tvm.vendorcontroller;

import com.tvm.DTO.LoginRequestDTO;
import com.tvm.DTO.VendorDTO;
import com.tvm.DTO.Vendorclientdto;
import com.tvm.VendorEntity.Vendor;
import com.tvm.Vendorservice.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendors")
public class VendorController
{
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
    public VendorDTO login(@RequestBody LoginRequestDTO request) {
        return vendorService.login(request.getEmail(),request.getPassword());
    }

    @PutMapping("/{id}/update")
    public VendorDTO update(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateProfile(id, vendor);
    }
  //feign
    @GetMapping("/{id}/is-approved")
    public Vendorclientdto isApproved(@PathVariable Long id) {
        return vendorService.isVendorApproved(id);
    }
}
