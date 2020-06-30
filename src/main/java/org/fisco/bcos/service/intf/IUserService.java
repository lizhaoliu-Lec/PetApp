package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IUserService {
    // 返回：0代表注册成功, 1代表用户id重复
    // function register(string _id, string _password, bool _isAdmin) public returns (uint8)
    JSONObject register(String _id, String _pwd, Boolean _is_admin) throws Exception;

    // 用户登录
    // 返回：状态码（0代表登录成功,1代表用户id或密码不正确）,用户地址(状态码为1时无效)
    // function login(string _id, string _password) public view returns (uint8, address) 
    JSONObject login(String _id, String _pwd) throws Exception;

    // 获取余额
    // function getBalance(string _id) public view returns (uint)
    JSONObject getBalance(String _id) throws Exception;

    // 设置余额
    // 返回是否设置成功
    // function setBalance(string _id, uint _balance) public returns (bool)
    JSONObject setBalance(String _id, BigInteger _balance) throws Exception;

    // 判断是否为管理员
    // function isAdmin(string _id) public view returns (bool)
    JSONObject isAdmin(String _id) throws Exception;
}
