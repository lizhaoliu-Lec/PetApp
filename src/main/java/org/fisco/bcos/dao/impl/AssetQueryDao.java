package org.fisco.bcos.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.blockService.AssetService;
import org.fisco.bcos.contract.Asset;
import org.fisco.bcos.dao.intf.IAssetQueryDao;
import org.fisco.bcos.global.Field;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class AssetQueryDao implements IAssetQueryDao {
    @Override
    public JSONObject queryAssetAccount(String assetAccount) throws Exception {
        AssetService assetService = AssetService.getAssetService();
        JSONObject ret = new JSONObject();
        String msg = "";
        try {
            Asset asset = Asset.load(assetService.loadAssetAddr(), assetService.getWeb3j(), assetService.getCredentials(),
                    new StaticGasProvider(assetService.getGasPrice(), assetService.getGasLimit()));
            Tuple2<BigInteger, BigInteger> result = asset.select(assetAccount).send();
            if (result.getValue1().compareTo(new BigInteger("0")) == 0) {
                msg = String.format(" asset account %s, value %s \n", assetAccount, result.getValue2());
            } else {
                msg = String.format(" %s asset account is not exist \n", assetAccount);
            }
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            assetService.getLogger().error(" queryAssetAmount exception, error message is {}", e.getMessage());
            msg = String.format(" query asset account failed, error message is %s\n", e.getMessage());
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        }

        return ret;
    }
}
