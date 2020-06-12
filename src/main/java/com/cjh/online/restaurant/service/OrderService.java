package com.cjh.online.restaurant.service;



import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Order;
import com.cjh.online.restaurant.vo.CartVo;
import com.cjh.online.restaurant.vo.OrderProductVo;
import com.cjh.online.restaurant.vo.OrderVo;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
public interface OrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);
    ServerResponse aliCallback(Map<String, String> params);
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);
    ServerResponse createOrder(Integer userId, Integer shippingId);
    ServerResponse<String> cancel(Integer userId, Long orderNo);
    ServerResponse getOrderCartProduct(Integer userId);
    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);
    ServerResponse<PageInfo<OrderProductVo>> getOrderList(Integer userId, int pageNum, int pageSize);

    ServerResponse updatePayMessage(Order order);
    ServerResponse<Order> addOrder(List<CartVo> cartVos, BigDecimal total, Integer addressId, Integer userId);
    ServerResponse<Order> getOrderByOderNo(Long orderNo);
    //backend
    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);
    ServerResponse<OrderVo> manageDetail(Long orderNo);
    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);
    ServerResponse<String> manageSendGoods(Long orderNo);


}
