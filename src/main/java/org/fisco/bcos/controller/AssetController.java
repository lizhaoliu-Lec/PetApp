package org.fisco.bcos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.AssetTransferDao;
import org.fisco.bcos.service.impl.AssetQueryService;
import org.fisco.bcos.service.impl.AssetRegisterService;
import org.fisco.bcos.service.impl.AssetTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class AssetController {
    @Autowired // must autowired, else bug
    private AssetRegisterService assetRegisterService;
    @Autowired
    private AssetQueryService assetQueryService;
    @Autowired
    private AssetTransferService assetTransferService;

    @RequestMapping(value = "/assetRegister", method = RequestMethod.POST)
    private JSONObject assetRegister(@RequestBody String input) throws Exception {
        JSONObject object = JSON.parseObject(input);
        String assetAccount = object.getString("asset_account");
        BigInteger amount = object.getBigInteger("amount");
        return assetRegisterService.register(assetAccount, amount);
    }

    @RequestMapping(value = "/assetQuery", method = RequestMethod.POST)
    private JSONObject assetQuery(@RequestBody String input) throws Exception {
        JSONObject object = JSON.parseObject(input);
        String assetAccount = object.getString("asset_account");
        return assetQueryService.query(assetAccount);
    }

    @RequestMapping(value = "/assetTransfer", method = RequestMethod.POST)
    private JSONObject assetTransfer(@RequestBody String input) throws Exception {
        JSONObject object = JSON.parseObject(input);
        String fromAccount = object.getString("from_account");
        String toAccount = object.getString("to_account");
        BigInteger amount = object.getBigInteger("amount");
        return assetTransferService.transfer(fromAccount, toAccount, amount);
    }

}
