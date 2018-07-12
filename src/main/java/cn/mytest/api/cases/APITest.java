package cn.mytest.api.cases;


import cn.mytest.api.base.TestBase;
import org.testng.annotations.Test;

@Test
public class APITest extends TestBase{

    public void test01(){
        check.assertEquals ( 1, 1, "failed" );
    }
    public void test02(){
        check.assertEquals ( 1, 2, "failed" );
    }
}