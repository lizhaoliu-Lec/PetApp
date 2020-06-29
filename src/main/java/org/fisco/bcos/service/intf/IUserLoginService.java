package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

public interface IUserLoginService {
    JSONObject login(String _id, String _pwd) throws Exception;
}
