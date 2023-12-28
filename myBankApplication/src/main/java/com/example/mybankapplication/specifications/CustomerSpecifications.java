package com.example.mybankapplication.specifications;

import com.example.mybankapplication.entities.CustomerEntity;
import com.example.mybankapplication.model.customers.CustomerFilterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerSpecifications {
//    public Specification<CustomerEntity> getCustomerSpecification(CustomerFilterDto customerFilterDto) {
//        Specification<CustomerEntity> firstNameSpecification = ((root, query, criteriaBuilder) ->
//                customerFilterDto.getFirstName() == null || customerFilterDto.getFirstName().isBlank() ?
//                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
//                        "%" + customerFilterDto.getFirstName().toLowerCase() + "%"));
//
//        Specification<CustomerEntity> lastNameSpecification = ((root, query, criteriaBuilder) ->
//                customerFilterDto.getLastName() == null || customerFilterDto.getLastName().isBlank() ?
//                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
//                        "%" + customerFilterDto.getLastName().toLowerCase() + "%"));
//
//        Specification<CustomerEntity> birthdateSpecification = ((root, query, criteriaBuilder) ->
//                customerFilterDto.getBirthDate() == null ?
//                        null : criteriaBuilder.equal(root.get("birthdate"), customerFilterDto.getBirthDate()));
//
//        return Specification.where(firstNameSpecification)
//                .and(lastNameSpecification)
//                .and(birthdateSpecification);
//    }

    private static <T> Specification<T> likeIgnoreCase(String attribute, String value) {
        return (root, query, criteriaBuilder) ->
                value == null || value.isBlank() ?
                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get(attribute)),
                        "%" + value.toLowerCase() + "%");
    }

    private static <T> Specification<T> equal(String attribute, Object value) {
        return (root, query, criteriaBuilder) ->
                value == null ?
                        null : criteriaBuilder.equal(root.get(attribute), value);
    }

    public static Specification<CustomerEntity> getCustomerSpecification(CustomerFilterDto customerFilterDto) {
        log.info("Get specification for customer {}", customerFilterDto.toString()); //debug
        return Specification.<CustomerEntity>where(
                likeIgnoreCase("firstName", customerFilterDto.getFirstName()))
                        .and(likeIgnoreCase("lastName", customerFilterDto.getLastName()))
                                .and(equal("birthDate", customerFilterDto.getBirthDate()))
                                        .and(likeIgnoreCase("email", customerFilterDto.getEmail()))
                                                .and(likeIgnoreCase("cif", customerFilterDto.getCif()))
                                                        .and(likeIgnoreCase("phoneNumber", customerFilterDto.getPhoneNumber()));
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
