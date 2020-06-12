package com.cjh.online.restaurant.mapper;

import com.cjh.online.restaurant.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart cart);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    int updateCount(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    Cart selectCartByUserIdProductIdColor(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("color") String color);

    List<Cart> selectCartByUserId(Integer userId);

    int selectCartProductCheckedStatusByUserId(Integer userId);

    int deleteByUserIdProductIds(@Param("userId") Integer userId, @Param("productIdList") List<String> productIdList);


    int checkedOrUncheckedProduct(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("checked") Integer checked);

    int selectCartProductCount(@Param("userId") Integer userId);

    Cart checkCart(Integer productId);

    List<Cart> selectCheckedCartByUserId(Integer userId);
    List<Cart> selectCheckedCartByUserTel(String tel);

    List<Cart> selectAllCartByUserId(Integer userId);

    int updateQuantity(Cart cart);
    int updateCartQuantity(Cart cart);
}
