package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IAssetRegisterService {
    JSONObject register(String assetAccount, BigInteger amount) throws Exception;
}
