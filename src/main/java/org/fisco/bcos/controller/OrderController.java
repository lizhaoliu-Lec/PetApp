package org.fisco.bcos.controller;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/userGetOrderByIndex", method = RequestMethod.POST)
    private JSONObject userGetOrderByIndex(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        BigInteger _order_index = object.getBigInteger("order_index");
        return orderService.userGetOrderByIndex(_id, _order_index);
    }

    @RequestMapping(value = "/userGetOrderIndex", method = RequestMethod.POST)
    private JSONObject userGetOrderIndex(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        return orderService.userGetOrderIndex(_id);
    }

    @RequestMapping(value = "/adminGetOrderByIndex", method = RequestMethod.POST)
    private JSONObject adminGetOrderByIndex(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        BigInteger _order_index = object.getBigInteger("order_index");
        return orderService.adminGetOrderByIndex(_order_index);
    }

    @RequestMapping(value = "/adminGetOrderIndex", method = RequestMethod.GET)
    private JSONObject adminGetOrderIndex() throws Exception {
        return orderService.adminGetOrderIndex();
    }

    @RequestMapping(value = "/adminGetReturnOrderIndex", method = RequestMethod.GET)
    private JSONObject adminGetReturnOrderIndex() throws Exception {
        return orderService.adminGetReturnOrderIndex();
    }


    @RequestMapping(value = "/applyForReturn", method = RequestMethod.POST)
    private JSONObject applyForReturn(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _order_id = object.getString("order_id");
        String _reason = object.getString("reason");
        return orderService.applyForReturn(_id, _order_id, _reason);
    }

    @RequestMapping(value = "/acceptReturn", method = RequestMethod.POST)
    private JSONObject acceptReturn(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _order_id = object.getString("order_id");
        return orderService.acceptReturn(_order_id);
    }

    @RequestMapping(value = "/rejectReturn", method = RequestMethod.POST)
    private JSONObject rejectReturn(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _order_id = object.getString("order_id");
        return orderService.rejectReturn(_order_id);
    }

    @RequestMapping(value = "/getReturnReasonByIndex", method = RequestMethod.POST)
    private JSONObject getReturnReasonByIndex(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        BigInteger _order_index = object.getBigInteger("order_index");
        return orderService.getReturnReasonByIndex(_order_index);
    }


}
