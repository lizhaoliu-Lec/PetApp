package org.fisco.bcos.service.intf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public interface IOrderService {
    // 用户获得订单信息函数, 买方, 卖方可看
    // 输入：用户id, 订单下标
    // 返回：订单id, 买家, 卖家, 时间, 宠物id, 宠物价格, 订单状态
    // function userGetOrderByIndex(string _id, uint _orderIndex) public view returns (string, string, string, string, string, uint16, uint8)
    JSONObject userGetOrderByIndex(String _id, BigInteger _orderIndex) throws Exception;

    // 用户获得自己的订单列表
    // 返回订单下标数组
    // function userGetOrderIndex(string _id) public view returns(uint[])
    JSONObject userGetOrderIndex(String _id) throws Exception;


    // 管理员获得订单信息函数
    // 输入：订单下标
    // 返回：订单id, 买家, 卖家, 时间, 宠物id, 宠物价格, 订单状态
    // function adminGetOrderByIndex(uint _orderIndex) public view returns (string, string, string, string, string, uint16, uint8)
    JSONObject adminGetOrderByIndex(BigInteger _orderIndex) throws Exception;


    // 返回所有订单（管理员查看）
    // 返回订单下标数组
    // function adminGetOrderIndex() public view returns (uint[])
    JSONObject adminGetOrderIndex() throws Exception;


    // 管理员获得请求仲裁的订单
    // 返回订单下标数组
    // function adminGetReturnOrderIndex() public view returns(uint[])
    JSONObject adminGetReturnOrderIndex() throws Exception;


    // 用户申请退货
    // 输入：用户id, 订单id, 退货原因
    // 返回：发起退货申请是否成功
    // function applyForReturn(string _id, string _orderId, string _reason) public returns (bool)
    JSONObject applyForReturn(String _id, String _orderId, String _reason) throws Exception;


    // 管理员接受退货申请
    // 输入：订单id
    // 返回：同意退货申请是否成功
    // function acceptReturn(string _orderId) public returns (bool)
    JSONObject acceptReturn(String _orderId) throws Exception;


    // 管理员拒绝退货申请
    // 输入：订单id
    // 返回：拒绝退货申请是否成功
    // function rejectReturn(string _orderId) public returns (bool)
    JSONObject rejectReturn(String _orderId) throws Exception;


    // 获取退款原因
    // 输入：订单下标
    // function getReturnReasonByIndex(uint _orderIndex) public view returns(string)
    JSONObject getReturnReasonByIndex(BigInteger _orderIndex) throws Exception;

}
