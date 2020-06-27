package org.fisco.bcos.dao.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IAssetQueryDao {
    JSONObject queryAssetAccount(String assetAccount) throws Exception;
}