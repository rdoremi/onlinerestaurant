package com.cjh.online.restaurant.vo;

public class CategoryVo {
    private Integer id;
    private Integer parentId;
    private String title;
    public CategoryVo(){}

    public CategoryVo(Integer id,Integer parentId,String title){
        this.id = id;
        this.parentId = parentId;
        this.title = title;
    }
    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getTitle() {
        return title;
    }


}
