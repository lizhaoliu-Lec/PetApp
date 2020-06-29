package org.fisco.bcos.dao.intf;

import com.alibaba.fastjson.JSONObject;

public interface IUserDao {
    // 返回：0代表注册成功, 1代表用户id重复
    // function register(string _id, string _password) public returns (uint8)
    JSONObject register(String _id, String _pwd) throws Exception;

    // 用户登录
    // 返回：状态码（0代表登录成功，1代表用户id或密码不正确），用户地址(状态码为1时无效)
    // function login(string _id, string _password) public view returns (uint8, address) 
    JSONObject login(String _id, String _pwd) throws Exception;
}
