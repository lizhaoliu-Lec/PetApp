package org.fisco.bcos.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.AssetTransferDao;
import org.fisco.bcos.service.intf.IAssetTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service(value = "AssetTransferService")
public class AssetTransferService implements IAssetTransferService {
    @Autowired
    AssetTransferDao assetTransferDao;

    @Override
    public JSONObject transfer(String fromAssetAccount, String toAssetAccount, BigInteger amount) throws Exception {
        return assetTransferDao.assetTransfer(fromAssetAccount, toAssetAccount, amount);
    }
}
