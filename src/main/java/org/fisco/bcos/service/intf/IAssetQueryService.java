package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;


public interface IAssetQueryService {
    JSONObject query(String assetAccount) throws Exception;
}
