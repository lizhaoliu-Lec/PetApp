package org.fisco.bcos.dao.intf;

import com.alibaba.fastjson.JSONObject;

// 用户登录
// 返回：状态码（0代表登录成功，1代表用户id或密码不正确），用户地址(状态码为1时无效)
// function login(string _id, string _password) public view returns (uint8, address) 
public interface IUserLoginDao {
    JSONObject userLogin(String _id, String _pwd) throws Exception;
}
