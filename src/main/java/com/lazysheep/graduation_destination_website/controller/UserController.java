package com.lazysheep.graduation_destination_website.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lazysheep.graduation_destination_website.DTO.*;
import com.lazysheep.graduation_destination_website.DTO.mapstruct.AllInformationConvert;
import com.lazysheep.graduation_destination_website.DTO.mapstruct.PersonInformationConvert;
import com.lazysheep.graduation_destination_website.common.ApiExceptionEnum;
import com.lazysheep.graduation_destination_website.common.AssertUtils;
import com.lazysheep.graduation_destination_website.common.R;
import com.lazysheep.graduation_destination_website.entity.ContactInformation;
import com.lazysheep.graduation_destination_website.entity.GraduationInformation;
import com.lazysheep.graduation_destination_website.entity.PersonInformation;
import com.lazysheep.graduation_destination_website.service.ContactInformationService;
import com.lazysheep.graduation_destination_website.service.GraduationInformationService;
import com.lazysheep.graduation_destination_website.service.PersonInformationService;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    PersonInformationService personInformationService;

    @Autowired
    ContactInformationService contactInformationService;

    @Autowired
    GraduationInformationService graduationInformationService;

    @GetMapping("/login")
    public R<PersonInformationResponseDTO> login(HttpServletRequest request, @RequestParam("studentId") String studentId,
                                              @RequestParam("password") String password){
        PersonInformation user = personInformationService.getOne(Wrappers.lambdaQuery(PersonInformation.class).eq(PersonInformation::getStudentId,studentId));

        AssertUtils.notNull(user, ApiExceptionEnum.USER_NOT_FOUND);

        password = DigestUtils.md5DigestAsHex(password.getBytes());

        AssertUtils.isTrue(password.equals(user.getPassword()),ApiExceptionEnum.PASSWORD_NOT_MATCHING);

        request.getSession().setAttribute("user",user.getStudentId());

        PersonInformationResponseDTO personInformationResponseDTO = PersonInformationConvert.INSTANCE.PERSON_INFORMATION_RESPONSE_DTO(user);

        return R.ok(personInformationResponseDTO);
    }

    @GetMapping("/autoLogin")
    public R<PersonInformationResponseDTO> autoLogin(HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("user");

        PersonInformation user = personInformationService.getOne(Wrappers.lambdaQuery(PersonInformation.class).eq(PersonInformation::getStudentId,userId));

        AssertUtils.notNull(user, ApiExceptionEnum.USER_NOT_FOUND);

        PersonInformationResponseDTO personInformationResponseDTO = PersonInformationConvert.INSTANCE.PERSON_INFORMATION_RESPONSE_DTO(user);

        return R.ok(personInformationResponseDTO);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
    }

    @PostMapping("/fuck")
    public void fuck(){

        List<ContactInformation> contactInformationList = new ArrayList<>();
        List<PersonInformation> personInformationList = new ArrayList<>();
        List<GraduationInformation> graduationInformationList = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            AllInformationResponseDTO allInformationResponseDTO = new AllInformationResponseDTO();
            Random random = new Random(System.currentTimeMillis()+i);
            allInformationResponseDTO.setYear(2014+random.nextInt(10));
            while(true){
                allInformationResponseDTO.setStudentId(String.valueOf((100000000L*(allInformationResponseDTO.getYear()-4))+130000+random.nextInt(400)));
                boolean flag=true;
                for (ContactInformation contactInformation : contactInformationList) {
                    if(contactInformation.getStudentId().equals(allInformationResponseDTO.getStudentId())){
                        flag=false;break;
                    }
                }
                if(flag==true)
                    break;
            }

            allInformationResponseDTO.setPassword(DigestUtils.md5DigestAsHex("12345".getBytes()));
            String[] firstName = {"???","???","???","???","???","???","???","???","???","???","???","???","???","???","???","???","???","???", "???","???","???","???","???","???","???"};
            String[] lastName = {"??????","??????","???","???","???","???","???","??????","???","???","??????","???","???","???","??????","??????","??????","???","???","???","??????","???","??????","??????","??????","???","??????","??????","??????","??????","???","??????","???","??????","??????","??????","??????","???","???","???","??????"};
            allInformationResponseDTO.setName(firstName[random.nextInt(20)]+lastName[random.nextInt(35)]);
            allInformationResponseDTO.setSex(random.nextInt(2));
            String[] major = {"????????????????????????","????????????","??????????????????????????????"};
            int num = random.nextInt(140);String word;
            if(num<=90) word = major[0]; else if(num<=120) word = major[1];else word = major[2];
            allInformationResponseDTO.setMajor(word);
            allInformationResponseDTO.setAccountType(0);
            allInformationResponseDTO.setCollege("??????????????????????????????");
            num = random.nextInt(10);
            if(num<=6) allInformationResponseDTO.setEduBg(0); else if(num<=8) allInformationResponseDTO.setEduBg(1);else allInformationResponseDTO.setEduBg(2);
            allInformationResponseDTO.setPhone(String.valueOf(13000000000L+100L*random.nextInt(69999999)));
            allInformationResponseDTO.setQq(String.valueOf(1L*random.nextInt(100000000)));
            allInformationResponseDTO.setWechat("wx"+allInformationResponseDTO.getPhone());
            allInformationResponseDTO.setEmail(allInformationResponseDTO.getQq()+"@163.com");
            Integer[] destinationType = {111,112,121,131,132,200};
            num = random.nextInt(38);int dt;
            if(num<5) dt=0;else if(num<6) dt=1;else if(num<12) dt=2;else if(num<13) dt=3;else if(num<14) dt=4;else dt=5;
            allInformationResponseDTO.setDestinationType(destinationType[dt]);
            if((destinationType[dt]/100) == 1){
                String[] jobs = {"??????????????????","???????????????","??????????????????","???????????????","???????????????","????????????","?????????"};
                if((destinationType[dt]%100)/10<3) {
                    String[] school = {"????????????", "????????????", "????????????????????????", "????????????", "??????????????????", "?????????????????????", "???????????????????????????", "??????????????????","??????????????????","????????????","XX??????"};
                    String[] place = {"?????????","?????????","?????????","??????????????????","?????????","?????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","?????????","??????????????????","??????????????????","??????????????????","??????????????????","?????????","??????????????????","??????????????????","??????????????????","??????????????????","????????????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","?????????????????????","??????????????????","??????????????????","??????????????????","??????????????????"};
                    num = random.nextInt(88);
                    dt=10;
                    if(num<1) dt=0;else if(num<3)dt=1;else if(num<7) dt=2;else if(num<11) dt=3;else if(num<15) dt=4;else if(num<27)dt=5;else if(num<31)dt=6;else if(num<35)dt=7;else if(num<39)dt=8;else dt=9;
                    allInformationResponseDTO.setOrganization(school[dt]);
                    if(dt!=10)
                        allInformationResponseDTO.setPlace(place[dt]);
                    else
                        allInformationResponseDTO.setPlace(place[num-65+10]);
                    allInformationResponseDTO.setSalary((double) (6000+random.nextInt(54000)));
                    num=random.nextInt(7);
                }else{
                    String[] school = {"????????????", "????????????", "??????????????????"};
                    String[] place = {"?????????","?????????","???????????????"};
                    num=random.nextInt(5);
                    if(num<3) dt=0;else if(num<4)dt=1;else dt=2;
                    allInformationResponseDTO.setPlace(place[dt]);
                    allInformationResponseDTO.setOrganization(school[dt]);
                    allInformationResponseDTO.setSalary((double) (10000+random.nextInt(300000)));
                }
                allInformationResponseDTO.setSalary(allInformationResponseDTO.getSalary()*(1.0-(2023- allInformationResponseDTO.getYear())/20.0));
                allInformationResponseDTO.setJobs(jobs[num]);
            }else{
                String[] company = {"??????","??????","????????????","????????????","????????????","?????????","?????????","??????","????????????","??????","??????","????????????"};
                String[] place = {"??????????????????","?????????","??????????????????","?????????","?????????","?????????","?????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","?????????","??????????????????","??????????????????","??????????????????","??????????????????","?????????","??????????????????","??????????????????","??????????????????","??????????????????","????????????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","??????????????????","?????????????????????","??????????????????","??????????????????","??????????????????","??????????????????"};
                String[] jobs ={"???????????????","?????????????????????","?????????????????????","?????????????????????","?????????????????????"};
                num = random.nextInt(36);
                if(num>=11)
                    allInformationResponseDTO.setOrganization(company[11]);
                else
                    allInformationResponseDTO.setOrganization(company[num]);
                allInformationResponseDTO.setPlace(place[num]);
                num = random.nextInt(5);
                allInformationResponseDTO.setJobs(jobs[num]);
                if(num==0||num==4)
                    allInformationResponseDTO.setSalary((double) (200000+random.nextInt(599999)));
                else if(num==3)
                    allInformationResponseDTO.setSalary((double) (100000+random.nextInt(299999)));
                else
                    allInformationResponseDTO.setSalary((double) (100000+random.nextInt(399999)));
                allInformationResponseDTO.setSalary(allInformationResponseDTO.getSalary()*(1.0-(2023- allInformationResponseDTO.getYear())/20.0));
            }
            contactInformationList.add(AllInformationConvert.INSATNCE.CONTACT_INFORMATION(allInformationResponseDTO));
            personInformationList.add(AllInformationConvert.INSATNCE.PERSON_INFORMATION(allInformationResponseDTO));
            graduationInformationList.add(AllInformationConvert.INSATNCE.GRADUATION_INFORMATION(allInformationResponseDTO));
        }
        personInformationService.saveBatch(personInformationList);
        contactInformationService.saveBatch(contactInformationList);
        graduationInformationService.saveBatch(graduationInformationList);
    }

    @DeleteMapping("/clear")
    public void clear(){
        List<ContactInformation> list = contactInformationService.lambdaQuery().eq(ContactInformation::getStatus, 1).list();
        List<PersonInformation> list1 = personInformationService.lambdaQuery().eq(PersonInformation::getStatus, 1).list();
        List<GraduationInformation> list2 = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        contactInformationService.removeBatchByIds(list);
        personInformationService.removeBatchByIds(list1);
        graduationInformationService.removeBatchByIds(list2);
    }

    /**
     * ????????????
     * @param city
     * @return
     */
    @GetMapping("/map")
    public List<MapResponseDTO> userMap(@RequestParam("city")String city){
        List<MapResponseDTO> mapResponseDTOList = new ArrayList<>();
        ArrayList<AllInformationResponseDTO> allInformationResponseDTOList= new ArrayList<>();
        List<ContactInformation> contactInformationList = contactInformationService.lambdaQuery().eq(ContactInformation::getStatus, 1).list();
        List<PersonInformation> personInformationList = personInformationService.lambdaQuery().eq(PersonInformation::getStatus, 1).list();
        List<GraduationInformation> graduationInformationList = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        for (ContactInformation contactInformation : contactInformationList) {
            String studentId = contactInformation.getStudentId();
            PersonInformation personInformation = new PersonInformation();
            GraduationInformation graduationInformation = new GraduationInformation();
            for (PersonInformation personInformation1 : personInformationList) {
                if(studentId.equals(personInformation1.getStudentId())){
                    personInformation = personInformation1;break;
                }
            }
            for (GraduationInformation information : graduationInformationList) {
                if(studentId.equals(information.getStudentId())){
                    graduationInformation = information;break;
                }
            }
            AllInformationResponseDTO informationResponseDTO = AllInformationResponseDTO.builder().studentId(studentId).name(personInformation.getName())
                    .sex(personInformation.getSex()).year(personInformation.getYear())
                    .email(contactInformation.getEmail()).phone(contactInformation.getPhone())
                    .jobs(graduationInformation.getJobs()).organization(graduationInformation.getOrganization())
                    .place(graduationInformation.getPlace()).salary(graduationInformation.getSalary())
                    .destinationType(graduationInformation.getType()).build();
            allInformationResponseDTOList.add(informationResponseDTO);
        }

        List<AllInformationResponseDTO> allInformationResponseDTOList1 = new ArrayList<>();
        for (AllInformationResponseDTO allInformationResponseDTO : allInformationResponseDTOList) {
            String theCity = allInformationResponseDTO.getPlace();
            if(theCity.length()!=3&&!theCity.equals("???????????????")){
                theCity = (theCity.split("???"))[1];
            }
            if(theCity.equals(city))
                allInformationResponseDTOList1.add(allInformationResponseDTO);
        }
        allInformationResponseDTOList = (ArrayList<AllInformationResponseDTO>) allInformationResponseDTOList1;
        for (AllInformationResponseDTO allInformationResponseDTO : allInformationResponseDTOList) {
            String companyName = allInformationResponseDTO.getOrganization();
            boolean flag=false;
            for (MapResponseDTO mapResponseDTO : mapResponseDTOList) {
                if(mapResponseDTO.getCompanyName().equals(companyName)){
                    flag=true;break;
                }
            }
            if(!flag){
                MapResponseDTO mapResponseDTO = new MapResponseDTO();
                mapResponseDTO.setCompanyName(companyName);
                mapResponseDTOList.add(mapResponseDTO);
            }
            for (MapResponseDTO mapResponseDTO : mapResponseDTOList) {
                if(mapResponseDTO.getCompanyName().equals(companyName)){
                    mapResponseDTO.setNumber(mapResponseDTO.getNumber()+1);
                    Alumnus build = Alumnus.builder().name(allInformationResponseDTO.getName())
                            .year(allInformationResponseDTO.getYear())
                            .jobs(allInformationResponseDTO.getJobs())
                            .salary(allInformationResponseDTO.getSalary())
                            .phone(allInformationResponseDTO.getPhone())
                            .email(allInformationResponseDTO.getEmail()).build();
                    if(mapResponseDTO.getAlumnus()==null)
                        mapResponseDTO.setAlumnus(new ArrayList<>());
                    mapResponseDTO.getAlumnus().add(build);
                    break;
                }
            }
        }
        return mapResponseDTOList;
    }

    /**
     * ?????????????????????
     * @param year
     * @return
     */
    @GetMapping("/researchdata")
    public List<CountDTO> researchData(@RequestParam("year")Integer year){

        List<GraduationInformation> graduationInformationList = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        List<CountDTO> list = new ArrayList<>();
        List<PersonInformation> personInformationList = personInformationService.lambdaQuery().eq(PersonInformation::getStatus, 1).eq(PersonInformation::getYear,year).list();
        for (GraduationInformation graduationInformation : graduationInformationList) {
            if(graduationInformation.getType().equals(200))
                continue;
            boolean flag=false;
            for (PersonInformation personInformation : personInformationList) {
                if(personInformation.getStudentId().equals(graduationInformation.getStudentId())){
                    flag=true;break;
                }
            }
            if(!flag)
                continue;
            flag=false;
            for (CountDTO researchDataResponseDTO : list) {
                if(researchDataResponseDTO.getName().equals(graduationInformation.getJobs())){
                    researchDataResponseDTO.setValue(researchDataResponseDTO.getValue()+1);
                    flag=true;break;
                }
            }
            if(!flag){
                CountDTO researchDataResponseDTO = new CountDTO();
                researchDataResponseDTO.setValue(1);
                researchDataResponseDTO.setName(graduationInformation.getJobs());
                list.add(researchDataResponseDTO);
            }

        }
        return list;
    }

    /**
     * ?????????????????????
     * @param year
     * @return
     */
    @GetMapping("/workdata")
    public List<CountDTO> workData(@RequestParam("year")Integer year){

        List<GraduationInformation> graduationInformationList = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        List<CountDTO> list = new ArrayList<>();
        List<PersonInformation> personInformationList = personInformationService.lambdaQuery().eq(PersonInformation::getStatus, 1).eq(PersonInformation::getYear,year).list();
        for (GraduationInformation graduationInformation : graduationInformationList) {
            if(!graduationInformation.getType().equals(200))
                continue;
            boolean flag=false;
            for (PersonInformation personInformation : personInformationList) {
                if(personInformation.getStudentId().equals(graduationInformation.getStudentId())){
                    flag=true;break;
                }
            }
            if(!flag)
                continue;
            flag=false;
            for (CountDTO researchDataResponseDTO : list) {
                if(researchDataResponseDTO.getName().equals(graduationInformation.getJobs())){
                    researchDataResponseDTO.setValue(researchDataResponseDTO.getValue()+1);
                    flag=true;break;
                }
            }
            if(!flag){
                CountDTO researchDataResponseDTO = new CountDTO();
                researchDataResponseDTO.setValue(1);
                researchDataResponseDTO.setName(graduationInformation.getJobs());
                list.add(researchDataResponseDTO);
            }

        }
        return list;
    }

    /**
     * ????????????????????????
     * @return
     */
    @GetMapping("/province")
    public List<CountDTO> province(){
        List<CountDTO> returnList = new ArrayList<>();
        List<GraduationInformation> graduationInformationList = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        for (GraduationInformation graduationInformation : graduationInformationList) {
            if(graduationInformation.getPlace().equals("???????????????"))
                continue;
            if(graduationInformation.getPlace().length()!=3){
                graduationInformation.setPlace(graduationInformation.getPlace().split("???")[0]+"???");
            }
            boolean flag = false;
            for (CountDTO countDTO : returnList) {
                if(countDTO.getName().equals(graduationInformation.getPlace())){
                    countDTO.setValue(countDTO.getValue()+1);
                    flag = true;break;
                }
            }
            if(!flag){
                CountDTO build = CountDTO.builder().name(graduationInformation.getPlace()).value(1).build();
                returnList.add(build);
            }
        }
        return returnList;
    }

    /**
     * ????????????????????????
     * @param province
     * @return
     */
    @GetMapping("/city")
    public List<CountDTO> city(@RequestParam("province")String province){
        List<CountDTO> countDTOList = new ArrayList<>();
        List<GraduationInformation> graduationInformations = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        for (GraduationInformation graduationInformation : graduationInformations) {
            String[] place = graduationInformation.getPlace().split("???");
            if(!place[0].equals(province))
                continue;
            if(place.length==1)
                graduationInformation.setPlace(place[0]);
            else
                graduationInformation.setPlace(place[1]);
            boolean flag=false;
            for (CountDTO countDTO : countDTOList) {
                if(countDTO.getName().equals(graduationInformation.getPlace())){
                    countDTO.setValue(countDTO.getValue()+1);
                    flag=true;
                    break;
                }
            }
            if(!flag){
                CountDTO build = CountDTO.builder().value(0).name(graduationInformation.getPlace()).build();
                countDTOList.add(build);
            }
        }
        return countDTOList;
    }

    /**
     * ??????????????????
     * @param year
     * @return
     */
    @GetMapping("/graduate")
    public List<CountDTO> graduate(@RequestParam("year")Integer year){
        List<CountDTO> returnList = new ArrayList<>();
        List<GraduationInformation> graduationInformationList = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        Integer[] key = new Integer[6];
        Arrays.fill(key, 0);
        List<PersonInformation> personInformations = personInformationService.lambdaQuery().eq(PersonInformation::getStatus, 1).list();
        for (GraduationInformation graduationInformation : graduationInformationList) {
            boolean flag=false;
            for (PersonInformation personInformation : personInformations) {
                if(personInformation.getStudentId().equals(graduationInformation.getStudentId())){
                    flag=(personInformation.getYear().equals(year));break;
                }
            }
            if(!flag)
                continue;
            switch (graduationInformation.getType()){
                case 111:
                    key[2]++;
                    break;
                case 112:
                    key[4]++;
                    break;
                case 200:
                    key[0]++;
                    break;
                case 121:
                    key[1]++;
                    break;
                default:
                    key[3]++;
                    break;
            }

        }
        CountDTO w = CountDTO.builder().name("??????").value(key[0]).build();
        returnList.add(w);
        w = CountDTO.builder().name("??????").value(key[1]).build();
        returnList.add(w);
        w = CountDTO.builder().name("??????").value(key[2]).build();
        returnList.add(w);
        w = CountDTO.builder().name("??????").value(key[3]).build();
        returnList.add(w);
        w = CountDTO.builder().name("??????").value(key[4]).build();
        returnList.add(w);
        return returnList;
    }

    @GetMapping("/allyear")
    public Map<String, Map<Integer,Integer>> allyear(){
        Map<String,Map<Integer,Integer>> result = new HashMap<>();
        int[][] count = new int[5][10];
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count.length; j++) {
                count[i][j]=0;
            }
        }
        List<GraduationInformation> graduationInformationList = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        List<PersonInformation> personInformationList = personInformationService.lambdaQuery().eq(PersonInformation::getStatus, 1).list();

        for (GraduationInformation graduationInformation : graduationInformationList) {
            for (PersonInformation personInformation : personInformationList) {
                if(graduationInformation.getStudentId().equals(personInformation.getStudentId())){
                    int key;
                    switch (graduationInformation.getType()){
                        case  200:
                            key=0;
                            break;
                        case 121:
                            key=1;
                            break;
                        case 111:
                            key=2;
                            break;
                        case 112:
                            key=3;
                            break;
                        default:
                            key=4;
                    }
                    count[key][personInformation.getYear()-2014]++;
                }
            }
        }
        for (int i = 0; i < count.length; i++) {
            String[] type = {"??????","??????","??????","??????","??????"};

            Map<Integer,Integer> yearMap = new HashMap<>();
            for (int j = 0; j < count[i].length; j++) {
                if(count[i][j]!=0){
                    yearMap.put(j+2014,count[i][j]);
                }
            }
            result.put(type[i],yearMap);
        }
        return result;
    }


}
