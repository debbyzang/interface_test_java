package cn.mytest.api.base;

import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import java.util.List;

public class CheckPoint extends Assertion {

    private int flag = 0;

    private String caseName = "";

    @Override
    public void onAssertFailure(IAssert assertCommand, AssertionError assertionError) {
        System.out.println("断言失败： 实际[" + assertCommand.getActual() + "] 预期[" + assertCommand.getExpected() + "]");
        flag = flag + 1;
    }

    @Override

    public void onAssertSuccess(IAssert assertCommand) {
        System.out.println("断言成功： 实际[" + assertCommand.getActual() + "] 预期[" + assertCommand.getExpected() + "]");
    }

    public void equals(boolean actual, boolean expected, String message) {

        try {

            assertEquals(actual, expected, message);

        } catch (Error e) {
        }

    }

    public void equals(long actual, long expected, String message) {

        try {

            assertEquals(actual, expected, message);

        } catch (Error e) {
        }

    }


    public void equals(String actual, String expected, String message) {

        try {

            assertEquals(actual, expected, message);

        } catch (Error e) {
        }

    }


    public void equals(int actual, int expected, String message) {

        try {

            assertEquals(actual, expected, message);

        } catch (Error e) {
        }

    }

    @Override
    public void fail(String msg) {
        try {
            assertEquals(true, false, msg);
        } catch (Error e) {
        }
    }

    /**
     * 需要比较的两个值，如果大于返回true，小于返回false
     */
    public boolean compare(long a, long b) {
        return (a - b) > 0;
    }

    public boolean compare(int a, int b) {
        return (a - b) > 0;
    }

    public void largerThan(int actual, int expected, String message) {
        try {
            boolean act = compare(actual, expected);
            assertEquals(act, true, message);
        } catch (Error e) {
        }
    }

    public void largerThan(long actual, long expected, String message) {
        try {
            boolean act = compare(actual, expected);
            assertEquals(act, true, message);
        } catch (Error e) {
        }
    }

    public void smallerThan(int actual, int expected, String message) {

        try {
            boolean act = compare(actual, expected);
            assertEquals(act, false, message);

        } catch (Error e) {
        }

    }


    public void equals(int actual, int expected) {

        try {

            assertEquals(actual, expected);

        } catch (Error e) {
        }

    }

    public void equalsNotNull(Object actual, String message) {

        try {

            this.assertNotNull(actual, message);

        } catch (Error e) {
        }

    }


    public void notEquals(String actual, String expected, String message) {

        try {
            assertNotEquals(actual, expected, message);

        } catch (Error e) {
        }

    }

    public void notEquals(int actual, int expected, String message) {

        try {

            assertNotEquals(actual, expected, message);

        } catch (Error e) {
        }

    }

    public void equals(List<String> actuals, String expected, String message) {

        if (actuals.size() != 0) {

            for (String actual : actuals) {

                try {

                    assertEquals(actual, expected, message);

                } catch (Error e) {
                }

            }

        } else System.out.println("检查点函数:实际结果 集合 对象为空!");

    }


    public void notEquals(List<String> actuals, String expected, String message) {

        if (actuals.size() != 0) {

            for (String actual : actuals) {

                try {

                    assertNotEquals(actual, expected, message);

                } catch (Error e) {
                }

            }

        } else System.out.println("检查点函数:实际结果 集合 对象为空!");

    }

    public void contains(String actual, String expected, String message) {

        if (actual.contains(expected)) {
            assertEquals(true, true, message);
        } else {
            assertEquals(false, true, message);
        }

    }

    public void notContains(String actual, String expected, String message) {

        if (actual.contains(expected)) {
            assertEquals(false, true, message);
        } else {
            assertEquals(true, true, message);
        }

    }

    public void result(String message) {
        assertEquals(flag, 0, "失败次数" + flag);

    }

    public void initFlag() {

        flag = 0;

    }


    public void isFailed(String message) {

        assertEquals(true, false, message);

        Reporter.log(caseName + ":" + message);

    }


}
