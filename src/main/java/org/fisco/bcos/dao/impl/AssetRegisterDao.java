package org.fisco.bcos.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.blockService.AssetService;
import org.fisco.bcos.contract.Asset;
import org.fisco.bcos.dao.intf.IAssetRegisterDao;
import org.fisco.bcos.global.Field;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class AssetRegisterDao implements IAssetRegisterDao {
    @Override
    public JSONObject registerAssetAccount(String assetAccount, BigInteger amount) throws Exception {
        AssetService assetService = AssetService.getAssetService();
        JSONObject ret = new JSONObject();
        String msg = "";
        try {
            Asset asset = Asset.load(assetService.loadAssetAddr(), assetService.getWeb3j(), assetService.getCredentials(),
                    new StaticGasProvider(assetService.getGasPrice(), assetService.getGasLimit()));
            TransactionReceipt receipt = asset.register(assetAccount, amount).send();
            List<Asset.RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
            if (!response.isEmpty()) {
                if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
                    msg = String.format(" register asset account success => asset: %s, value: %s", assetAccount,
                            amount);
                } else {
                    msg = String.format(" register asset account failed, ret code is %s",
                            response.get(0).ret.toString());
                }
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            assetService.getLogger().error(" registerAssetAccount exception, error message is {}", e.getMessage());
            msg = String.format(" register asset account failed, error message is %s", e.getMessage());
            ret.put(Field.MSG_KEY, msg);
            System.out.println(msg);
        }
        return ret;

    }
}
