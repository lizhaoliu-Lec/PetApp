package org.fisco.bcos.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.blockService.UserService;
import org.fisco.bcos.contract.Asset;
import org.fisco.bcos.contract.User;
import org.fisco.bcos.dao.intf.IUserRegisterDao;
import org.fisco.bcos.global.Field;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


// 返回：0代表注册成功, 1代表用户id重复
// function register(string _id, string _password) public returns (uint8)
@Repository
public class UserRegisterDao implements IUserRegisterDao {
    @Override
    public JSONObject userRegister(String _id, String _pwd) throws Exception {
        UserService userService = UserService.getUserService();
        JSONObject ret = new JSONObject();
        String msg = "";
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
            BigInteger code = user.register(_id, _pwd);
            if (code.compareTo(new BigInteger("0")) == 0) {
                msg = String.format(" register successfully, id %s, password %s \n", _id, _pwd);
            } else {
                msg = String.format(" register fail, id %s already exist \n", _id);
            }
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            userService.getLogger().error(" userRegister exception, error message is {}", e.getMessage());
            msg = String.format(" userRegister exception, error message is %s\n", e.getMessage());
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        }
        return ret;
    }
}
