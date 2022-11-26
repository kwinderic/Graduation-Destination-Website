package com.lazysheep.graduation_destination_website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazysheep.graduation_destination_website.entity.PersonInformation;
import com.lazysheep.graduation_destination_website.mapper.PersonInformationMapper;
import com.lazysheep.graduation_destination_website.service.PersonInformationService;
import org.springframework.stereotype.Service;

@Service
public class PersonInformationServiceImpl extends ServiceImpl<PersonInformationMapper, PersonInformation> implements PersonInformationService {
}
