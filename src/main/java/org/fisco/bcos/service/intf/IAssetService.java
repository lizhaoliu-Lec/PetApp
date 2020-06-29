package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IAssetService {
    JSONObject register(String assetAccount, BigInteger amount) throws Exception;

    JSONObject query(String assetAccount) throws Exception;

    JSONObject transfer(String fromAssetAccount, String toAssetAccount, BigInteger amount) throws Exception;
}
