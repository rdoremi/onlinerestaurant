package com.cjh.online.restaurant.service.impl;

import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Product;
import com.cjh.online.restaurant.service.ProductService;
import com.cjh.online.restaurant.vo.ProductDetailVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ServerResponse deleteProductById(Integer id) {
        return null;
    }

    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        return null;
    }

    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        return null;
    }

    @Override
    public ServerResponse setCommentById(Product product) {
        return null;
    }

    @Override
    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getProductList(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getProductListByMerchantId(Integer pageNum, Integer pageSize, String merchantId) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> selectListOrderByRecommend(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getProduct(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ServerResponse getProductByCategoryId(int pageNum, int pageSize, Integer categoryId) {
        return null;
    }

    @Override
    public ServerResponse getProductBag(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getProductByRecommend(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getProductByMerchantId(String merchantId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ServerResponse<ProductDetailVo> getProductById(Integer productId) {
        return null;
    }
}
