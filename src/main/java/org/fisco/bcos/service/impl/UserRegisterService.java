package org.fisco.bcos.service.impl;


import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.UserRegisterDao;
import org.fisco.bcos.service.intf.IUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "UserRegisterService")
public class UserRegisterService implements IUserRegisterService {
    @Autowired
    UserRegisterDao userRegisterDao;

    @Override
    public JSONObject register(String _id, String _pwd) throws Exception {
        return userRegisterDao.userRegister(_id, _pwd);
    }
}
