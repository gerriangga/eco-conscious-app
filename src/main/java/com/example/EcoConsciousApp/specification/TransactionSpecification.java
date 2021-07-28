package com.example.EcoConsciousApp.specification;

import com.example.EcoConsciousApp.dto.TransactionSearchDTO;
import com.example.EcoConsciousApp.entity.PurchaseProduct;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {
    public static Specification<PurchaseProduct> getSpesification(TransactionSearchDTO transactionSearchDTO) {
        return new Specification<PurchaseProduct>() {
            @Override
            public Predicate toPredicate(Root<PurchaseProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!(transactionSearchDTO.getTransactionSearchCustomerId() == null)) {
                    Predicate transactionByCustomerId = criteriaBuilder.like(root.get("customer").get("id"), "%" + transactionSearchDTO.getTransactionSearchCustomerId() + "%");
                    predicates.add(transactionByCustomerId);
                }

                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}

