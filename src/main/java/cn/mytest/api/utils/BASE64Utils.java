package cn.mytest.api.utils;

import java.io.IOException;

public class BASE64Utils {

    public static void main(String[] args) {
        System.out.println(encode("abc"));
    }

    /**
     * 编码
     * @param bstr
     * @return String
     */
    public static String encode(byte[] bstr){
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    public static String encode(String string){
        return encode(string.getBytes());
    }

    /**
     * 解码
     * @param str
     * @return string
     */
    public static byte[] decode(String str){
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer( str );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt;
    }
}
