package org.fisco.bcos.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.UserDao;
import org.fisco.bcos.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service(value = "UserService")
public class UserService implements IUserService {
    @Autowired
    UserDao userDao;

    @Override
    public JSONObject register(String _id, String _pwd, Boolean _is_admin) throws Exception {
        return userDao.register(_id, _pwd, _is_admin);
    }

    @Override
    public JSONObject login(String _id, String _pwd) throws Exception {
        return userDao.login(_id, _pwd);
    }

    @Override
    public JSONObject getBalance(String _id) throws Exception {
        return userDao.getBalance(_id);
    }

    @Override
    public JSONObject setBalance(String _id, BigInteger _balance) throws Exception {
        return userDao.setBalance(_id, _balance);
    }

    @Override
    public JSONObject isAdmin(String _id) throws Exception {
        return userDao.isAdmin(_id);
    }
}
