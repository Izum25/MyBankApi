package com.example.mybankapplication.mapper;

import com.example.mybankapplication.dao.AccountEntity;
import com.example.mybankapplication.dao.CustomerEntity;
import com.example.mybankapplication.dao.PassportEntity;
import com.example.mybankapplication.model.PassportDto;
import com.example.mybankapplication.model.accounts.AccountDto;
import com.example.mybankapplication.model.customers.CustomerDto;
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
    date = "2023-12-27T13:24:34+0400",
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
    public CustomerDto mapToDto(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setPassport( passportEntityToPassportDto( customerEntity.getPassport() ) );
        customerDto.setId( customerEntity.getId() );
        customerDto.setFirstName( customerEntity.getFirstName() );
        customerDto.setLastName( customerEntity.getLastName() );
        customerDto.setBirthDate( customerEntity.getBirthDate() );
        customerDto.setEmail( customerEntity.getEmail() );
        customerDto.setAccounts( accountEntityListToAccountDtoList( customerEntity.getAccounts() ) );

        return customerDto;
    }

    @Override
    public CustomerEntity mapToEntity(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId( customerDto.getId() );
        customerEntity.setFirstName( customerDto.getFirstName() );
        customerEntity.setLastName( customerDto.getLastName() );
        customerEntity.setBirthDate( customerDto.getBirthDate() );
        customerEntity.setEmail( customerDto.getEmail() );
        customerEntity.setPassport( passportDtoToPassportEntity( customerDto.getPassport() ) );
        customerEntity.setAccounts( accountDtoListToAccountEntityList( customerDto.getAccounts() ) );

        return customerEntity;
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

    protected AccountDto accountEntityToAccountDto(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId( accountEntity.getId() );
        accountDto.setBranchCode( accountEntity.getBranchCode() );
        accountDto.setAccountNumber( accountEntity.getAccountNumber() );
        accountDto.setAccountOpenDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( accountEntity.getAccountOpenDate() ) ) );
        accountDto.setAccountExpireDate( accountEntity.getAccountExpireDate() );
        accountDto.setIban( accountEntity.getIban() );
        accountDto.setSwift( accountEntity.getSwift() );
        accountDto.setCurrency( accountEntity.getCurrency() );
        accountDto.setAccountType( accountEntity.getAccountType() );
        accountDto.setStatus( accountEntity.getStatus() );
        accountDto.setAvailableBalance( accountEntity.getAvailableBalance() );
        accountDto.setCurrentBalance( accountEntity.getCurrentBalance() );
        accountDto.setBlockedAmount( accountEntity.getBlockedAmount() );
        accountDto.setCustomer( accountEntity.getCustomer() );

        return accountDto;
    }

    protected List<AccountDto> accountEntityListToAccountDtoList(List<AccountEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountDto> list1 = new ArrayList<AccountDto>( list.size() );
        for ( AccountEntity accountEntity : list ) {
            list1.add( accountEntityToAccountDto( accountEntity ) );
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

    protected AccountEntity accountDtoToAccountEntity(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( accountDto.getId() );
        accountEntity.setBranchCode( accountDto.getBranchCode() );
        accountEntity.setAccountNumber( accountDto.getAccountNumber() );
        accountEntity.setAccountOpenDate( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( accountDto.getAccountOpenDate() ) ) );
        accountEntity.setAccountExpireDate( accountDto.getAccountExpireDate() );
        accountEntity.setIban( accountDto.getIban() );
        accountEntity.setSwift( accountDto.getSwift() );
        accountEntity.setCurrency( accountDto.getCurrency() );
        accountEntity.setAccountType( accountDto.getAccountType() );
        accountEntity.setStatus( accountDto.getStatus() );
        accountEntity.setAvailableBalance( accountDto.getAvailableBalance() );
        accountEntity.setCurrentBalance( accountDto.getCurrentBalance() );
        accountEntity.setBlockedAmount( accountDto.getBlockedAmount() );
        accountEntity.setCustomer( accountDto.getCustomer() );

        return accountEntity;
    }

    protected List<AccountEntity> accountDtoListToAccountEntityList(List<AccountDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountEntity> list1 = new ArrayList<AccountEntity>( list.size() );
        for ( AccountDto accountDto : list ) {
            list1.add( accountDtoToAccountEntity( accountDto ) );
        }

        return list1;
    }
}
