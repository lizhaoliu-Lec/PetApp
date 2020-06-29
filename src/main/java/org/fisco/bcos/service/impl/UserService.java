package org.fisco.bcos.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.UserDao;
import org.fisco.bcos.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "UserService")
public class UserService implements IUserService {
    @Autowired
    UserDao userDao;

    @Override
    public JSONObject register(String _id, String _pwd) throws Exception {
        return userDao.register(_id, _pwd);
    }

    @Override
    public JSONObject login(String _id, String _pwd) throws Exception {
        return userDao.login(_id, _pwd);
    }
}
