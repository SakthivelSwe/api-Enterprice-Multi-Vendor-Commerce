package com.tvm.service;

import com.tvm.dto.EmailRequest;
import com.tvm.dto.VendorDTO;
import com.tvm.entity.EmailType;
import com.tvm.entity.Vendor;
import com.tvm.entity.VendorStatus;
import com.tvm.feign.NotificationClient;
import com.tvm.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepo;
    @Autowired
    private NotificationClient notificationClient;

    @Override
    public VendorDTO registerVendor(Vendor vendor) {
        vendor.setStatus(VendorStatus.APPROVED);
        Vendor saved = vendorRepo.save(vendor);
        return mapToDTO(saved);
    }

    @Override
    public VendorDTO login(String email, String password) {
        Vendor vendor = vendorRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        if (!vendor.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return  mapToDTO(vendor);  // Ensure this doesn’t throw NullPointer
    }


    @Override
    public VendorDTO updateProfile(Long id, Vendor updatedVendor) {
        Vendor vendor = vendorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setVendorName(updatedVendor.getVendorName());
        vendor.setEmail(updatedVendor.getEmail());
        Vendor saved = vendorRepo.save(vendor);
        return mapToDTO(saved);
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO approveVendor(Long vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setStatus(VendorStatus.APPROVED);
        Vendor saved = vendorRepo.save(vendor);


        EmailRequest email = new EmailRequest(
                vendor.getEmail(),
                "Vendor Approval Notification",
                EmailType.VENDOR_APPROVAL,
                new String[]{vendor.getVendorName(), "APPROVED"}
        );
        notificationClient.sendEmail(email);

        return mapToDTO(saved);
    }

    @Override
    public VendorDTO rejectVendor(Long vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setStatus(VendorStatus.REJECTED);
        return mapToDTO(vendorRepo.save(vendor));
    }

    @Override
    public boolean isVendorApproved(Long vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        return vendor.getStatus() == VendorStatus.APPROVED;
    }

    @Override
    public void deleteVendor(Long id) {
        Vendor vendor = vendorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendorRepo.delete(vendor);
    }

    private VendorDTO mapToDTO(Vendor vendor) {
        return new VendorDTO(
                vendor.getId(),
                vendor.getVendorName(),
                vendor.getEmail(),
                vendor.getStatus()
        );
    }
}