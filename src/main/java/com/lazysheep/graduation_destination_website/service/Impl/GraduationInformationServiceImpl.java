package com.lazysheep.graduation_destination_website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lazysheep.graduation_destination_website.entity.GraduationInformation;
import com.lazysheep.graduation_destination_website.mapper.GraduationInformationMapper;
import com.lazysheep.graduation_destination_website.service.GraduationInformationService;
import org.springframework.stereotype.Service;

@Service
public class GraduationInformationServiceImpl extends ServiceImpl<GraduationInformationMapper, GraduationInformation> implements GraduationInformationService {
}
