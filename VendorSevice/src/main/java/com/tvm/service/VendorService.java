package com.tvm.service;


import com.tvm.dto.VendorDTO;
import com.tvm.entity.Vendor;

import java.util.List;

public interface VendorService {
    VendorDTO registerVendor(Vendor vendor);
    VendorDTO login(String email, String password);
    VendorDTO updateProfile(Long id, Vendor vendor);
    boolean isVendorApproved(Long id);
    VendorDTO approveVendor(Long id);
    VendorDTO rejectVendor(Long id);
    List<VendorDTO> getAllVendors();
    void deleteVendor(Long id);

}
