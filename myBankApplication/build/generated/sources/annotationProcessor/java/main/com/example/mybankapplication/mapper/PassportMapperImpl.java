package com.example.mybankapplication.mapper;

import com.example.mybankapplication.dao.PassportEntity;
import com.example.mybankapplication.model.PassportDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-27T13:24:33+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class PassportMapperImpl implements PassportMapper {

    @Override
    public PassportDto mapToDto(PassportEntity passportEntity) {
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

    @Override
    public PassportEntity mapToEntity(PassportDto passportDto) {
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
}
