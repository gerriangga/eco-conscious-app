package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.VendorUsableProductRegistrationDetail;
import com.example.EcoConsciousApp.repository.VendorUsableProductRegistrationDetailRepository;
import com.example.EcoConsciousApp.repository.VendorUsableProductRegistrationRepository;
import com.example.EcoConsciousApp.service.VendorUsableProductRegistrationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorUsableProductRegistrationDetaillmpl implements VendorUsableProductRegistrationDetailService {
    @Autowired
    VendorUsableProductRegistrationDetailRepository registrationDetailRepository;

    @Override
    public VendorUsableProductRegistrationDetail saveRegistrationDetail(VendorUsableProductRegistrationDetail registrationDetail) {
        return registrationDetailRepository.save(registrationDetail);
    }

}
