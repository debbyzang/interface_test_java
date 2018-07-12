package cn.mytest.api.utils;

import java.util.Random;

public class TestTools {

    public static long UINT32_MAX = 4294967295l;
    public static long UINT32_MAX_1 = 4294967294l;
    public static long UINT32_MAX1 = 4294967296l;
    public static String UINT64_MAX = "18446744073709551615";
    public static String UINT64_MAX1 = "18446744073709551616";
    public static String INT64_MAX = "9223372036854775807";
    public static String INT64_MAX_1 = "9223372036854775806";
    public static String INT64_MAX1 = "9223372036854775808";

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成随机字符串
     * @param length 表示生成字符串的长度
     * @return
     */
    public static String getRandomLetter(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        return getRandom(base, length);
    }

    private static String getRandom(String base, int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomChinese(int length) { //length表示生成字符串的长度
        String base = "均才可传统的资产流通渠道有限几乎都依赖于大渠道行业大渠道由于垄断地位大幅增加费用从而导致流程成本显著提高小渠道及个人难以在流通环节发挥作用";
        return getRandom(base, length);
    }

        public static String getRandomMix(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789，、-均才可[]完成，;、。：（1）传统的资产流通渠道有限，几乎都依赖于大渠道，行业大渠道由于垄断地位大幅增加费用，从而导致流程成本显著提高，小渠道及个人难以在流通环节发挥作用。";
        return getRandom(base, length);
    }

}
