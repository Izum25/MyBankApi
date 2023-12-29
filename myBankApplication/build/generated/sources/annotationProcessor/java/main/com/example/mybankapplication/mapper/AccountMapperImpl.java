package com.example.mybankapplication.mapper;

import com.example.mybankapplication.entities.AccountEntity;
import com.example.mybankapplication.model.accounts.AccountRequest;
import com.example.mybankapplication.model.accounts.AccountResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-30T00:32:10+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    private final DatatypeFactory datatypeFactory;

    public AccountMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public AccountResponse toDto(AccountEntity accountEntity) {
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

    @Override
    public AccountEntity fromDto(AccountRequest accountRequest) {
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
}
