package com.example.EcoConsciousApp.specification;

import com.example.EcoConsciousApp.dto.VendorSearchDTO;
import com.example.EcoConsciousApp.entity.Vendor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VendorSpecification {
    public static Specification<Vendor> getSpecification(VendorSearchDTO vendorSearchDTO) {
        return new Specification<Vendor>(){

            @Override
            public Predicate toPredicate(Root<Vendor> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(vendorSearchDTO.getSearchVendorName() == null)) {
                    Predicate vendorNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("vendorName")),
                            "%" + vendorSearchDTO.getSearchVendorName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(vendorNamePredicate);
                }

                if (!(vendorSearchDTO.getSearchVendorAddress() == null)) {
                    Predicate vendorAddressPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("vendorAddress")),
                            "%" + vendorSearchDTO.getSearchVendorAddress().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(vendorAddressPredicate);
                }

                if (!(vendorSearchDTO.getSearchStatus()==null)) {
                    Predicate productStatusPredicate = criteriaBuilder.equal(root.get("status"),vendorSearchDTO.getSearchStatus());
                    predicates.add(productStatusPredicate);
                }

                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
