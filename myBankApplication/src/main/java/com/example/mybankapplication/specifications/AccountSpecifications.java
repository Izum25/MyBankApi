package com.example.mybankapplication.specifications;

import com.example.mybankapplication.entities.AccountEntity;
import com.example.mybankapplication.model.accounts.AccountFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AccountSpecifications {
//    private final AccountRepository accountRepository;
//    private final AccountMapper accountMapper;
//
//    public Page<AccountFilterDto> findAccountByFilter(AccountFilterDto filterDto, PageRequest pageRequest) {
//        Specification<AccountEntity> spec = (root, query, criteriaBuilder) -> {
//            // Add your filtering logic based on the fields in filterDto
//            // For example:
//            Predicate predicate = criteriaBuilder.conjunction();
//            if (filterDto.getAccountNumber() != null) {
//                predicate = criteriaBuilder.and(predicate,
//                        criteriaBuilder.equal(root.get("accountNumber"), filterDto.getAccountNumber()));
//            }
//            // Add more conditions as needed...
//
//            return predicate;
//        };
//
//        Page<AccountEntity> accountEntityPage = accountRepository.findAll(spec, pageRequest);
//        return accountEntityPage.map(accountMapper::mapToFilterDto);
//    }

//    public Specification<AccountEntity> getAccountSpecification(AccountFilterDto accountFilterDto) {
//        Specification<AccountEntity> branchCodeSpecification = ((root, query, criteriaBuilder) ->
//                accountFilterDto.getBranchCode() == null || accountFilterDto.getBranchCode().isBlank() ?
//                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("branchCode")),
//                        "%" + accountFilterDto.getBranchCode().toLowerCase() + "%"));
//        Specification<AccountEntity> accountNumberSpecification = ((root, query, criteriaBuilder) ->
//                accountFilterDto.getAccountNumber() == null || accountFilterDto.getAccountNumber().isBlank() ?
//                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("accountNumber")),
//                        "%" + accountFilterDto.getAccountNumber().toLowerCase() + "%"));
////        Specification<AccountEntity> accountOpenDateSpecification = ((root, query, criteriaBuilder) ->
////                accountFilterDto.getAccountOpenDate() == null || accountFilterDto.getAccountOpenDate() ?
////                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("accountNumber")),
////                        "%" + accountFilterDto.getAccountNumber().toLowerCase() + "%"));
////        Specification<AccountEntity> accountExpireDateSpecification = ((root, query, criteriaBuilder) ->
////                accountFilterDto.getAccountNumber() == null || accountFilterDto.getAccountNumber().isBlank() ?
////                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("accountNumber")),
////                        "%" + accountFilterDto.getAccountNumber().toLowerCase() + "%"));
//        Specification<AccountEntity> ibanSpecification = ((root, query, criteriaBuilder) ->
//                accountFilterDto.getIban() == null || accountFilterDto.getIban().isBlank() ?
//                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("iban")),
//                        "%" + accountFilterDto.getIban().toLowerCase() + "%"));
//        Specification<AccountEntity> swiftSpecification = ((root, query, criteriaBuilder) ->
//                accountFilterDto.getSwift() == null || accountFilterDto.getSwift().isBlank() ?
//                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("swift")),
//                        "%" + accountFilterDto.getSwift().toLowerCase() + "%"));
//        Specification<AccountEntity> currencySpecification = ((root, query, criteriaBuilder) ->
//                accountFilterDto.getCurrency() == null || accountFilterDto.getCurrency().isBlank() ?
//                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("currency")),
//                        "%" + accountFilterDto.getCurrency().toLowerCase() + "%"));
////        Specification<AccountEntity> accountTypeSpecification = ((root, query, criteriaBuilder) ->
////                accountFilterDto.getAccountType() == null || accountFilterDto.getAccountType().isBlank() ?
////                        null : criteriaBuilder.like(criteriaBuilder.lower(root.get("currency")),
////                        "%" + accountFilterDto.getCurrency().toLowerCase() + "%"));
//
//
//
//        return Specification.where(branchCodeSpecification)
//                .and(accountNumberSpecification)
////                .and(accountNumberSpecification)
////                .and(accountNumberSpecification)
//                .and(ibanSpecification)
//                .and(swiftSpecification)
//                .and(currencySpecification)
//                ;
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

    public static Specification<AccountEntity> getAccountSpecification(AccountFilterDto accountFilterDto) {
        return Specification.<AccountEntity>where(
                        likeIgnoreCase("branchCode", accountFilterDto.getBranchCode()))
                .and(likeIgnoreCase("accountNumber", accountFilterDto.getAccountNumber()))
                .and(equal("accountOpenDate", accountFilterDto.getAccountOpenDate()))
                .and(equal("accountExpireDate", accountFilterDto.getAccountExpireDate()))
                .and(likeIgnoreCase("iban", accountFilterDto.getIban()))
                .and(likeIgnoreCase("swift", accountFilterDto.getSwift()))
                .and(likeIgnoreCase("currency", accountFilterDto.getCurrency()))
                .and(equal("accountType", accountFilterDto.getAccountType()))
                .and(equal("status", accountFilterDto.getStatus()))
                .and(equal("availableBalance", accountFilterDto.getAvailableBalance()))
                .and(equal("currentBalance", accountFilterDto.getCurrentBalance()))
                .and(equal("blockedAmount", accountFilterDto.getBlockedAmount()));
    }
}
