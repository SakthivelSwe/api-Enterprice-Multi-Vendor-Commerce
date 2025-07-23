package com.tvm.service;



import com.tvm.dto.VendorDTO;
import com.tvm.entity.Vendor;
import com.tvm.mapper.VendorMapper;
import com.tvm.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private VendorMapper vendorMapper;

    @Override
    public VendorDTO registerVendor(Vendor vendor) {
        vendor.setApproved(false); // Not approved by default
        return vendorMapper.toDTO(vendorRepository.save(vendor));
    }

    @Override
    public VendorDTO login(String email, String password) {
        Vendor vendor = vendorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        if (!vendor.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        return vendorMapper.toDTO(vendor);
    }

    @Override
    public VendorDTO updateProfile(Long id, Vendor vendorUpdate) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendor.setName(vendorUpdate.getName());
        vendor.setEmail(vendorUpdate.getEmail());
        vendor.setPassword(vendorUpdate.getPassword());
        return vendorMapper.toDTO(vendorRepository.save(vendor));
    }


    public boolean isVendorApproved(Long id) {
        return vendorRepository.findById(id)
                .map(Vendor::isApproved)
                .orElse(false);
    }

    @Override
    public VendorDTO approveVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendor.setApproved(true);
        return vendorMapper.toDTO(vendorRepository.save(vendor));
    }

    @Override
    public VendorDTO rejectVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendor.setApproved(false);
        return vendorMapper.toDTO(vendorRepository.save(vendor));
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(vendorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }


}