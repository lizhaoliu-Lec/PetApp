package org.fisco.bcos.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.MarketDao;
import org.fisco.bcos.service.intf.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service(value = "MarketService")
public class MarketService implements IMarketService {
    @Autowired
    MarketDao marketDao;

    @Override
    public JSONObject getPetByIndex(String _id, BigInteger _pet_index) throws Exception {
        return marketDao.getPetByIndex(_id, _pet_index);
    }

    @Override
    public JSONObject getPetIndex(String _id) throws Exception {
        return marketDao.getPetIndex(_id);
    }

    @Override
    public JSONObject getPetForSale() throws Exception {
        return marketDao.getPetForSale();
    }

    @Override
    public JSONObject createPet(String _id, String _type, BigInteger _price, String _name, String _img, String _intro) throws Exception {
        return marketDao.createPet(_id, _type, _price, _name, _img, _intro);
    }

    @Override
    public JSONObject modifyPetInfo(String _id, String _petId, String _name, String _type, BigInteger _price, String _img, String _intro) throws Exception {
        return marketDao.modifyPetInfo(_id, _petId, _name, _type, _price, _img, _intro);
    }

    @Override
    public JSONObject buyPet(String _id, String _petId, String _time) throws Exception {
        return marketDao.buyPet(_id, _petId, _time);
    }

    @Override
    public JSONObject sellPet(String _id, String _petId) throws Exception {
        return marketDao.sellPet(_id, _petId);
    }

    @Override
    public JSONObject cancelSellPet(String _id, String _petId) throws Exception {
        return marketDao.cancelSellPet(_id, _petId);
    }
}
