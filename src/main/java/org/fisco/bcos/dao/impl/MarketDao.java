package org.fisco.bcos.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.blockService.UserService;
import org.fisco.bcos.contract.Market;
import org.fisco.bcos.blockService.MarketService;
import org.fisco.bcos.blockService.OrderService;
import org.fisco.bcos.contract.Order;
import org.fisco.bcos.dao.intf.IMarketDao;
import org.fisco.bcos.global.Field;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class MarketDao implements IMarketDao {
    // 1. 先部署`User`, 然后部署`Market`和`Order`
    // 2. 在`Market`中调用`setUserAddress` 和`setOrderAddress`, 传入合约地址
    // 3. 同理在`Order`中调用`setMarketAddress`
    // 4. must call send, else bug
    public MarketDao() throws Exception {
        // 1
        UserService userService = UserService.getUserService();
        MarketService marketService = MarketService.getMarketService();
        OrderService orderService = OrderService.getOrderService();
        // 2
        Market market = Market.load(marketService.loadAssetAddr(), marketService.getWeb3j(), marketService.getCredentials(), new StaticGasProvider(marketService.getGasPrice(), marketService.getGasLimit()));
        market.setUserAddress(userService.loadAssetAddr()).send();
        market.setOrderAddress(orderService.loadAssetAddr()).send();
        // 3
        Order order = Order.load(orderService.loadAssetAddr(), orderService.getWeb3j(), orderService.getCredentials(), new StaticGasProvider(marketService.getGasPrice(), marketService.getGasLimit()));
        order.setMarketAddress(marketService.loadAssetAddr()).send();
    }

    private Market getMarket(MarketService marketService) throws Exception {
        return Market.load(marketService.loadAssetAddr(),
                marketService.getWeb3j(), marketService.getCredentials(),
                new StaticGasProvider(marketService.getGasPrice(), marketService.getGasLimit()));
    }

    @Override
    public JSONObject getPetByIndex(String _id, BigInteger _pet_index) throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 返回 宠物id, 类型, 价格, 名字, 宠物上架状态, 宠物图片, 宠物介绍
            Tuple7<String, String, BigInteger, String, BigInteger, String, String> result = market.getPetByIndex(_id, _pet_index).send();
            ret.put(Field.PET_ID, result.getValue1());
            ret.put(Field.PET_TYPE, result.getValue2());
            ret.put(Field.PET_PRICE, result.getValue3());
            ret.put(Field.PET_NAME, result.getValue4());
            ret.put(Field.PET_STATUS, result.getValue5());
            ret.put(Field.PET_URL, result.getValue6());
            ret.put(Field.PET_INTRO, result.getValue7());
            msg = " getPetByIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            marketService.getLogger().error(" getPetByIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" getPetByIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject getPetIndex(String _id) throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 返回宠物下标数组
            List result = market.getPetIndex(_id).send();
            ret.put(Field.PET_LIST, result);
            msg = " getPetIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            marketService.getLogger().error(" getPetIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" getPetIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject getPetForSale() throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 返回宠物下标数组
            List result = market.getPetForSale().send();
            ret.put(Field.PET_LIST, result);
            msg = " getPetForSale successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            marketService.getLogger().error(" getPetForSale exception, error message is {} ", e.getMessage());
            msg = String.format(" getPetForSale exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject createPet(String _id, String _type, BigInteger _price, String _name, String _img, String _intro) throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 返回宠物下标数组
            TransactionReceipt receipt = market.createPet(_id, _type, _price, _name, _img, _intro).send();
            List<Market.CreatePetEventEventResponse> response = market.getCreatePetEventEvents(receipt);
            if (!response.isEmpty()) {
                Market.CreatePetEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" createPet successfully, id %s, type %s, price %s, name %s, img %s, intro %s ",
                        result._id, result._type, result._price, result._name, result._img, result._intro);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            marketService.getLogger().error(" createPet exception, error message is {} ", e.getMessage());
            msg = String.format(" createPet exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject modifyPetInfo(String _id, String _petId, String _name, String _type, BigInteger _price, String _img, String _intro) throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 修改宠物信息
            // 可修改：宠物名字,宠物类型,宠物价格,宠物图片地址,宠物介绍
            // 返回：是否修改成功
            TransactionReceipt receipt = market.modifyPetInfo(_id, _petId, _name, _type, _price, _img, _intro).send();
            List<Market.ModifyPetInfoEventEventResponse> response = market.getModifyPetInfoEventEvents(receipt);
            if (!response.isEmpty()) {
                Market.ModifyPetInfoEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" modifyPetInfo successfully, id %s, type %s, price %s, name %s, img %s, intro %s ",
                        result._id, result._type, result._price, result._name, result._img, result._intro);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            marketService.getLogger().error(" modifyPetInfo exception, error message is {} ", e.getMessage());
            msg = String.format(" modifyPetInfo exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject buyPet(String _id, String _petId, String _time) throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 输入：用户id,宠物id,购买时间
            TransactionReceipt receipt = market.buyPet(_id, _petId, _time).send();
            List<Market.BuyPetEventEventResponse> response = market.getBuyPetEventEvents(receipt);
            if (!response.isEmpty()) {
                Market.BuyPetEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" buyPet successfully, id %s, petId %s, time %s ",
                        result._id, result._petId, result._time);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            marketService.getLogger().error(" buyPet exception, error message is {} ", e.getMessage());
            msg = String.format(" buyPet exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject sellPet(String _id, String _petId) throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 输入：用户id,宠物id
            TransactionReceipt receipt = market.sellPet(_id, _petId).send();
            List<Market.SellPetEventEventResponse> response = market.getSellPetEventEvents(receipt);
            if (!response.isEmpty()) {
                Market.SellPetEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" sellPet successfully, id %s, petId %s ",
                        result._id, result._petId);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            marketService.getLogger().error(" sellPet exception, error message is {} ", e.getMessage());
            msg = String.format(" sellPet exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject cancelSellPet(String _id, String _petId) throws Exception {
        MarketService marketService = MarketService.getMarketService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Market market = getMarket(marketService);
            // 输入：用户id,宠物id
            TransactionReceipt receipt = market.cancelSellPet(_id, _petId).send();
            List<Market.CancelSellPetEventEventResponse> response = market.getCancelSellPetEventEvents(receipt);
            if (!response.isEmpty()) {
                Market.CancelSellPetEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" cancelSellPet successfully, id %s, petId %s ",
                        result._id, result._petId);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            marketService.getLogger().error(" cancelSellPet exception, error message is {} ", e.getMessage());
            msg = String.format(" sellPet exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }
}
