import cn.mytest.api.base.TestBase;
import cn.mytest.api.utils.HttpUtil;
import org.testng.annotations.Test;

@Test
public class TestUtil extends TestBase {


    public static void getUserId() {
        String getResult = HttpUtil.get ( baseURL + getUserIdURL, null );
        System.out.println ( "getResult = " + getResult );
    }
}
