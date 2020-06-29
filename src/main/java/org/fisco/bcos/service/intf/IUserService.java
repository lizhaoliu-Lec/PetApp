package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

public interface IUserService {
    JSONObject register(String _id, String _pwd) throws Exception;

    JSONObject login(String _id, String _pwd) throws Exception;
}
