package org.fisco.bcos.service.impl;


import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.AssetQueryDao;
import org.fisco.bcos.service.intf.IAssetQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "AssetQueryService")
public class AssetQueryService implements IAssetQueryService {
    @Autowired // must autowired, else bug
    private AssetQueryDao assetQueryDao;

    @Override
    public JSONObject query(String assetAccount) throws Exception {
        return assetQueryDao.queryAssetAccount(assetAccount);
    }
}
