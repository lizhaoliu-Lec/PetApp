package org.fisco.bcos.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.dao.impl.OrderDao;
import org.fisco.bcos.service.intf.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service(value = "OrderService")
public class OrderService implements IOrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public JSONObject userGetOrderByIndex(String _id, BigInteger _orderIndex) throws Exception {
        return orderDao.userGetOrderByIndex(_id, _orderIndex);
    }

    @Override
    public JSONObject userGetOrderIndex(String _id) throws Exception {
        return orderDao.userGetOrderIndex(_id);
    }

    @Override
    public JSONObject adminGetOrderByIndex(BigInteger _orderIndex) throws Exception {
        return orderDao.adminGetOrderByIndex(_orderIndex);
    }

    @Override
    public JSONObject adminGetOrderIndex() throws Exception {
        return orderDao.adminGetOrderIndex();
    }

    @Override
    public JSONObject adminGetReturnOrderIndex() throws Exception {
        return orderDao.adminGetReturnOrderIndex();
    }

    @Override
    public JSONObject applyForReturn(String _id, String _orderId, String _reason) throws Exception {
        return orderDao.applyForReturn(_id, _orderId, _reason);
    }

    @Override
    public JSONObject acceptReturn(String _orderId) throws Exception {
        return orderDao.acceptReturn(_orderId);
    }

    @Override
    public JSONObject rejectReturn(String _orderId) throws Exception {
        return orderDao.rejectReturn(_orderId);
    }

    @Override
    public JSONObject getReturnReasonByIndex(BigInteger _orderIndex) throws Exception {
        return orderDao.getReturnReasonByIndex(_orderIndex);
    }
}
