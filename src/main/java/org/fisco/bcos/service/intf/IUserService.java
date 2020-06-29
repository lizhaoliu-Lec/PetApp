package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IUserService {
    JSONObject register(String _id, String _pwd) throws Exception;

    JSONObject login(String _id, String _pwd) throws Exception;

    JSONObject getBalance(String _id) throws Exception;

    JSONObject setBalance(String _id, BigInteger _balance) throws Exception;

    JSONObject isAdmin(String _id) throws Exception;
}
