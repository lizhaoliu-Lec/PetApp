package org.fisco.bcos.dao.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IAssetTransferDao {
    JSONObject assetTransfer(String fromAssetAccount, String toAssetAccount, BigInteger amount) throws Exception;
}
