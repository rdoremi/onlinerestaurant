package com.cjh.online.restaurant.service;


import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    public ServerResponse<User> login(String tel, String password);

    ServerResponse<User> loginByFore(String tel, String password);

    public ServerResponse<PageInfo> selectAllManage(int pageNum, int pageSize, User us);

    ServerResponse<PageInfo> getUserList(int pageNum, int pageSize);

    //    public ServerResponse<PageInfo> selectAllUser(int pageNum, int pageSize);
    public ServerResponse getUserByTel(String tel);

    public ServerResponse<User> getUserById(Integer id);


    public ServerResponse addUser(User user);

    public ServerResponse update(User user);

    public ServerResponse updateByAdmin(User user);

    public ServerResponse updateRole(User user);


    public ServerResponse deleteAdmin(int id);

    ServerResponse<PageInfo> selectAllUser(Integer pageNum, Integer pageSize);

    public ServerResponse deleteById(int id, User user);

    public ServerResponse<String> register(User user);

    public ServerResponse<String> checkValid(String str, String type);//校验用户名和邮箱

    public ServerResponse<String> selectQuestion(String tel);

    public ServerResponse<String> checkAnswer(String tel, String question, String answer);

    public ServerResponse<String> forgetRestPassword(String tel, String passwordNew, String forgetToker);

    public ServerResponse<String> resetPassword(String passwordold, String passwordnew, User user);

    public ServerResponse<User> updateInformation(User user);

    public ServerResponse<User> getInformation(String tel);

    public ServerResponse checkAdminRole(User user);

    public ServerResponse checkSuperAdminRole(User user);
    public ServerResponse checkShopRole(User user);

    User selectByTel(String tel);
}
