package org.fisco.bcos.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.blockService.UserService;
import org.fisco.bcos.contract.User;
import org.fisco.bcos.dao.intf.IUserDao;
import org.fisco.bcos.global.Field;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class UserDao implements IUserDao {
    @Override
    public JSONObject register(String _id, String _pwd) throws Exception {
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

    @Override
    public JSONObject login(String _id, String _pwd) throws Exception {
        UserService userService = UserService.getUserService();
        JSONObject ret = new JSONObject();
        String msg = "";
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
            Tuple2<BigInteger, String> result = user.login(_id, _pwd);
            if (result.getValue1().compareTo(new BigInteger("0")) == 0) {
                msg = String.format(" login successfully, account %s, password %s \n", _id, _pwd);
                ret.put("address", result.getValue2());
            } else {
                msg = String.format(" login fail, account %s or password %s not right\n", _id, _pwd);
            }
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            userService.getLogger().error(" userLogin exception, error message is {}", e.getMessage());
            msg = String.format(" login account failed, error message is %s\n", e.getMessage());
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        }
        return ret;
    }
}
