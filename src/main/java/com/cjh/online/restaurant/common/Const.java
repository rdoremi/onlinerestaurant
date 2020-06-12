package com.cjh.online.restaurant.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {

    public static final String CURRENT_USER = "current";
    public static final String FORE_CURRENT_USER = "foreCurrent";
    public static final String SHOP_CURRENT_USER = "shopCurrent";
    public static final String SUB_ORDER = "product";
    public static final String RECEIVER = "receiver";
    public static final String TOTAL = "total";
    public static final String EMAIL = "email";
    public static final  String USERNAME = "username";
    public static final String TEL = "tel";
    public static final int REMMCONEND = 100;
    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
    public interface Cart{
        int CHENKED = 1;//购物车选中状态
        int UN_CHENKED = 0;//购物车为选择状态
        String LIMIT_NUM_FALL = "LIMIT_NUM_FALL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
    public interface Order{
        int NOT_PAY = 0;//未支付
        int PAY = 1;//已支付 待收货
        int CANCER_PAY = 2;//已取消
        int NO_SHIPPING = 3;//未发货
        int SHIPPED = 4;//已发货
        int GET_PRODUCT = 5;//已收货
        int NO_REVIEW = 6;//未评价 待评价
        int REVIEWED = 7;//已评价
    }
    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
        int ROLE_SUPER_ADMIN = 2;//超级管理员
        int ROLE_MERCHANT = 3;//商家
        int ROLE_MERCHANT_EMPLOYEE = 4;
    }
    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }
    }

}
