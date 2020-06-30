package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IMarketService {
    // 输入 用户id, 宠物下标
    // 返回 宠物id, 类型,  价格,  名字,  宠物上架状态,  宠物图片,  宠物介绍
    // function getPetByIndex(string _id, uint _index) public view returns (string, string, uint16, string, uint8, string, string)
    JSONObject getPetByIndex(String _id, BigInteger _pet_index) throws Exception;

    // 用户查看自己的宠物
    // 返回宠物下标数组
    // function getPetIndex(string _id) public view returns (uint[])
    JSONObject getPetIndex(String _id) throws Exception;


    // 获取市场在售宠物列表
    // 返回宠物下标数组
    // function getPetForSale() public view returns(uint[])
    JSONObject getPetForSale() throws Exception;


    // 创建宠物
    // 输入：用户id, 宠物类型, 价格, 名字, 图片, 介绍
    // 返回：是否修改成功
    // function createPet(string _id, string _type, uint16 _price, string _name, string _img, string _intro) public returns (bool)
    JSONObject createPet(String _id, String _type, BigInteger _price, String _name, String _img, String _intro) throws Exception;

    // 修改宠物信息
    // 可修改：宠物名字, 宠物类型, 宠物价格, 宠物图片地址, 宠物介绍
    // 返回：是否修改成功
    // function modifyPetInfo(string _id, string _petId, string _name, string _type, uint16 _price, string _img, string _intro) public returns (bool)
    JSONObject modifyPetInfo(String _id, String _petId, String _name, String _type, BigInteger _price, String _img, String _intro) throws Exception;

    // 购买宠物
    // 输入：用户id, 宠物id, 购买时间
    // function buyPet(string _id, string _petId, string _time) public returns (bool)
    JSONObject buyPet(String _id, String _petId, String _time) throws Exception;

    // 出售宠物
    // 输入：用户id, 宠物id
    // 返回：成功置为出售状态则返回true
    // function sellPet(string _id, string _petId) public returns (bool)
    JSONObject sellPet(String _id, String _petId) throws Exception;


    // 取消出售宠物
    // 输入：用户id, 宠物id
    // 返回：成功取消出售状态则返回true
    // function cancelSellPet(string _id, string _petId) public returns (bool)
    JSONObject cancelSellPet(String _id, String _petId) throws Exception;

}
