package com.example.EcoConsciousApp.service.lmpl;

import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.entity.VendorUsableProductRegistration;
import com.example.EcoConsciousApp.entity.VendorUsableProductRegistrationDetail;
import com.example.EcoConsciousApp.repository.VendorUsableProductRegistrationRepository;
import com.example.EcoConsciousApp.service.UsableProductService;
import com.example.EcoConsciousApp.service.VendorService;
import com.example.EcoConsciousApp.service.VendorUsableProductRegistrationDetailService;
import com.example.EcoConsciousApp.service.VendorUsableProductRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendorUsableProductRegistrationlmpl implements VendorUsableProductRegistrationService {
    @Autowired
    VendorUsableProductRegistrationRepository registrationRepository;

    @Autowired
    VendorUsableProductRegistrationDetailService registrationDetailService;

    @Autowired
    UsableProductService usableproductService;

    @Autowired
    VendorService vendorService;

    @Override
    @Transactional
    public VendorUsableProductRegistration transaction(VendorUsableProductRegistration registration) {
        VendorUsableProductRegistration registration1 = registrationRepository.save(registration);
        for (VendorUsableProductRegistrationDetail registrationDetail : registration1.getRegistrationDetails()) {
            UsableProduct product = usableproductService.getUsableProductById(registrationDetail.getUsableProduct().getId());
            product.setUsableProductStock(product.getUsableProductStock() + registrationDetail.getQuantity());
            usableproductService.saveUsableProduct(product);
            registrationDetail.setRegistration(registration1);
            registrationDetailService.saveRegistrationDetail(registrationDetail);
        }
        return registration1;
    }

    @Override
    public VendorUsableProductRegistration getTransactionByiD(String id) {
        return registrationRepository.findById(id).get();
    }
}
