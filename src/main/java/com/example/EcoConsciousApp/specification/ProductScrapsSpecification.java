package com.example.EcoConsciousApp.specification;

import com.example.EcoConsciousApp.dto.ProductScrapsSearchDTO;
import com.example.EcoConsciousApp.entity.ProductScraps;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductScrapsSpecification {
    public static Specification<ProductScraps> getSpecification(ProductScrapsSearchDTO productSearchDTO) {
        return new Specification<ProductScraps>(){

            @Override
            public Predicate toPredicate(Root<ProductScraps> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(productSearchDTO.getSearchProductName() == null)) {
                    Predicate productNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")),
                            "%" + productSearchDTO.getSearchProductName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(productNamePredicate);
                }
                if (!(productSearchDTO.getSearchProductPrice()==null)) {
                    Predicate productPricePredicate = criteriaBuilder.equal(root.get("productPrice"),productSearchDTO.getSearchProductPrice());
                    predicates.add(productPricePredicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
