package com.cjh.online.restaurant.mapper;




import com.cjh.online.restaurant.entity.Merchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantMapper {
    //    public Userbean login(String tel,String password);
    public List<Merchant> selectAll();
    public List<Merchant> selectAllManage(String merchant_id);
    public List<Merchant> selectManage();
    public List<Merchant> selectAllUser(String merchant_id);
    public Merchant selectById(int id);
    public Merchant selectByTel(String tel);
    public int insert(Merchant user);
    public int register(Merchant user);
    public int update(Merchant user);
    public int updateByAdmin(Merchant user);
    public int updateRole(Merchant user);
    public int delete(String tel);
    public int deleteById(int id);
    public int deleteAdminById(int id);
    public int checkUser(String tel);
    public int checkUserByFore(String tel);
    public Merchant selectLogin(@Param("tel") String tel, @Param("password") String password);
    public int checkEmail(String email);
    public int checkUsername(String username);
    public String selectQuestion(String tel);
    public int checkAnswer(@Param("tel") String tel, @Param("question") String question, @Param("answer") String answer);
    public int updatePasswordByTel(@Param("tel") String tel, @Param("password") String passwordNew);
    public int checkPassword(@Param("password") String password, @Param("tel") String tel);
    public int checkEmailByTel(@Param("tel") String tel, @Param("email") String email);
    public int updateInform(Merchant user);
}
