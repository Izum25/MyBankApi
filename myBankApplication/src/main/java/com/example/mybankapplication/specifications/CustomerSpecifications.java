package com.example.mybankapplication.specifications;

import com.example.mybankapplication.dao.CustomerEntity;
import com.example.mybankapplication.model.CustomerFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpecifications {
    public Specification<CustomerEntity> getCustomerSpecification(CustomerFilterDto customerFilterDto) {
        Specification<CustomerEntity> firstNameSpecification = ((root, query, criteriaBuilder) ->
                customerFilterDto.getFirstName() == null || customerFilterDto.getFirstName().isBlank() ?
                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        "%" + customerFilterDto.getFirstName().toLowerCase() + "%"));

        Specification<CustomerEntity> lastNameSpecification = ((root, query, criteriaBuilder) ->
                customerFilterDto.getLastName() == null || customerFilterDto.getLastName().isBlank() ?
                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        "%" + customerFilterDto.getLastName().toLowerCase() + "%"));

        Specification<CustomerEntity> birthdateSpecification = ((root, query, criteriaBuilder) ->
                customerFilterDto.getBirthDate() == null ?
                        null : criteriaBuilder.equal(root.get("birthdate"), customerFilterDto.getBirthDate()));

        return Specification.where(firstNameSpecification)
                .and(lastNameSpecification)
                .and(birthdateSpecification);
    }
//    public static Specification<CustomerEntity> firstNameContainsIgnoreCase(String firstName) {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
//    }
//
//    public static Specification<CustomerEntity> lastNameContainsIgnoreCase(String lastName) {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
//    }
//
//    public static Specification<CustomerEntity> birthdateEquals(LocalDate birthdate) {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get("birthdate"), birthdate);
//    }
}
