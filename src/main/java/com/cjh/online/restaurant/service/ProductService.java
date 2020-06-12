package com.cjh.online.restaurant.service;



import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Product;
import com.cjh.online.restaurant.vo.ProductDetailVo;
import com.github.pagehelper.PageInfo;


/**
 * Created by geely
 */
public interface ProductService {
    ServerResponse deleteProductById(Integer id);

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse setCommentById(Product product);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(Integer pageNum, Integer pageSize);

    ServerResponse<PageInfo> getProductListByMerchantId(Integer pageNum, Integer pageSize, String merchantId);



    ServerResponse<PageInfo> selectListOrderByRecommend(Integer pageNum, Integer pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);

    ServerResponse<PageInfo> getProduct(Integer pageNum, Integer pageSize);

    ServerResponse getProductByCategoryId(int pageNum, int pageSize, Integer categoryId);

    ServerResponse getProductBag(int pageNum, int pageSize);

    ServerResponse<PageInfo> getProductByRecommend(Integer pageNum, Integer pageSize);

    ServerResponse<PageInfo> getProductByMerchantId(String merchantId, Integer pageNum, Integer pageSize);

    ServerResponse<ProductDetailVo> getProductById(Integer productId);

}
