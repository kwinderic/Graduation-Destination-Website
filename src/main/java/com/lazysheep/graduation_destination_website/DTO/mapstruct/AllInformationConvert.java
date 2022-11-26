package com.lazysheep.graduation_destination_website.DTO.mapstruct;

import com.lazysheep.graduation_destination_website.DTO.AllInformationResponseDTO;
import com.lazysheep.graduation_destination_website.DTO.MapResponseDTO;
import com.lazysheep.graduation_destination_website.entity.ContactInformation;
import com.lazysheep.graduation_destination_website.entity.GraduationInformation;
import com.lazysheep.graduation_destination_website.entity.PersonInformation;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface AllInformationConvert {
    AllInformationConvert INSATNCE = Mappers.getMapper(AllInformationConvert.class);

    @Mapping(target = "type", source = "accountType")
    PersonInformation PERSON_INFORMATION(AllInformationResponseDTO allInformationResponseDTO);

    ContactInformation CONTACT_INFORMATION(AllInformationResponseDTO allInformationResponseDTO);

    @Mapping(target = "type", source = "destinationType")
    GraduationInformation GRADUATION_INFORMATION(AllInformationResponseDTO allInformationResponseDTO);

//    @Mapping(target = "destinationType",source = "graduationInformation.type",qualifiedByName = "3ToAll")
//    ArrayList<AllInformationResponseDTO> ALL_INFORMATION_RESPONSE_DTO(List<ContactInformation> contactInformation, List<GraduationInformation> graduationInformation, List<PersonInformation> personInformation);


}