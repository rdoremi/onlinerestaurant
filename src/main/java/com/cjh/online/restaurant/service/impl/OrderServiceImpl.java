package com.cjh.online.restaurant.service.impl;


import com.cjh.online.restaurant.common.Const;
import com.cjh.online.restaurant.common.ResponseCode;
import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Address;
import com.cjh.online.restaurant.entity.Order;
import com.cjh.online.restaurant.entity.OrderItem;
import com.cjh.online.restaurant.mapper.AddressMapper;
import com.cjh.online.restaurant.mapper.OrderItemMapper;
import com.cjh.online.restaurant.mapper.OrderMapper;
import com.cjh.online.restaurant.service.OrderService;
import com.cjh.online.restaurant.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ServerResponse<Order> addOrder(List<CartVo> cartVos, BigDecimal total, Integer addressId, Integer userId) {
        if (cartVos == null|| addressId < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            Order order = new Order();
            order.setAddressId(addressId);
            Date date = new Date();
            String strDateFormat = "yyyyMMddHHmmssSSS";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            order.setOrderNo(Long.valueOf(sdf.format(date)));
            order.setUserId(userId);
            order.setStatus(Const.Order.NOT_PAY);
            order.setPayment(total);
            order.setPostage(0);


            int rowCount1 = 0;
            for (CartVo cartVo : cartVos) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderNo(order.getOrderNo());
                orderItem.setProductId(cartVo.getProductId());
                orderItem.setQuantity(cartVo.getQuantity());
                orderItem.setProductName(cartVo.getProductName());
                orderItem.setUserId(userId);
                orderItem.setProductImage(cartVo.getImages());
                orderItem.setTotalPrice(cartVo.getPriceTotal());
                rowCount1 = orderItemMapper.insert(orderItem);

            }
            int rowCount2 = orderMapper.addOrder(order);

            if (rowCount1 > 0&& rowCount2 > 0){
                return ServerResponse.createBySuccess(order);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    @Override
    public ServerResponse<Order> getOrderByOderNo(Long orderNo) {
        if (orderNo < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Order order = orderMapper.selectByOrderNo(orderNo);
        return ServerResponse.createBySuccess(order);
    }

    @Override
    public ServerResponse updatePayMessage(Order order) {
        if (StringUtils.isEmpty(order.getPayId())||order.getOrderNo() < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            int rowCount = orderMapper.updatePayMessage(order);
            if (rowCount > 0){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    @Override
    public ServerResponse createOrder(Integer userId, Integer shippingId) {
        return null;
    }
    @Override
    public ServerResponse pay(Long orderNo, Integer userId, String path) {

        return null;
    }

    @Override
    public ServerResponse aliCallback(Map<String, String> params) {
        return null;
    }

    @Override
    public ServerResponse queryOrderPayStatus(Integer userId, Long orderNo) {
        return null;
    }



    @Override
    public ServerResponse<String> cancel(Integer userId, Long orderNo) {
        return null;
    }

    @Override
    public ServerResponse getOrderCartProduct(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo<OrderProductVo>> getOrderList(Integer userId, int pageNum, int pageSize) {
        if (userId < 0 || pageNum < 0 || pageSize < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByUserId(userId);
        List<OrderProductVo> orderProductVoList = Lists.newArrayList();


        for (Order order : orders) {

            OrderProductVo productVo = new OrderProductVo();
            AddressVo addressVo = this.getAddress(order);
            productVo.setAddress(addressVo);
            productVo.setProductTotalPrice(order.getPayment());
            productVo.setOrderNo(order.getOrderNo());
            productVo.setDate(order.getCreateTime());

            if ( order.getStatus() == Const.Order.NOT_PAY){
                productVo.setOrderStatus("未支付");
            }else if ( order.getStatus() == Const.Order.PAY){
                productVo.setOrderStatus("待发货");
            }else if ( order.getStatus()== Const.Order.CANCER_PAY){
                productVo.setOrderStatus("已取消");
            }else if ( order.getStatus() == Const.Order.SHIPPED){
                productVo.setOrderStatus("已发货");
            }else if ( order.getStatus() == Const.Order.GET_PRODUCT){
                productVo.setOrderStatus("已收货");
            }else if (order.getStatus() == Const.Order.NO_REVIEW){
                productVo.setOrderStatus("待评价");
            }else if (order.getStatus() == Const.Order.REVIEWED){
                productVo.setOrderStatus("已评价");
            }
           List<OrderItem> orderItem = orderItemMapper.selectByOrderNo(order.getOrderNo());
           List<OrderItemVo> orderItemVos = Lists.newArrayList();
            for (OrderItem item : orderItem) {
                OrderItemVo orderItemVo = this.getOrderItemVo(item);
                orderItemVos.add(orderItemVo);
            }
            productVo.setOrderItemVoList(orderItemVos);

            orderProductVoList.add(productVo);
        }
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(orderProductVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> manageList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse<OrderVo> manageDetail(Long orderNo) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse<String> manageSendGoods(Long orderNo) {
        return null;
    }

    private AddressVo getAddress(Order order){
        Address address = addressMapper.selectAddressById(order.getAddressId());
        AddressVo addressVo = new AddressVo();
        addressVo.setReceiver(address.getReceiver());
        addressVo.setTel(address.getTel());
        addressVo.setId(address.getId());
        addressVo.setPostalcode(address.getPostalcode());
        addressVo.setDetail(address.getArea()+" "+address.getDetail());
        return addressVo;
    }
    private OrderItemVo getOrderItemVo(OrderItem orderItem){
        OrderItemVo orderItemVo = new OrderItemVo();
        orderItemVo.setProductImage("/admin/file/file_download?path=/"+orderItem.getProductImage());
        orderItemVo.setOrderNo(orderItem.getOrderNo());
        orderItemVo.setProductId(orderItem.getProductId());
        orderItemVo.setProductName(orderItem.getProductName());
        orderItemVo.setQuantity(orderItem.getQuantity());
        orderItemVo.setTotalPrice(orderItem.getTotalPrice());

        return orderItemVo;
    }
}
