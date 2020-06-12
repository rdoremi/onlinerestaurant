package com.cjh.online.restaurant.service.impl;


import com.cjh.online.restaurant.common.ResponseCode;
import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Cart;
import com.cjh.online.restaurant.entity.Product;
import com.cjh.online.restaurant.mapper.CartMapper;
import com.cjh.online.restaurant.mapper.ProductMapper;
import com.cjh.online.restaurant.service.CartService;
import com.cjh.online.restaurant.utils.BigDecimalUtil;
import com.cjh.online.restaurant.vo.CartVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse<CartVo> add(Cart cart) {
        if (cart == null||cart.getProductId() == null||cart.getUserId() == null) {
            return ServerResponse.createBySuccessCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart temCart = cartMapper.checkCart(cart.getProductId());

        if (temCart != null) {
            try {
                cart.setQuantity(temCart.getQuantity() + cart.getQuantity());
                int rowCount = cartMapper.updateQuantity(cart);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), "加入购物车成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "加入购物车失败");
            }
        }


        try {
            int rowCount = cartMapper.insertSelective(cart);
            if (rowCount > 0) {
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), "加入购物车成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "加入购物车失败");
    }

    @Override
    public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count) {
        return null;
    }

    @Override
    public ServerResponse<CartVo> deleteProduct(Integer userId, String productIds) {
        return null;
    }

    @Override
    public ServerResponse<CartVo> list(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked) {
        return null;
    }

    @Override
    public ServerResponse<Integer> getCartProductCount(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse addCart(Cart cart) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getAllCartList(Integer userId, Integer page, Integer limit) {
        Page pg = PageHelper.startPage(page,limit);
        List<Cart> cartList = cartMapper.selectAllCartByUserId(userId);
        List<CartVo> cartVoList = Lists.newArrayList();
        BigDecimal totalPrice = new BigDecimal("0");
        for (Cart cart : cartList) {
            Product product = productMapper.selectByPrimaryKey(cart.getProductId());
            CartVo cartVo = new CartVo();

            cartVo.setPrice(product.getPrice());
            cartVo.setPriceTotal(BigDecimalUtil.mul(product.getPrice().doubleValue(),cart.getQuantity()));
            cartVo.setImages("/admin/file/file_download?path=/"+product.getMainImage());

            cartVo.setId(cart.getId());
            cartVo.setProductId(cart.getProductId());
            cartVo.setQuantity(cart.getQuantity());
            cartVo.setProductName(product.getTitle());
            totalPrice = BigDecimalUtil.add(cartVo.getPriceTotal().doubleValue(),totalPrice.doubleValue());
            cartVo.setAllTotalPrice(totalPrice);
            cartVoList.add(cartVo);
        }

        PageInfo pageInfo = new PageInfo(pg);
        pageInfo.setList(cartVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<BigDecimal> getAllTotal(String cartId) {

        BigDecimal total = new BigDecimal("0");
        if (StringUtils.isEmpty(cartId)){
            return ServerResponse.createBySuccess(total);
        }
        String[] idList = cartId.split(",");
        for (int i = 0; i < idList.length; i++) {
            Cart cart = cartMapper.selectByPrimaryKey(Integer.valueOf(idList[i]));
            Product product = productMapper.selectByPrimaryKey(cart.getProductId());
            total = BigDecimalUtil.add(BigDecimalUtil.mul(cart.getQuantity().doubleValue(),product.getPrice().doubleValue()).doubleValue(),total.doubleValue());
        }
        return ServerResponse.createBySuccess(total);
    }

    @Override
    public ServerResponse updateQuantity(Integer userId, Integer cartId, Integer quantity) {
        if (userId < 0 || cartId < 0 || quantity < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = new Cart();
        cart.setQuantity(quantity);
        cart.setId(cartId);
        cart.setUserId(userId);
        try{
            int rowCount = cartMapper.updateCartQuantity(cart);
            if (rowCount > 0){
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    @Override
    public ServerResponse deleteCart(Integer id) {
        if (id < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        try{
            int rowCount = cartMapper.deleteByPrimaryKey(id);
            if (rowCount > 0){
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),"删除成功");
            }

        }catch (Exception e){

        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"删除失败");
    }

    @Override
    public ServerResponse<List<CartVo>> getCartListById(String cartId) {
        if (StringUtils.isEmpty(cartId)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        BigDecimal total = new BigDecimal("0");
        String[] idList = cartId.split(",");
        List<CartVo> cartVoList = Lists.newArrayList();
        for (int i = 0; i < idList.length; i++) {
            Cart cart = cartMapper.selectByPrimaryKey(Integer.valueOf(idList[i]));
            Product product = productMapper.selectByPrimaryKey(cart.getProductId());
            CartVo cartVo =new CartVo();
            cartVo.setId(cart.getId());
            cartVo.setPriceTotal(BigDecimalUtil.mul(cart.getQuantity().doubleValue(),product.getPrice().doubleValue()));
            cartVo.setProductId(product.getId());
            cartVo.setProductName(product.getTitle());
            cartVo.setQuantity(cart.getQuantity());
            cartVo.setImages(product.getMainImage());
            total = BigDecimalUtil.add(BigDecimalUtil.mul(cart.getQuantity().doubleValue(),product.getPrice().doubleValue()).doubleValue(),total.doubleValue());
            cartVoList.add(cartVo);
        }

        return ServerResponse.createBySuccess(cartVoList);
    }
}
