package com.qingfeng.test;

import android.content.Context;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${chuan.qi} on 2016/2/25.
 * email:chuan.qi@ikang.com
 */
public class StringUtils {
    /** 日期格式 */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTEN_TYPE_2 = "yyyy/MM/dd";

    /**
     * null、去掉首尾英文空格后长度为0、"null"或"NULL"字符串，均返回true。
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0 || "null".equals(str.toLowerCase()) || str.equals("[]") || "".equals(str));
    }

    public static boolean checkInput(String... str) {
        for (String s : str) {
            if (TextUtils.isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 日期字符串转换为date
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(str);
        return date;
    }

    /**
     * 日期字符串转换为date
     * @param str
     * @return
     */
    public static Date stringToDate(String str,String partten) {
        DateFormat format = new SimpleDateFormat(partten);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(str);
        return date;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * date转换为string
     * @param date
     * @return
     */
    public static String ConverToString(Date date) {
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        return df.format(date);
    }

    /**
     * date转换为string
     * @param date
     * @return
     */
    public static String ConverToString(Date date,String type) {
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }


    /**
     * string 转 calendar
     * @param str
     * @return
     * @throws ParseException
     */
    public static Calendar StringToCalendar(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        Date date = sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String StringPattern(String date, String oldPattern, String newPattern) {
        if (date == null || oldPattern == null || newPattern == null)
            return "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern) ;        // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern) ;        // 实例化模板对象
        Date d = null ;
        try{
            d = sdf1.parse(date) ;   // 将给定的字符串中的日期提取出来
        }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace() ;       // 打印异常信息
        }
        if(d == null)
            return "";
        else
            return sdf2.format(d);
    }

    public static Pattern IDNUMBER18_PTTN = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");
    public static Pattern IDNUMBER15_PTTN = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

    public static boolean checkName(String name){
        Pattern p = Pattern.compile("^[a-zA-Z\\u4e00-\\u9fa5][\\w\\u4e00-\\u9fa5-·\\s]*[^·\\s]{1,19}+$");
        Matcher matcher = p.matcher(name);
        return matcher.matches();
    }

    public static boolean checkCardPwd(String pwd){
        Pattern p = Pattern.compile("^\\d{6}$");
        Matcher matcher = p.matcher(pwd);
        return matcher.matches();
    }

    public static boolean checkLicense(String number){
        if(number.length() != 18 && number.length() != 15)
            return false;

        Matcher matcher;
        if(number.length() == 18){
            matcher = IDNUMBER18_PTTN.matcher(number);
            return matcher.matches();
        }
        else if(number.length() == 15){
            matcher = IDNUMBER15_PTTN.matcher(number);
            return matcher.matches();
        }
        return true;
    }

    public static boolean checkMobile(String mobile){
//        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1,5-9])|(170))\\d{8}$");
        Pattern p = Pattern.compile("^(1)\\d{10}$");
        Matcher matcher = p.matcher(mobile);
        return matcher.matches();
    }

    public static boolean checkEmail(String email){
        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

    private static final String URL_START = "http";
    public static String urlCheck(String url) {
        if (!StringUtils.isEmpty(url) && !url.startsWith(URL_START)){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(URL_START);
            stringBuffer.append(url);
            return (stringBuffer.toString());
        }
        else
            return url;
    }

    public static String toLeng2(int a){//前面加0，变为2位
        String b = a+"";
        for (int i = 0; i < 2-b.length(); i++) {
            b = "0"+b;
        }
        return b;
    }

    public static void main(String[] args) {
        Float f = 1f;
        System.out.println(f.toString());//1.0
        System.out.println(subZeroAndDot("1"));;  // 转换后为1
        System.out.println(subZeroAndDot("10"));;  // 转换后为10
        System.out.println(subZeroAndDot("1.0"));;  // 转换后为1
        System.out.println(subZeroAndDot("1.010"));;  // 转换后为1.01
        System.out.println(subZeroAndDot("1.01"));;  // 转换后为1.01
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static String dateFormat(Context context,String dateStr){
        Date date = StringUtils.stringToDate(dateStr);
        Calendar regDate = Calendar.getInstance();
        regDate.setTime(date);
        Calendar regNow = Calendar.getInstance();
        Calendar regTomorrow = Calendar.getInstance();
        regTomorrow.add(Calendar.DAY_OF_YEAR, 1);
        Calendar regAfter = Calendar.getInstance();
        regAfter.add(Calendar.DAY_OF_YEAR, 2);

        StringBuffer sb = new StringBuffer();
        String txt_date = StringPattern(dateStr,"yyyy-MM-dd","MM-dd");
        if(sameDate(regDate,regNow)){
            //今天
            sb.append(context.getString(R.string.util_date_now));
        }
        else if(sameDate(regDate,regTomorrow)){
            //明天
            sb.append(context.getString(R.string.util_date_tomorrow));
        }
        else if(sameDate(regDate,regAfter)){
            //后天
            sb.append(context.getString(R.string.util_date_after_tomorrow));
        }
        else {
            //其他
            switch (date.getDay()){
                case 0:
                    sb.append(context.getString(R.string.util_date_sunday));
                    break;

                case 1:
                    sb.append(context.getString(R.string.util_date_mondy));
                    break;

                case 2:
                    sb.append(context.getString(R.string.util_date_tuesday));
                    break;

                case 3:
                    sb.append(context.getString(R.string.util_date_wednesday));
                    break;

                case 4:
                    sb.append(context.getString(R.string.util_date_thursday));
                    break;

                case 5:
                    sb.append(context.getString(R.string.util_date_friday));
                    break;

                case 6:
                    sb.append(context.getString(R.string.util_date_saturday));
                    break;

                default:
                    break;
            }
        }

        sb.append(txt_date);
        return sb.toString();
    }

    private static boolean sameDate(Calendar cal, Calendar selectedDate) {
        return cal.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH)
                && cal.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
                && cal.get(Calendar.DAY_OF_MONTH) == selectedDate.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * null、长度为0、多个英文空格、"null"字符串
     * @params str
     * @return
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0 || "null".equals(str.toLowerCase()));
    }

    /**
     * 拼接字符串
     * @params strings
     * @return
     */
    public static String makeString(String... strings) {
        if (strings == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
            }
            return sb.toString();
        }
    }

    /**
     * 邮箱正则
     * @param mail
     * @return
     */
    public static boolean isEmail(String mail) {
        if (TextUtils.isEmpty(mail)) {
            return false;
        }
        String str = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(mail);
        return m.matches();
    }

    /**
     * 判断输入是否为卡号
     * @param input
     * @return
     */
    public static boolean isCardNum(String input){
        Pattern ptInput = Pattern.compile("^[0-9]{16}$");
        Matcher mtInput = ptInput.matcher(input);
        return mtInput.matches();
    }

    public static String cardNumFormat(String cardNum){
        String regex = "(.{4})";
        return  cardNum.replaceAll (regex, "$1 ");
    }
}
