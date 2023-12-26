package com.example.mybankapplication.mapper;

import com.example.mybankapplication.dao.AccountEntity;
import com.example.mybankapplication.model.accounts.AccountDto;
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
    date = "2023-12-27T00:08:07+0400",
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
    public AccountDto mapToDto(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId( accountEntity.getId() );
        accountDto.setBranchCode( accountEntity.getBranchCode() );
        accountDto.setAccountNumber( accountEntity.getAccountNumber() );
        accountDto.setAccountOpenDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( accountEntity.getAccountOpenDate() ) ) );
        accountDto.setIban( accountEntity.getIban() );
        accountDto.setCurrency( accountEntity.getCurrency() );
        accountDto.setAccountType( accountEntity.getAccountType() );
        accountDto.setStatus( accountEntity.getStatus() );
        accountDto.setAvailableBalance( accountEntity.getAvailableBalance() );
        accountDto.setCurrentBalance( accountEntity.getCurrentBalance() );
        accountDto.setBlockedAmount( accountEntity.getBlockedAmount() );
        accountDto.setCustomer( accountEntity.getCustomer() );

        return accountDto;
    }

    @Override
    public AccountEntity mapToEntity(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( accountDto.getId() );
        accountEntity.setBranchCode( accountDto.getBranchCode() );
        accountEntity.setAccountNumber( accountDto.getAccountNumber() );
        accountEntity.setAccountOpenDate( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( accountDto.getAccountOpenDate() ) ) );
        accountEntity.setIban( accountDto.getIban() );
        accountEntity.setCurrency( accountDto.getCurrency() );
        accountEntity.setAccountType( accountDto.getAccountType() );
        accountEntity.setStatus( accountDto.getStatus() );
        accountEntity.setAvailableBalance( accountDto.getAvailableBalance() );
        accountEntity.setCurrentBalance( accountDto.getCurrentBalance() );
        accountEntity.setBlockedAmount( accountDto.getBlockedAmount() );
        accountEntity.setCustomer( accountDto.getCustomer() );

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
