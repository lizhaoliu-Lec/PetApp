package org.fisco.bcos.controller;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.service.impl.UserLoginService;
import org.fisco.bcos.service.impl.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private UserLoginService userLoginService;


    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    private JSONObject userRegister(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _pwd = object.getString("password");
        return userRegisterService.register(_id, _pwd);
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    private JSONObject userLogin(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _pwd = object.getString("password");
        return userLoginService.login(_id, _pwd);
    }
}
