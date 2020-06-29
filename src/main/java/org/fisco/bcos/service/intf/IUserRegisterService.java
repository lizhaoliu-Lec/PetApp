package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

public interface IUserRegisterService {
    JSONObject register(String _id, String _pwd) throws Exception;
}
