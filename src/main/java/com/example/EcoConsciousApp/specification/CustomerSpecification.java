package com.example.EcoConsciousApp.specification;

import com.example.EcoConsciousApp.dto.CustomerSearchDTO;
import com.example.EcoConsciousApp.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO){
        return new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!(customerSearchDTO.getSearchCustomerFullName() == null)) {
                    Predicate customerFirstNamePredicate = criteriaBuilder.like(root.get("fullName"), "%" + customerSearchDTO.getSearchCustomerFullName() + "%");
                    predicates.add(customerFirstNamePredicate);
                }
                if (!(customerSearchDTO.getSearchCustomerAddress() == null)) {
                    Predicate customerLastNamePredicate = criteriaBuilder.like(root.get("address"), "%" + customerSearchDTO.getSearchCustomerAddress() + "%");
                    predicates.add(customerLastNamePredicate);
                }
                if (!(customerSearchDTO.getSearchCustomerEmail() == null)) {
                    Predicate customerEmailPredicate = criteriaBuilder.like(root.get("email"), "%" + customerSearchDTO.getSearchCustomerEmail() + "%");
                    predicates.add(customerEmailPredicate);
                }

                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);

            }
        };
    }
}

