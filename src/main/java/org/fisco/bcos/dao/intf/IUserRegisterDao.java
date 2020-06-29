package org.fisco.bcos.dao.intf;

import com.alibaba.fastjson.JSONObject;

// 用户注册
// 返回：0代表注册成功，1代表用户id重复
// function register(string _id, string _password) public returns (uint8)
public interface IUserRegisterDao {
    JSONObject userRegister(String _id, String _pwd) throws Exception;
}
