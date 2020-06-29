package org.fisco.bcos.controller;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    private JSONObject userRegister(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _pwd = object.getString("password");
        return userService.register(_id, _pwd);
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    private JSONObject userLogin(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _pwd = object.getString("password");
        return userService.login(_id, _pwd);
    }

    @RequestMapping(value = "/userGetBalance", method = RequestMethod.POST)
    private JSONObject userGetBalance(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        return userService.getBalance(_id);
    }

    @RequestMapping(value = "/userSetBalance", method = RequestMethod.POST)
    private JSONObject userSetBalance(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        BigInteger _balance = object.getBigInteger("balance");
        return userService.setBalance(_id, _balance);
    }

    @RequestMapping(value = "/userIsAdmin", method = RequestMethod.POST)
    private JSONObject userIsAdmin(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        return userService.isAdmin(_id);
    }
}
