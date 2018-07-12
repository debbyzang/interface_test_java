package cn.mytest.api.base;


import cn.mytest.api.utils.JsonReader;
import cn.mytest.api.utils.TimeUtils;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    static JsonReader jr = new JsonReader ( );

    //读取配置文件
    private static String testjson;

    static {
        try {
            testjson = IOUtils.toString(TestBase.class.getResourceAsStream("/config.json"));
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    protected static String baseUrl = jr.getJsonString ( testjson, "baseUrl" );


    protected static CheckPoint check = new CheckPoint ( );

    @AfterMethod
    public void tearDown(Method method) {
        System.out.println ( "\n*****TestCase: " + method.getName ( ) + " end*****\n" );
        System.out.println ( TimeUtils.getTime ( ) );
    }


}
