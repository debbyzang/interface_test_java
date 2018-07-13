package cn.mytest.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by Debby on 2017/6/7.
 */
public class ParseUtil {

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static String parseString(String result, String key) {
        if (result.contains(key)) {
            return JSON.parseObject(result).getString(key);
        } else {
            System.out.println (" key: " + key + " doesn't exist");
            return null;
        }
    }

    public static Integer parseInt(String result, String key) {
        return JSON.parseObject(result).getInteger(key);
    }

    public static String parseArray(String results, Integer index) {
        JSONArray jsonArray = JSON.parseArray(results);
        return jsonArray.get(index).toString();
    }

    public static List<Map> parseToListMap(String metadatas) {
        return JSONArray.parseArray(metadatas, Map.class);
    }

    public static JSONArray parseToArray(String result) {
        return JSON.parseArray(result);
    }

    /**
     * 将字符串转成JSONArray
     * @param result
     * @return
     */
    public static JSONArray parsetoJSONArray(String result){
        JSONArray assets = null;
        if (result != null) {
             assets = JSON.parseArray(result);
        }
        return assets;
    }

    public static Long parseLong(String result, String key) {
        return JSON.parseObject(result).getLong(key);
    }

}
