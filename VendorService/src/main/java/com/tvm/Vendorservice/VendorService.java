package com.tvm.Vendorservice;

import com.tvm.DTO.VendorDTO;
import com.tvm.DTO.Vendorclientdto;
import com.tvm.VendorEntity.Vendor;

import java.util.List;

public interface VendorService
{
    VendorDTO registerVendor(Vendor vendor);
    VendorDTO login(String email, String password);
    VendorDTO updateProfile(Long id, Vendor vendor);
    Vendorclientdto isVendorApproved(Long id);
    VendorDTO approveVendor(Long id);
    VendorDTO rejectVendor(Long id);
    List<VendorDTO> getAllVendors();
    void deleteVendor(Long id);
}
