package com.example.EcoConsciousApp.anotation;

import com.example.EcoConsciousApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return customerRepository.findByPhoneNumber(phoneNumber) == null;
    }
}

