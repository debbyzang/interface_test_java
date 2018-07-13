package cn.mytest.api.utils;

import cn.mytest.api.base.TestBase;
import cn.mytest.api.modules.WorkSpace;
import com.alibaba.fastjson.JSON;

public class BizUtils extends TestBase {

    /**
     * get token
     *
     * @return
     */
    public static String login() {
        String postResult = HttpUtil.post ( baseURL + loginURL + "?username=" + usr + "&password=" + pwd, null );
        return ParseUtil.parseString ( postResult, "token" );
    }

    public static String getUserId() {
        String getResult = HttpUtil.get ( baseURL + getUserIdURL, null );
        return ParseUtil.parseString ( getResult, "id" );
    }

    public static WorkSpace workspace(String create, String description, String id, String lastModified, String name
            , String userId) {
        WorkSpace workSpace = new WorkSpace ( );
        workSpace.setCreated ( create );
        workSpace.setDescription ( description );
        workSpace.setId ( id );
        workSpace.setLastModified ( lastModified );
        workSpace.setName ( name );
        workSpace.setUserId ( userId );
        return workSpace;
    }

    public static WorkSpace project(String create, String description, String id, String lastModified, String name
            , String userId, String type, String workspaceId) {
        WorkSpace workSpace = new WorkSpace ( );
        workSpace.setCreated ( create );
        workSpace.setDescription ( description );
        workSpace.setId ( id );
        workSpace.setLastModified ( lastModified );
        workSpace.setName ( name );
        workSpace.setUserId ( userId );
        workSpace.setType ( type );
        workSpace.setWorkspaceId ( workspaceId );
        return workSpace;
    }

    public static String createWorkspace(String create, String description, String id, String lastModified, String name
            , String userId) {
        WorkSpace workSpace = workspace ( create, description, id, lastModified, name, userId );
        return HttpUtil.post ( baseURL + workspacesURL, JSON.toJSONString ( workSpace ) );
    }

    public static String updateWorkspace(String create, String description, String id, String lastModified, String name
            , String userId) {
        WorkSpace workSpace = workspace ( create, description, id, lastModified, name, userId );
        return HttpUtil.put ( baseURL + updateworkspacesURL + id, JSON.toJSONString ( workSpace ) );
    }

    public static String createProject(String create, String description, String id, String lastModified, String name
            , String userId, String type, String workspaceId){
        WorkSpace project = project ( create, description, id, lastModified, name, userId, type, workspaceId );
        return HttpUtil.post ( baseURL + designProjectURL, JSON.toJSONString ( project ) );
    }

    public static int deleteProject(String projectId){
        return HttpUtil.delete ( baseURL + designProjectURL + projectId );
    }

    public static int deleteWorkspace(String workspaceId){
        return HttpUtil.delete ( baseURL + updateworkspacesURL + workspaceId );
    }

}
