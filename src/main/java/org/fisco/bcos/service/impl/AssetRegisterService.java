package org.fisco.bcos.service.impl;


import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.AssetRegisterDao;
import org.fisco.bcos.service.intf.IAssetRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service(value = "AssetRegisterService")
public class AssetRegisterService implements IAssetRegisterService {
    @Autowired // must autowired, else bug
    private AssetRegisterDao assetRegisterDao;

    @Override
    public JSONObject register(String assetAccount, BigInteger amount) throws Exception {
        return assetRegisterDao.registerAssetAccount(assetAccount, amount);
    }
}
