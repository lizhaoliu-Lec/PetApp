package org.fisco.bcos.controller;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.service.impl.MarketService;
import org.fisco.bcos.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class MarketController {
    @Autowired
    private MarketService marketService;

    @RequestMapping(value = "/getPetByIndex", method = RequestMethod.POST)
    private JSONObject getPetByIndex(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        BigInteger _index = object.getBigInteger("index");
        return marketService.getPetByIndex(_id, _index);
    }

    @RequestMapping(value = "/getPetIndex", method = RequestMethod.POST)
    private JSONObject getPetIndex(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        return marketService.getPetIndex(_id);
    }

    @RequestMapping(value = "/getPetForSale", method = RequestMethod.GET)
    private JSONObject getPetForSale() throws Exception {
        return marketService.getPetForSale();
    }

    @RequestMapping(value = "/createPet", method = RequestMethod.POST)
    private JSONObject createPet(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _type = object.getString("type");
        BigInteger _price = object.getBigInteger("price");
        String _name = object.getString("name");
        String _img = object.getString("url");
        String _intro = object.getString("introduction");
        return marketService.createPet(_id, _type, _price, _name, _img, _intro);
    }

    @RequestMapping(value = "/modifyPetInfo", method = RequestMethod.POST)
    private JSONObject modifyPetInfo(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _petId = object.getString("pet_id");
        String _type = object.getString("type");
        BigInteger _price = object.getBigInteger("price");
        String _name = object.getString("name");
        String _img = object.getString("url");
        String _intro = object.getString("introduction");
        return marketService.modifyPetInfo(_id, _petId, _name, _type, _price, _img, _intro);
    }

    @RequestMapping(value = "/buyPet", method = RequestMethod.POST)
    private JSONObject buyPet(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _petId = object.getString("pet_id");
        String _time = object.getString("time");
        return marketService.buyPet(_id, _petId, _time);
    }

    @RequestMapping(value = "/sellPet", method = RequestMethod.POST)
    private JSONObject sellPet(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _petId = object.getString("pet_id");
        return marketService.sellPet(_id, _petId);
    }

    @RequestMapping(value = "/cancelSellPet", method = RequestMethod.POST)
    private JSONObject cancelSellPet(@RequestBody String input) throws Exception {
        JSONObject object = JSONObject.parseObject(input);
        String _id = object.getString("account");
        String _petId = object.getString("pet_id");
        return marketService.cancelSellPet(_id, _petId);
    }
}
