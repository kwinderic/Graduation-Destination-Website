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
            String[] firstName = {"宋","亓","赵","钱","孙","李","周","吴","郑","王","冯","陈","楮","卫","蒋","沈","韩","杨", "朱","秦","尤","许","何","吕","施"};
            String[] lastName = {"家庆","天舒","小","明","丽","红","强","小丽","炎","燕","晓燕","洋","阳","庄","小庄","晓阳","小洋","山","进","明","小文","文","文丽","晓歌","小歌","歌","晓波","小波","小亮","晓亮","亮","品华","霞","红霞","晓炎","晓强","德华","华","城","诚","小明"};
            allInformationResponseDTO.setName(firstName[random.nextInt(20)]+lastName[random.nextInt(35)]);
            allInformationResponseDTO.setSex(random.nextInt(2));
            String[] major = {"计算机科学与技术","人工智能","数据科学与大数据技术"};
            int num = random.nextInt(140);String word;
            if(num<=90) word = major[0]; else if(num<=120) word = major[1];else word = major[2];
            allInformationResponseDTO.setMajor(word);
            allInformationResponseDTO.setAccountType(0);
            allInformationResponseDTO.setCollege("计算机科学与技术学院");
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
                String[] jobs = {"计算机图形学","计算机视觉","自然语言处理","嵌入式系统","可视化技术","无线网络","区块链"};
                if((destinationType[dt]%100)/10<3) {
                    String[] school = {"清华大学", "北京大学", "北京航空航天大学", "浙江大学", "上海交通大学", "中国科学院大学", "中国科学与技术大学", "西北交通大学","电子科技大学","山东大学","XX大学"};
                    String[] place = {"北京市","北京市","北京市","浙江省杭州市","上海市","北京市","安徽省合肥市","陕西省西安市","四川省成都市","山东省青岛市","广东省深圳市","广东省广州市","天津市","山东省潍坊市","山东省济南市","江苏省苏州市","江苏省南京市","重庆市","辽宁省大连市","浙江省宁波市","福建省厦门市","湖北省武汉市","黑龙江省哈尔滨市","辽宁省沈阳市","陕西省西安市","吉林省长春市","湖南省长沙市","福建省福州市","江西省南昌市","河南省郑州市","河北省石家庄市","广东省佛山市","广东省东莞市","江苏省无锡市","山东省烟台市"};
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
                    String[] school = {"香港大学", "澳门大学", "澳洲国立大学"};
                    String[] place = {"香港市","澳门市","澳大利亚市"};
                    num=random.nextInt(5);
                    if(num<3) dt=0;else if(num<4)dt=1;else dt=2;
                    allInformationResponseDTO.setPlace(place[dt]);
                    allInformationResponseDTO.setOrganization(school[dt]);
                    allInformationResponseDTO.setSalary((double) (10000+random.nextInt(300000)));
                }
                allInformationResponseDTO.setSalary(allInformationResponseDTO.getSalary()*(1.0-(2023- allInformationResponseDTO.getYear())/20.0));
                allInformationResponseDTO.setJobs(jobs[num]);
            }else{
                String[] company = {"腾讯","腾讯","阿里巴巴","阿里巴巴","字节跳动","米哈游","拼多多","微软","山大地纬","算能","浪潮","牛牛公司"};
                String[] place = {"广东省深圳市","北京市","浙江省杭州市","北京市","北京市","上海市","上海市","江苏省苏州市","山东省济南市","山东省青岛市","山东省青岛市","广东省深圳市","广东省广州市","天津市","山东省潍坊市","山东省济南市","江苏省苏州市","江苏省南京市","重庆市","辽宁省大连市","浙江省宁波市","福建省厦门市","湖北省武汉市","黑龙江省哈尔滨市","辽宁省沈阳市","陕西省西安市","吉林省长春市","湖南省长沙市","福建省福州市","江西省南昌市","河南省郑州市","河北省石家庄市","广东省佛山市","广东省东莞市","江苏省无锡市","山东省烟台市"};
                String[] jobs ={"算法工程师","前端开发工程师","后端开发工程师","测试开发工程师","游戏开发工程师"};
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
     * 地图接口
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
            if(theCity.length()!=3&&!theCity.equals("澳大利亚市")){
                theCity = (theCity.split("省"))[1];
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
     * 研究方向饼状图
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
     * 工作方向饼状图
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
     * 每个省里校友人数
     * @return
     */
    @GetMapping("/province")
    public List<CountDTO> province(){
        List<CountDTO> returnList = new ArrayList<>();
        List<GraduationInformation> graduationInformationList = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        for (GraduationInformation graduationInformation : graduationInformationList) {
            if(graduationInformation.getPlace().equals("澳大利亚市"))
                continue;
            if(graduationInformation.getPlace().length()!=3){
                graduationInformation.setPlace(graduationInformation.getPlace().split("省")[0]+"省");
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
     * 某省份各地市人数
     * @param province
     * @return
     */
    @GetMapping("/city")
    public List<CountDTO> city(@RequestParam("province")String province){
        List<CountDTO> countDTOList = new ArrayList<>();
        List<GraduationInformation> graduationInformations = graduationInformationService.lambdaQuery().eq(GraduationInformation::getStatus, 1).list();
        for (GraduationInformation graduationInformation : graduationInformations) {
            String[] place = graduationInformation.getPlace().split("省");
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
     * 毕业去向数据
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
        CountDTO w = CountDTO.builder().name("工作").value(key[0]).build();
        returnList.add(w);
        w = CountDTO.builder().name("考研").value(key[1]).build();
        returnList.add(w);
        w = CountDTO.builder().name("保研").value(key[2]).build();
        returnList.add(w);
        w = CountDTO.builder().name("出国").value(key[3]).build();
        returnList.add(w);
        w = CountDTO.builder().name("直博").value(key[4]).build();
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
            String[] type = {"工作","考研","保研","直博","出国"};

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
