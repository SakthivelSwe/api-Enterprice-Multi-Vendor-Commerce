package com.tvm.mapper;


import com.tvm.dto.VendorDTO;
import com.tvm.entity.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

    public VendorDTO toDTO(Vendor vendor) {
        VendorDTO dto = new VendorDTO();
        dto.setId(vendor.getId());
        dto.setName(vendor.getName());
        dto.setEmail(vendor.getEmail());
        dto.setApproved(vendor.isApproved());
        return dto;
    }

    public Vendor toEntity(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setId(dto.getId());
        vendor.setName(dto.getName());
        vendor.setEmail(dto.getEmail());
        vendor.setApproved(dto.isApproved());
        return vendor;
    }
}
