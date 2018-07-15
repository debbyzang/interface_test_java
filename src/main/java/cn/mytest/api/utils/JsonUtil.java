package cn.mytest.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;


public class JsonUtil {


    public String getJsonString(String path, String key){
        JSONObject co = JSON.parseObject(path);
        String base = JSON.parseObject(co.toJSONString()).getString(key);
        return base;
    }


    public static String parseString(String result, String key) {
        if (result.contains(key)) {
            return JSON.parseObject(result).getString(key);
        } else {
            System.out.println (" key: " + key + " doesn't exist");
            return null;
        }
    }

}
