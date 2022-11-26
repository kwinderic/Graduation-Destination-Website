package com.lazysheep.graduation_destination_website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazysheep.graduation_destination_website.entity.ContactInformation;
import com.lazysheep.graduation_destination_website.mapper.ContactInformationMapper;
import com.lazysheep.graduation_destination_website.service.ContactInformationService;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationServiceImpl extends ServiceImpl<ContactInformationMapper, ContactInformation>    implements ContactInformationService {
}
