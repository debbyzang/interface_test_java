package cn.mytest.api.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static void main(String[] args) {
        System.out.println(getTodayBefore(1));
        System.out.println(getTodayAfter(1));
        System.out.println(getToday());
    }

    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String abc = user_time + " 00:00:00 000";
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date d;
        try {
            d = sdf.parse(abc);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return sdf.format(new Date().getTime());
    }

    /**
     * 获取当天前几天的字符串
     *
     * @return
     */
    public static String getToday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当天前几天的字符串
     *
     * @param day
     * @return
     */
    public static String getTodayBefore(int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(getDateBefore(new Date(), day));
    }

    /**
     * 获取当天后几天的字符串
     *
     * @param day
     * @return
     */
    public static String getTodayAfter(int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(getDateAfter(new Date(), day));
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
}
