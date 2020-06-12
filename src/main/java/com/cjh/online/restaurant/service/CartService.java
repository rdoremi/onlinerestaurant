package com.cjh.online.restaurant.service;



import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Cart;
import com.cjh.online.restaurant.vo.CartVo;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    ServerResponse<CartVo> add(Cart cart);
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);
    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);
    ServerResponse<Integer> getCartProductCount(Integer userId);
    ServerResponse addCart(Cart cart);
    ServerResponse<PageInfo> getAllCartList(Integer userId, Integer page, Integer limit);
    ServerResponse<BigDecimal> getAllTotal(String cartId);
    ServerResponse updateQuantity(Integer userId, Integer cartId, Integer quantity);
    ServerResponse deleteCart(Integer id);
    ServerResponse<List<CartVo>> getCartListById(String cartId);
}
