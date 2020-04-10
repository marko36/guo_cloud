package com.jc.cloud.school.util;

import com.jc.cloud.school.entity.Activity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 开发测试使用，批量插入数据
 * @Author chenjian
 * @Date 2019/6/20
 **/
public class MySqlBulkInsert {

    public List<Activity> bulkInsert(int num){

        List<Activity> activities = new ArrayList<>();
        Random ra =new Random();
        for(int i = 0;i<num;i++){
            Activity activity = new Activity();
            String catalog = "/activity/"+this.get10UUID()+"/";

            activity.setTitle("查询数据库测试"+i);
            activity.setCover(catalog+"cover.jpg");
            activity.setPresentation(catalog+"presentation.jpg");
            activity.setSchedule(catalog+"schedule.jpg");
            activity.setLecturer(catalog+"lecturer.jpg");
            activity.setPrevious(catalog+"previous.jpg");
            activity.setSite(this.getRandomSite());
            activity.setBeginTime(LocalDateTime.of(this.getRandomDate(),this.getRandomTime()));
            activity.setEndTime(LocalDateTime.of(this.getRandomDate(),this.getRandomTime()));
            activity.setDeadline(LocalDateTime.of(this.getRandomDate(),this.getRandomTime()));
            activity.setNumberNum((ra.nextInt(10)+1)*100);
            activity.setApplyNumber(ra.nextInt(8000));
            activity.setPrice(new BigDecimal((ra.nextInt(100)+1)*100));
            activity.setPhone(this.getTel());
            activity.setVersion(1);
            activity.setDelFlag(true);

            activity.setCreateBy("admin");
            activity.setCreateTime(LocalDateTime.of(this.getRandomDate(),this.getRandomTime()));
            activities.add(activity);
        }
        return activities;
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }


    private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公";
    private static String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽";
    private static String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    /**
     * 返回中文姓名
     */
    private static String getChineseName() {
        int index=getNum(0, firstName.length()-1);
        String first=firstName.substring(index, index+1);
        int sex=getNum(0,1);
        String str = null;
        int length = 1;
        if(sex==0){
            str=girl;
            length=girl.length();
        }else {
            str = boy;
            length=boy.length();
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }




    /**
     * 返回手机号码
     */
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+thrid;
    }


    public String getRandomSite(){
        String[] site = {"北京市","天津市","上海市","重庆市","河北省","山西省","辽宁省","吉林省","黑龙江省","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","海南省","四川省","贵州省","云南省","陕西省","甘肃省"};
        Random ra =new Random();
        return site[ra.nextInt(site.length)];
    }

    public String get5UUID(){
        return UUID.randomUUID().toString().substring(0,5).replaceAll("-","");
    }

    public String get10UUID(){
        return UUID.randomUUID().toString().substring(0,10).replaceAll("-","");
    }

    public LocalTime getRandomTime(){
        Random ra =new Random();
        return LocalTime.of(ra.nextInt(24),ra.nextInt(60),ra.nextInt(60));
    }
    public LocalDate getRandomDate(){
        Random ra =new Random();
        LocalDate ld = LocalDate.of(2000+ra.nextInt(30),ra.nextInt(9)+3,ra.nextInt(30)+1);
        if(ld.getMonthValue() == 2){
            ld.plusMonths(1);
        }
        return ld;
    }

}
