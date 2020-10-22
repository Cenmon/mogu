package com.cenmo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Cenmo
 * @Date 2020-10-21 2020/10/21
 */
public class test {

    @Test
    public void test(){
        String str = "[{\"group\":\"组名1\",\"params\":[\"组员1\",\"组员2\"]},{\"group\":\"组名2\",\"params\":[\"组员1\",\"组员2\"]},{\"group\":\"组名3\",\"params\":[\"组员1\",\"组员2\",\"组员3\",\"组员4\"]}]";
        List<Map> maps = JSON.parseObject(str, new TypeReference<List<Map>>(){});

//        System.out.println(map);
        for (Map map : maps) {
            System.out.println(map);
            System.out.println((List)map.get("params"));

        }
    }
}
