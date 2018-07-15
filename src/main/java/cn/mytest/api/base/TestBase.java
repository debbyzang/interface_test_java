package cn.mytest.api.base;


import cn.mytest.api.utils.BizUtils;
import cn.mytest.api.utils.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    static JsonUtil jr = new JsonUtil( );

    //读取配置文件
    private static String testjson;
    private static String testURL;
    public static String token;
    public static String userId;

    static {
        try {
            testjson = IOUtils.toString(TestBase.class.getResourceAsStream("/config.json"));
            testURL = IOUtils.toString(TestBase.class.getResourceAsStream("/configURL.json"));
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    protected static String baseURL = jr.getJsonString ( testjson, "baseUrl" );
    protected static String usr = jr.getJsonString ( testjson, "usrname" );
    protected static String pwd = jr.getJsonString ( testjson, "password" );
    protected static String loginURL = jr.getJsonString ( testURL, "login" );
    protected static String workspacesURL = jr.getJsonString ( testURL, "workspaces" );
    protected static String updateworkspacesURL = jr.getJsonString ( testURL, "updateworkspaces" );
    protected static String getUserIdURL = jr.getJsonString ( testURL, "getUserId" );
    protected static String designProjectURL = jr.getJsonString ( testURL, "designProject" );


    protected static CheckPoint check = new CheckPoint ( );

    @BeforeSuite
    public void setUpTest(){
        token = BizUtils.login ();
        check.assertNotNull ( token ,"登录失败");
        userId = BizUtils.getUserId ();
    }

    @BeforeMethod
    public void setUp(Method method) {
        System.out.println ( "\n*****TestCase: " + method.getName ( ) + " start*****\n" );
    }

//    @AfterMethod
//    public void tearDown(Method method) {
//        System.out.println ( "\n*****TestCase: " + method.getName ( ) + " end*****\n" );
//    }


}
