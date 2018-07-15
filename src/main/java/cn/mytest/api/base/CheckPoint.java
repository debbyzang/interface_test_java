package cn.mytest.api.base;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

public class CheckPoint extends Assertion {


    @Override
    public void onAssertFailure(IAssert assertCommand, AssertionError assertionError) {
        System.out.println("断言失败： 实际[" + assertCommand.getActual() + "] 预期[" + assertCommand.getExpected() + "]");
    }

    @Override

    public void onAssertSuccess(IAssert assertCommand) {
        System.out.println("断言成功： 实际[" + assertCommand.getActual() + "] 预期[" + assertCommand.getExpected() + "]");
    }


    /*@Override
    public void fail(String msg) {
        try {
            assertEquals(true, false, msg);
        } catch (Error e) {
        }
    }
*/
}
