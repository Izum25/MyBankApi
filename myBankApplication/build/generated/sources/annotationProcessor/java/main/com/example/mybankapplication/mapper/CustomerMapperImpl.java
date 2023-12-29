package com.example.mybankapplication.mapper;

import com.example.mybankapplication.entities.AccountEntity;
import com.example.mybankapplication.entities.CustomerEntity;
import com.example.mybankapplication.entities.PassportEntity;
import com.example.mybankapplication.model.PassportDto;
import com.example.mybankapplication.model.accounts.AccountRequest;
import com.example.mybankapplication.model.accounts.AccountResponse;
import com.example.mybankapplication.model.customers.CustomerRequest;
import com.example.mybankapplication.model.customers.CustomerResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-30T00:32:11+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    private final DatatypeFactory datatypeFactory;

    public CustomerMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public CustomerResponse toDto(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setPassport( passportEntityToPassportDto( customerEntity.getPassport() ) );
        customerResponse.setId( customerEntity.getId() );
        customerResponse.setFirstName( customerEntity.getFirstName() );
        customerResponse.setLastName( customerEntity.getLastName() );
        customerResponse.setBirthDate( customerEntity.getBirthDate() );
        customerResponse.setEmail( customerEntity.getEmail() );
        customerResponse.setCif( customerEntity.getCif() );
        customerResponse.setPhoneNumber( customerEntity.getPhoneNumber() );
        customerResponse.setAccounts( accountEntityListToAccountResponseList( customerEntity.getAccounts() ) );

        return customerResponse;
    }

    @Override
    public CustomerEntity fromDto(CustomerRequest customerRequest) {
        if ( customerRequest == null ) {
            return null;
        }

        CustomerEntity.CustomerEntityBuilder customerEntity = CustomerEntity.builder();

        customerEntity.id( customerRequest.getId() );
        customerEntity.firstName( customerRequest.getFirstName() );
        customerEntity.lastName( customerRequest.getLastName() );
        customerEntity.birthDate( customerRequest.getBirthDate() );
        customerEntity.email( customerRequest.getEmail() );
        customerEntity.cif( customerRequest.getCif() );
        customerEntity.phoneNumber( customerRequest.getPhoneNumber() );
        customerEntity.passport( passportDtoToPassportEntity( customerRequest.getPassport() ) );
        customerEntity.accounts( accountRequestListToAccountEntityList( customerRequest.getAccounts() ) );

        return customerEntity.build();
    }

    @Override
    public CustomerEntity fromDto(CustomerResponse customerResponse) {
        if ( customerResponse == null ) {
            return null;
        }

        CustomerEntity.CustomerEntityBuilder customerEntity = CustomerEntity.builder();

        customerEntity.id( customerResponse.getId() );
        customerEntity.firstName( customerResponse.getFirstName() );
        customerEntity.lastName( customerResponse.getLastName() );
        customerEntity.birthDate( customerResponse.getBirthDate() );
        customerEntity.email( customerResponse.getEmail() );
        customerEntity.cif( customerResponse.getCif() );
        customerEntity.phoneNumber( customerResponse.getPhoneNumber() );
        customerEntity.passport( passportDtoToPassportEntity( customerResponse.getPassport() ) );
        customerEntity.accounts( accountResponseListToAccountEntityList( customerResponse.getAccounts() ) );

        return customerEntity.build();
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate ) {
        if ( localDate == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendarDate(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.getDayOfMonth(),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }

    private static LocalDateTime xmlGregorianCalendarToLocalDateTime( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        if ( xcal.getYear() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMonth() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getDay() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getHour() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMinute() != DatatypeConstants.FIELD_UNDEFINED
        ) {
            if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED
                && xcal.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond(),
                    Duration.ofMillis( xcal.getMillisecond() ).getNano()
                );
            }
            else if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond()
                );
            }
            else {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute()
                );
            }
        }
        return null;
    }

    protected PassportDto passportEntityToPassportDto(PassportEntity passportEntity) {
        if ( passportEntity == null ) {
            return null;
        }

        PassportDto passportDto = new PassportDto();

        if ( passportEntity.getId() != null ) {
            passportDto.setId( passportEntity.getId().intValue() );
        }
        passportDto.setName( passportEntity.getName() );
        passportDto.setSurname( passportEntity.getSurname() );
        passportDto.setBirthDate( passportEntity.getBirthDate() );
        passportDto.setExpiredDate( passportEntity.getExpiredDate() );

        return passportDto;
    }

    protected AccountResponse accountEntityToAccountResponse(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        AccountResponse.AccountResponseBuilder accountResponse = AccountResponse.builder();

        accountResponse.id( accountEntity.getId() );
        accountResponse.branchCode( accountEntity.getBranchCode() );
        accountResponse.accountNumber( accountEntity.getAccountNumber() );
        accountResponse.accountOpenDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( accountEntity.getAccountOpenDate() ) ) );
        accountResponse.accountExpireDate( accountEntity.getAccountExpireDate() );
        accountResponse.iban( accountEntity.getIban() );
        accountResponse.swift( accountEntity.getSwift() );
        accountResponse.currency( accountEntity.getCurrency() );
        accountResponse.accountType( accountEntity.getAccountType() );
        accountResponse.status( accountEntity.getStatus() );
        accountResponse.availableBalance( accountEntity.getAvailableBalance() );
        accountResponse.currentBalance( accountEntity.getCurrentBalance() );
        accountResponse.blockedAmount( accountEntity.getBlockedAmount() );
        accountResponse.pin( accountEntity.getPin() );

        return accountResponse.build();
    }

    protected List<AccountResponse> accountEntityListToAccountResponseList(List<AccountEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountResponse> list1 = new ArrayList<AccountResponse>( list.size() );
        for ( AccountEntity accountEntity : list ) {
            list1.add( accountEntityToAccountResponse( accountEntity ) );
        }

        return list1;
    }

    protected PassportEntity passportDtoToPassportEntity(PassportDto passportDto) {
        if ( passportDto == null ) {
            return null;
        }

        PassportEntity passportEntity = new PassportEntity();

        if ( passportDto.getId() != null ) {
            passportEntity.setId( passportDto.getId().longValue() );
        }
        passportEntity.setName( passportDto.getName() );
        passportEntity.setSurname( passportDto.getSurname() );
        passportEntity.setBirthDate( passportDto.getBirthDate() );
        passportEntity.setExpiredDate( passportDto.getExpiredDate() );

        return passportEntity;
    }

    protected AccountEntity accountRequestToAccountEntity(AccountRequest accountRequest) {
        if ( accountRequest == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( accountRequest.getId() );
        accountEntity.setBranchCode( accountRequest.getBranchCode() );
        accountEntity.setAccountNumber( accountRequest.getAccountNumber() );
        accountEntity.setAccountOpenDate( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( accountRequest.getAccountOpenDate() ) ) );
        accountEntity.setAccountExpireDate( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( accountRequest.getAccountExpireDate() ) ) );
        accountEntity.setIban( accountRequest.getIban() );
        accountEntity.setSwift( accountRequest.getSwift() );
        accountEntity.setCurrency( accountRequest.getCurrency() );
        accountEntity.setAccountType( accountRequest.getAccountType() );
        accountEntity.setStatus( accountRequest.getStatus() );
        accountEntity.setAvailableBalance( accountRequest.getAvailableBalance() );
        accountEntity.setCurrentBalance( accountRequest.getCurrentBalance() );
        accountEntity.setBlockedAmount( accountRequest.getBlockedAmount() );
        accountEntity.setPin( accountRequest.getPin() );

        return accountEntity;
    }

    protected List<AccountEntity> accountRequestListToAccountEntityList(List<AccountRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountEntity> list1 = new ArrayList<AccountEntity>( list.size() );
        for ( AccountRequest accountRequest : list ) {
            list1.add( accountRequestToAccountEntity( accountRequest ) );
        }

        return list1;
    }

    protected AccountEntity accountResponseToAccountEntity(AccountResponse accountResponse) {
        if ( accountResponse == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( accountResponse.getId() );
        accountEntity.setBranchCode( accountResponse.getBranchCode() );
        accountEntity.setAccountNumber( accountResponse.getAccountNumber() );
        accountEntity.setAccountOpenDate( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( accountResponse.getAccountOpenDate() ) ) );
        accountEntity.setAccountExpireDate( accountResponse.getAccountExpireDate() );
        accountEntity.setIban( accountResponse.getIban() );
        accountEntity.setSwift( accountResponse.getSwift() );
        accountEntity.setCurrency( accountResponse.getCurrency() );
        accountEntity.setAccountType( accountResponse.getAccountType() );
        accountEntity.setStatus( accountResponse.getStatus() );
        accountEntity.setAvailableBalance( accountResponse.getAvailableBalance() );
        accountEntity.setCurrentBalance( accountResponse.getCurrentBalance() );
        accountEntity.setBlockedAmount( accountResponse.getBlockedAmount() );
        accountEntity.setPin( accountResponse.getPin() );

        return accountEntity;
    }

    protected List<AccountEntity> accountResponseListToAccountEntityList(List<AccountResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountEntity> list1 = new ArrayList<AccountEntity>( list.size() );
        for ( AccountResponse accountResponse : list ) {
            list1.add( accountResponseToAccountEntity( accountResponse ) );
        }

        return list1;
    }
}
