package org.fisco.bcos.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.UserLoginDao;
import org.fisco.bcos.service.intf.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "UserLoginService")
public class UserLoginService implements IUserLoginService {
    @Autowired
    UserLoginDao userLoginDao;

    @Override
    public JSONObject login(String _id, String _pwd) throws Exception {
        return userLoginDao.userLogin(_id, _pwd);
    }
}
