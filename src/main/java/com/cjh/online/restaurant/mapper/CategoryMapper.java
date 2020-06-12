package com.cjh.online.restaurant.mapper;




import com.cjh.online.restaurant.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAllProductCategory();


    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectCategoryChildrenByParentId(Integer parentId);

    List<Category> selectParentCategory();
}