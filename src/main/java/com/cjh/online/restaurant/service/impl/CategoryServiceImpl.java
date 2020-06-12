package com.cjh.online.restaurant.service.impl;



import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Category;
import com.cjh.online.restaurant.mapper.CategoryMapper;
import com.cjh.online.restaurant.service.CategoryService;
import com.cjh.online.restaurant.vo.CategoryVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service
public class CategoryServiceImpl implements CategoryService {
    //    private Logger logger = (Logger) LoggerFactory.getLogger(CategoryServiceImpl.class);
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank((categoryName))) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Override
    public ServerResponse deleteCategory(Integer id) {
        if (id == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        int rowCount = categoryMapper.deleteByPrimaryKey(id);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("删除品类成功");
        }
        return ServerResponse.createByErrorMessage("删除品类失败");
    }

    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);
        List<Integer> categoryList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }

    @Override
    public ServerResponse<List<Map<String, Object>>> selectAllProductCategory() {

        List<Category> categoryList = categoryMapper.selectAllProductCategory();
        List<CategoryVo> productCategoryVoList = Lists.newArrayList();

        for (Category category : categoryList) {
            CategoryVo productCategoryVo = new CategoryVo(category.getId(),category.getParentId(),category.getName());
           productCategoryVoList.add(productCategoryVo);
        }
        return ServerResponse.createBySuccess(getTreeCategory(productCategoryVoList));
    }
    /*
    * 转换成二级分类格式
    * */
    private List<Map<String, Object>> getTreeCategory(List<CategoryVo> productCategorieList){
        List<Map<String, Object>> ret = new ArrayList<Map<String,Object>>();
        //所有的父分类整理
        for(CategoryVo productCategory : productCategorieList){
            if(productCategory.getParentId() == 0){
                Map<String, Object> top = new HashMap<String, Object>();
                top.put("id", productCategory.getId());
                top.put("title", productCategory.getTitle());
                top.put("children", new ArrayList<Map<String,Object>>());
                top.put("parentId",productCategory.getParentId());
                ret.add(top);
            }
        }
        for(CategoryVo productCategory : productCategorieList){
            if(productCategory.getParentId() != 0){
                for(Map<String, Object> map : ret){
                    if(productCategory.getParentId().longValue() == Long.valueOf(map.get("id")+"")){
                        List children = (List)map.get("children");
                        Map<String, Object> child = new HashMap<String, Object>();
                        child.put("id", productCategory.getId());
                        child.put("title", productCategory.getTitle());
                        child.put("parentId",productCategory.getParentId());
                        children.add(child);
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public ServerResponse<List<CategoryVo>> selectParentById(Integer parentId) {
        if (parentId < 0 || parentId == null){
            ServerResponse.createByErrorMessage("参数错误");
        }
        List<Category> list = categoryMapper.selectCategoryChildrenByParentId(parentId);//通过parentId查询
        if (list == null){
            return ServerResponse.createByErrorMessage("查询为空");
        }
        List<CategoryVo> productCategoryVoList = Lists.newArrayList();
        for (Category category : list) {
            CategoryVo productCategoryVo = new CategoryVo(category.getId(),category.getParentId(),category.getName());
            productCategoryVoList.add(productCategoryVo);
        }
        return ServerResponse.createBySuccess(productCategoryVoList);
    }

    @Override
    public ServerResponse<List<CategoryVo>> selectChildrenByParentId(Integer parentId) {
        return null;
    }

    @Override
    public ServerResponse<List<CategoryVo>> selectAllCategoryList() {
        List<Category> list = categoryMapper.selectAllProductCategory();
        List<CategoryVo> productCategoryVoList = Lists.newArrayList();
        for (Category category : list) {
            CategoryVo productCategoryVo = new CategoryVo(category.getId(),category.getParentId(),category.getName());
            productCategoryVoList.add(productCategoryVo);
        }
        return ServerResponse.createBySuccess(productCategoryVoList);
    }

    @Override
    public ServerResponse<List<CategoryVo>> selectParentCategoryList() {
        List<Category> categoryList = categoryMapper.selectParentCategory();
        List<CategoryVo> categoryVoList = Lists.newArrayList();
        for (Category category : categoryList) {
            CategoryVo productCategoryVo = new CategoryVo(category.getId(),category.getParentId(),category.getName());
            categoryVoList.add(productCategoryVo);
        }
        return ServerResponse.createBySuccess(categoryVoList);
    }
}
