package com.tvm.Vendorservice;

import com.tvm.DTO.VendorDTO;
import com.tvm.DTO.Vendorclientdto;
import com.tvm.Exceptionhandler.VendorIdIsNotFound;
import com.tvm.VendorEntity.Vendor;
import com.tvm.VendorRepository.VendorRepository;
import com.tvm.vendormapper.VendorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements  VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @ Autowired
    private VendorMapper vendorMapper;
//
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

    @Override
    public Vendorclientdto isVendorApproved(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorIdIsNotFound("Vendor not found"));

        if (!vendor.isApproved()) {
            throw new  VendorIdIsNotFound( "Vendor is not approved yet");
        }

        return new Vendorclientdto(
                vendor.getId(),
                vendor.getName(),
                vendor.getEmail(),
                true
        );
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
