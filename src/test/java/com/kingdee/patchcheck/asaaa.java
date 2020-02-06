package com.kingdee.patchcheck;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.patchcheck.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * description: asaaa <br>
 * date: 2020\1\16 0016 15:24 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * TODO
 */
public class asaaa {
    public static void main(String[] args) {
        List<List<String>> listOut = new ArrayList<>();
        User[] users = new User[5];
        User user1 =  new User();
        user1.setName("1111");
        user1.setId(6666);
        users[0] = user1;
        String jsonList = JSON.toJSONString(users);
        System.out.println(jsonList);
        JSONArray objects = JSON.parseArray(jsonList);
        JSONObject JSONObject = (JSONObject)   objects.get(0);

        System.out.println(JSONObject.get("id"));

    }
}
