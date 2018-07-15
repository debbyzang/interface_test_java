package cn.mytest.api.cases;


import cn.mytest.api.base.TestBase;
import cn.mytest.api.utils.BizUtils;
import cn.mytest.api.utils.JsonUtil;
import org.testng.annotations.Test;


public class APITest extends TestBase{

    String workspaceId ;
    String projectId ;

    @Test
    public void loginTest(){
        setUpTest ();
    }

    @Test(dependsOnMethods = "loginTest")
    public void createWorkspaceTest(){
        String create = "2018-07-09T06:35:18.009Z";
        String description = "create default desc";
        String id = "";
        String lastModified = "2018-07-09T06:35:18.009Z";
        String name = "workspacename"+ System.currentTimeMillis ();
        String userid = userId;
        String result = BizUtils.createWorkspace ( create, description, id, lastModified, name, userid );
        String reName = JsonUtil.parseString ( result, "name" );
        check.assertEquals ( reName, name, "create workspace failed" );
        workspaceId = JsonUtil.parseString ( result, "id" );
    }

    @Test(dependsOnMethods = "createWorkspaceTest")
    public void updateWorkspaceTest(){
        String create = "2018-07-09T06:35:18.009Z";
        String description = "create default desc";
        String id = workspaceId;
        String lastModified = "2018-07-09T06:35:18.009Z";
        String name = "workspacenameupdate"+ System.currentTimeMillis ();
        String userid = userId;
        String result = BizUtils.updateWorkspace ( create, description, id, lastModified, name, userid );
        String reName = JsonUtil.parseString ( result, "name" );
        check.assertEquals ( reName, name, "update workspace failed" );
    }

    @Test(dependsOnMethods = "updateWorkspaceTest")
    public void createProjectTest(){
        String create = "2018-07-09T06:35:18.009Z";
        String description = "create default desc";
        String id = "";
        String lastModified = "2018-07-09T06:35:18.009Z";
        String name = "workspacenameupdate"+ System.currentTimeMillis ();
        String userid = "";
        String type = "DESIGN";
        String result = BizUtils.createProject ( create, description, id, lastModified, name, userid, type, workspaceId );
        String reName = JsonUtil.parseString ( result, "name" );
        check.assertEquals ( reName, name, "create project failed" );
        projectId = JsonUtil.parseString ( result, "id" );
    }

    @Test(dependsOnMethods = "createProjectTest")
    public void deleteProjectTest(){
        int code = BizUtils.deleteProject ( projectId );
        check.assertEquals ( code, 204, "delete project check failed" );
    }

    @Test(dependsOnMethods = "deleteProjectTest")
    public void deleteWorkspaceTest(){
        int code = BizUtils.deleteWorkspace ( workspaceId );
        check.assertEquals ( code, 204, "delete workspace check failed" );
    }

}