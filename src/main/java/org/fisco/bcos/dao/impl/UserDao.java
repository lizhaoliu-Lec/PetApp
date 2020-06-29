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
        Boolean success = Boolean.FALSE;
        try {
            User user = User.load(userService.loadAssetAddr(), userService.getWeb3j(), userService.getCredentials(), new StaticGasProvider(userService.getGasPrice(), userService.getGasLimit()));
            BigInteger code = user.register(_id, _pwd);
            if (code.compareTo(new BigInteger("0")) == 0) {
                msg = String.format(" register successfully, id %s, password %s ", _id, _pwd);
                success = Boolean.TRUE;
            } else {
                msg = String.format(" register fail, id %s already exist ", _id);
            }
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            userService.getLogger().error(" register exception, error message is {} ", e.getMessage());
            msg = String.format(" register exception, error message is %s ", e.getMessage());
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        }
        ret.put(Field.SUCCESS_KEY, success);
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
            Tuple2<BigInteger, String> result = user.login(_id, _pwd);
            if (result.getValue1().compareTo(new BigInteger("0")) == 0) {
                msg = String.format(" login successfully, account %s, password %s ", _id, _pwd);
                success = Boolean.TRUE;
                ret.put(Field.ADDRESS_KEY, result.getValue2());
            } else {
                msg = String.format(" login fail, account %s or password %s not right ", _id, _pwd);
            }
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            userService.getLogger().error(" userLogin exception, error message is {} ", e.getMessage());
            msg = String.format(" login account failed, error message is %s ", e.getMessage());
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        }
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
            BigInteger balance = user.getBalance(_id);
            msg = String.format(" get balance successfully, id %s, balance %s ", _id, balance.toString());
            System.out.println(msg);
            success = Boolean.TRUE;
            ret.put(Field.BALANCE_KEY, balance);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            userService.getLogger().error(" get balance exception, error message is {} ", e.getMessage());
            msg = String.format(" get balance exception, error message is %s ", e.getMessage());
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        }
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
            success = user.setBalance(_id, _balance);
            msg = String.format(" set balance successfully, id %s ", _id);
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        } catch (Exception e) {
            userService.getLogger().error(" set balance exception, error message is {} ", e.getMessage());
            msg = String.format(" set balance exception, error message is %s ", e.getMessage());
            System.out.println(msg);
            ret.put(Field.MSG_KEY, msg);
        }
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
            Boolean result = user.isAdmin(_id);
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
