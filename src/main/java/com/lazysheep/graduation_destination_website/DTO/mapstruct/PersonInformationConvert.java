package com.lazysheep.graduation_destination_website.DTO.mapstruct;

import com.lazysheep.graduation_destination_website.DTO.PersonInformationResponseDTO;
import com.lazysheep.graduation_destination_website.entity.PersonInformation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonInformationConvert {
    PersonInformationConvert INSTANCE = Mappers.getMapper(PersonInformationConvert.class);

    PersonInformationResponseDTO PERSON_INFORMATION_RESPONSE_DTO(PersonInformation personInformation);
}
