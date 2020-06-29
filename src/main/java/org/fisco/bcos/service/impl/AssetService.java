package org.fisco.bcos.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.AssetDao;
import org.fisco.bcos.service.intf.IAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service(value = "AssetService")
public class AssetService implements IAssetService {
    @Autowired
    AssetDao assetDao;

    @Override
    public JSONObject register(String assetAccount, BigInteger amount) throws Exception {
        return assetDao.register(assetAccount, amount);
    }

    @Override
    public JSONObject query(String assetAccount) throws Exception {
        return assetDao.query(assetAccount);
    }

    @Override
    public JSONObject transfer(String fromAssetAccount, String toAssetAccount, BigInteger amount) throws Exception {
        return assetDao.transfer(fromAssetAccount, toAssetAccount, amount);
    }
}
