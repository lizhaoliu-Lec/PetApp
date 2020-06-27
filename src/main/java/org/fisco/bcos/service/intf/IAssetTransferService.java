package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IAssetTransferService {
    JSONObject transfer(String fromAssetAccount, String toAssetAccount, BigInteger amount) throws Exception;

}
