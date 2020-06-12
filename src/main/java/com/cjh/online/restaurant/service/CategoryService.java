package com.cjh.online.restaurant.service;



import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Category;
import com.cjh.online.restaurant.vo.CategoryVo;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse deleteCategory(Integer id);
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
    ServerResponse<List<Map<String, Object>>> selectAllProductCategory();
    ServerResponse <List<CategoryVo>> selectParentById(Integer parentId);
    ServerResponse<List<CategoryVo>> selectChildrenByParentId(Integer parentId);
    ServerResponse<List<CategoryVo>> selectAllCategoryList();
    ServerResponse<List<CategoryVo>> selectParentCategoryList();
}
