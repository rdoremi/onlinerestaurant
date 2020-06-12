package com.cjh.online.restaurant.service;



import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Merchant;
import com.github.pagehelper.PageInfo;

public interface MerchantService {

    public ServerResponse<Merchant> login(String tel, String password);

    ServerResponse<Merchant> loginByFore(String tel, String password);

    public ServerResponse<PageInfo> selectAllManage(int pageNum, int pageSize, Merchant us, String merchant_id);

    ServerResponse<PageInfo> getUserList(int pageNum, int pageSize, String merchant_i);

    //    public ServerResponse<PageInfo> selectAllUser(int pageNum, int pageSize);
    public ServerResponse getUserByTel(String tel);

    public ServerResponse<Merchant> getUserById(Integer id);


    public ServerResponse addUser(Merchant user);

    public ServerResponse update(Merchant user);

    public ServerResponse updateByAdmin(Merchant user);

    public ServerResponse updateRole(Merchant user);


    public ServerResponse deleteAdmin(int id);

    ServerResponse<PageInfo> selectAllUser(Integer pageNum, Integer pageSize, String merchant_id);

    public ServerResponse deleteById(int id, Merchant user);

    public ServerResponse<String> register(Merchant user);

    public ServerResponse<String> checkValid(String str, String type);//校验用户名和邮箱

    public ServerResponse<String> selectQuestion(String tel);

    public ServerResponse<String> checkAnswer(String tel, String question, String answer);

    public ServerResponse<String> forgetRestPassword(String tel, String passwordNew, String forgetToker);

    public ServerResponse<String> resetPassword(String passwordold, String passwordnew, Merchant user);

    public ServerResponse<Merchant> updateInformation(Merchant user);

    public ServerResponse<Merchant> getInformation(String tel);

    public ServerResponse checkAdminRole(Merchant user);

    public ServerResponse checkSuperAdminRole(Merchant user);
    public ServerResponse checkShopRole(Merchant user);

    Merchant selectByTel(String tel);
}
