package org.fisco.bcos.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.blockService.UserService;
import org.fisco.bcos.contract.User;
import org.fisco.bcos.dao.intf.IUserDao;
import org.fisco.bcos.global.Field;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class UserDao implements IUserDao {
    @Override
    public JSONObject register(String _id, String _pwd, Boolean _is_admin) throws Exception {
        UserService userService = UserService.getUserService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
//            BigInteger code = user.register(_id, _pwd);
            TransactionReceipt receipt = user.register(_id, _pwd, _is_admin).send();
            List<User.RegisterEventEventResponse> responses = user.getRegisterEventEvents(receipt);
            if (!responses.isEmpty()) {
                User.RegisterEventEventResponse result = responses.get(0);
                if (result._ret.compareTo(new BigInteger("0")) == 0) {
                    msg = String.format(" register successfully, id %s, password %s ", result._ret, result._password);
                    success = Boolean.TRUE;
                } else {
                    msg = String.format(" register fail, id %s already exist ", _id);
                }
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
            System.out.println(msg);
        } catch (Exception e) {
            userService.getLogger().error(" register exception, error message is {} ", e.getMessage());
            msg = String.format(" register exception, error message is %s ", e.getMessage());
            System.out.println(msg);
        }
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject login(String _id, String _pwd) throws Exception {
        UserService userService = UserService.getUserService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
//            Tuple2<BigInteger, String> result = user.login(_id, _pwd);
            BigInteger result = user.login(_id, _pwd).send();
            if (result.compareTo(new BigInteger("0")) == 0) {
                msg = String.format(" login successfully, account %s, password %s ", _id, _pwd);
                success = Boolean.TRUE;
//                ret.put(Field.ADDRESS_KEY, result.getValue2());
            } else {
                msg = String.format(" login fail, account %s or password %s not right ", _id, _pwd);
            }
            System.out.println(msg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            userService.getLogger().error(" userLogin exception, error message is {} ", e.getMessage());
            msg = String.format(" login account failed, error message is %s ", e.getMessage());
            System.out.println(msg);
        }
        ret.put(Field.MSG_KEY, msg);
        ret.put(Field.SUCCESS_KEY, success);
        return ret;
    }

    @Override
    public JSONObject getBalance(String _id) throws Exception {
        UserService userService = UserService.getUserService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
            BigInteger balance = user.getBalance(_id).send();
            msg = String.format(" get balance successfully, id %s, balance %s ", _id, balance.toString());
            System.out.println(msg);
            success = Boolean.TRUE;
            ret.put(Field.BALANCE_KEY, balance);
        } catch (Exception e) {
            userService.getLogger().error(" get balance exception, error message is {} ", e.getMessage());
            msg = String.format(" get balance exception, error message is %s ", e.getMessage());
            System.out.println(msg);
        }
        ret.put(Field.MSG_KEY, msg);
        ret.put(Field.SUCCESS_KEY, success);
        return ret;
    }

    @Override
    public JSONObject setBalance(String _id, BigInteger _balance) throws Exception {
        UserService userService = UserService.getUserService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
//            success = user.setBalance(_id, _balance);
            TransactionReceipt receipt = user.setBalance(_id, _balance).send();
            List<User.SetBalanceEventEventResponse> response = user.getSetBalanceEventEvents(receipt);
            if (!response.isEmpty()) {
                User.SetBalanceEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" set balance successfully, id %s, balance %s ", result._id, result.balance);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            userService.getLogger().error(" set balance exception, error message is {} ", e.getMessage());
            msg = String.format(" set balance exception, error message is %s ", e.getMessage());
            System.out.println(msg);
        }
        ret.put(Field.MSG_KEY, msg);
        ret.put(Field.SUCCESS_KEY, success);
        return ret;
    }

    @Override
    public JSONObject isAdmin(String _id) throws Exception {
        UserService userService = UserService.getUserService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
            Boolean result = user.isAdmin(_id).send();
            msg = String.format(" isAdmin successfully, id %s ", _id);
            success = Boolean.TRUE;
            System.out.println(msg);
            ret.put(Field.IS_ADMIN_KEY, result);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            userService.getLogger().error(" isAdmin exception, error message is {} ", e.getMessage());
            msg = String.format(" isAdmin exception, error message is %s ", e.getMessage());
            System.out.println(msg);
            ret.put(Field.IS_ADMIN_KEY, Boolean.FALSE);
            ret.put(Field.MSG_KEY, msg);
        }
        ret.put(Field.SUCCESS_KEY, success);
        return ret;
    }
}
