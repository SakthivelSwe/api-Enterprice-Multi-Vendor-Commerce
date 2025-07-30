package com.tvm.vendorcontroller;

import com.tvm.DTO.VendorDTO;
import com.tvm.Vendorservice.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/vendors")
public class AdminController
{
    @Autowired
    private VendorService vendorService;

    @GetMapping
    public List<VendorDTO> getAll() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{id}/approve")
    public VendorDTO approve(@PathVariable Long id) {
        return vendorService.approveVendor(id);
    }

    @PutMapping("/{id}/reject")
    public VendorDTO reject(@PathVariable Long id) {
        return vendorService.rejectVendor(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return "Vendor with ID " + id + " deleted successfully.";
    }
}
