package com.kingdee.patchcheck;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.patchcheck.model.User;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
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
    public static void main(String[] args) throws SocketException, UnknownHostException {


        System.out.println(getLocalIP());

    }
    public static String getLocalIP() throws UnknownHostException, SocketException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
