package org.fisco.bcos.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.blockService.OrderService;
import org.fisco.bcos.contract.Order;
import org.fisco.bcos.dao.intf.IOrderDao;
import org.fisco.bcos.global.Field;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class OrderDao implements IOrderDao {
    private Order getOrder(OrderService orderService) throws Exception {
        return Order.load(orderService.loadAssetAddr(),
                orderService.getWeb3j(), orderService.getCredentials(),
                new StaticGasProvider(orderService.getGasPrice(), orderService.getGasLimit()));
    }

    @Override
    public JSONObject userGetOrderByIndex(String _id, BigInteger _orderIndex) throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order order = getOrder(orderService);
            // 用户获得订单信息函数,买方,卖方可看
            // 输入：用户id,订单下标
            // 返回：订单id,买家,卖家,时间,宠物id,宠物价格,订单状态
            Tuple7<String, String, String, String, String, BigInteger, BigInteger> result = order.userGetOrderByIndex(_id, _orderIndex).send();
            ret.put(Field.ORDER_ID, result.getValue1());
            ret.put(Field.ORDER_BUYER, result.getValue2());
            ret.put(Field.ORDER_SELLER, result.getValue3());
            ret.put(Field.ORDER_TIME, result.getValue4());
            ret.put(Field.PET_ID, result.getValue5());
            ret.put(Field.PET_PRICE, result.getValue6());
            ret.put(Field.ORDER_STATUS, result.getValue7());
            msg = " userGetOrderByIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            orderService.getLogger().error(" userGetOrderByIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" userGetOrderByIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject userGetOrderIndex(String _id) throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order order = getOrder(orderService);
            // 用户获得自己的订单列表
            // 返回订单下标数组
            List result = order.userGetOrderIndex(_id).send();
            ret.put(Field.ORDER_LIST, result);
            msg = " userGetOrderIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            orderService.getLogger().error(" userGetOrderIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" userGetOrderIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject adminGetOrderByIndex(BigInteger _orderIndex) throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order order = getOrder(orderService);
            // 管理员获得订单信息函数
            // 输入：订单下标
            // 返回：订单id,买家,卖家,时间,宠物id,宠物价格,订单状态
            Tuple7<String, String, String, String, String, BigInteger, BigInteger> result = order.adminGetOrderByIndex(_orderIndex).send();
            ret.put(Field.ORDER_ID, result.getValue1());
            ret.put(Field.ORDER_BUYER, result.getValue2());
            ret.put(Field.ORDER_SELLER, result.getValue3());
            ret.put(Field.ORDER_TIME, result.getValue4());
            ret.put(Field.PET_ID, result.getValue5());
            ret.put(Field.PET_PRICE, result.getValue6());
            ret.put(Field.ORDER_STATUS, result.getValue7());
            msg = " adminGetOrderByIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            orderService.getLogger().error(" adminGetOrderByIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" adminGetOrderByIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject adminGetOrderIndex() throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order order = getOrder(orderService);
            // 返回所有订单（管理员查看）
            // 返回订单下标数组
            List result = order.adminGetOrderIndex().send();
            ret.put(Field.ORDER_LIST, result);
            msg = " adminGetOrderIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            orderService.getLogger().error(" adminGetOrderIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" adminGetOrderIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject adminGetReturnOrderIndex() throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order order = getOrder(orderService);
            // 返回所有订单（管理员查看）
            // 返回订单下标数组
            List result = order.adminGetReturnOrderIndex().send();
            ret.put(Field.ORDER_LIST, result);
            msg = " adminGetReturnOrderIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            orderService.getLogger().error(" adminGetReturnOrderIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" adminGetReturnOrderIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject applyForReturn(String _id, String _orderId, String _reason) throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order Order = getOrder(orderService);
            // 用户申请退货
            // 输入：用户id,订单id,退货原因
            // 返回：发起退货申请是否成功
            TransactionReceipt receipt = Order.applyForReturn(_id, _orderId, _reason).send();
            List<Order.ApplyForReturnEventEventResponse> response = Order.getApplyForReturnEventEvents(receipt);
            if (!response.isEmpty()) {
                Order.ApplyForReturnEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" applyForReturn successfully, id %s, orderId %s, reason %s ",
                        result._id, result._orderId, result._reason);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            orderService.getLogger().error(" applyForReturn exception, error message is {} ", e.getMessage());
            msg = String.format(" applyForReturn exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject acceptReturn(String _orderId) throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order Order = getOrder(orderService);
            TransactionReceipt receipt = Order.acceptReturn(_orderId).send();
            List<Order.AcceptReturnEventEventResponse> response = Order.getAcceptReturnEventEvents(receipt);
            if (!response.isEmpty()) {
                Order.AcceptReturnEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" acceptReturn successfully, orderId %s ", result._orderId);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            orderService.getLogger().error(" acceptReturn exception, error message is {} ", e.getMessage());
            msg = String.format(" acceptReturn exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject rejectReturn(String _orderId) throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order Order = getOrder(orderService);
            TransactionReceipt receipt = Order.rejectReturn(_orderId).send();
            List<Order.RejectReturnEventEventResponse> response = Order.getRejectReturnEventEvents(receipt);
            if (!response.isEmpty()) {
                Order.RejectReturnEventEventResponse result = response.get(0);
                success = result._ret;
                msg = String.format(" rejectReturn successfully, orderId %s ", result._orderId);
            } else {
                msg = " event log not found, maybe transaction not exec. ";
            }
        } catch (Exception e) {
            orderService.getLogger().error(" rejectReturn exception, error message is {} ", e.getMessage());
            msg = String.format(" rejectReturn exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }

    @Override
    public JSONObject getReturnReasonByIndex(BigInteger _orderIndex) throws Exception {
        OrderService orderService = OrderService.getOrderService();
        JSONObject ret = new JSONObject();
        String msg = "";
        Boolean success = Boolean.FALSE;
        try {
            Order order = getOrder(orderService);
            String result = order.getReturnReasonByIndex(_orderIndex).send();
            ret.put(Field.ORDER_ID, result);
            msg = " getReturnReasonByIndex successfully ";
            success = Boolean.TRUE;
        } catch (Exception e) {
            orderService.getLogger().error(" getReturnReasonByIndex exception, error message is {} ", e.getMessage());
            msg = String.format(" getReturnReasonByIndex exception, error message is %s ", e.getMessage());
        }
        System.out.println(msg);
        ret.put(Field.SUCCESS_KEY, success);
        ret.put(Field.MSG_KEY, msg);
        return ret;
    }
}
