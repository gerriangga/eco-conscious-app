package com.example.EcoConsciousApp.specification;

import com.example.EcoConsciousApp.dto.UsableProductSearchDTO;
import com.example.EcoConsciousApp.entity.UsableProduct;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UsableProductSpecification {
    public static Specification<UsableProduct> getSpecification(UsableProductSearchDTO usableProductSearchDTO){
        return new Specification<UsableProduct>() {
            @Override
            public Predicate toPredicate(Root<UsableProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(!(usableProductSearchDTO.getSearchUsableProductName() == null)){
                    Predicate usableProductNamePredicate = criteriaBuilder.like(root.get("usableProductName"), "%" + usableProductSearchDTO.getSearchUsableProductName() + "%");
                    predicates.add(usableProductNamePredicate);
                }
                if(!(usableProductSearchDTO.getSearchUsableProductDescription() == null)){
                    Predicate usableProductDescriptionPredicate = criteriaBuilder.like(root.get("usableProductDescription"), "%" + usableProductSearchDTO.getSearchUsableProductDescription() + "%");
                    predicates.add(usableProductDescriptionPredicate);
                }

                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);

            }
        };
    }
}
