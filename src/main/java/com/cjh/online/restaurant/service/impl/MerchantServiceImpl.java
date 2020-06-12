package com.cjh.online.restaurant.service.impl;


import com.cjh.online.restaurant.common.Const;
import com.cjh.online.restaurant.common.ResponseCode;
import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Merchant;
import com.cjh.online.restaurant.mapper.MerchantMapper;
import com.cjh.online.restaurant.service.MerchantService;
import com.cjh.online.restaurant.vo.UserListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public ServerResponse addUser(Merchant user) {

        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        try {
            int rowCount = merchantMapper.insert(user);
            return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc())
                    : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
    }

    @Override
    public ServerResponse getUserById(Integer id) {
        if (id < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return ServerResponse.createBySuccess(merchantMapper.selectById(id));
    }

    @Override
    public ServerResponse<PageInfo> getUserList(int pageNum, int pageSize,String merchant_id) {
        if (pageNum < 0 || pageSize < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Page page = PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(page);
        List<Merchant> userList = merchantMapper.selectAllUser(merchant_id);
        List<UserListVo> userListVos = Lists.newArrayList();
        for (Merchant user : userList) {
            UserListVo userListVo = this.getUserListVo(user);
            userListVos.add(userListVo);
        }
        pageInfo.setList(userListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getUserByTel(String tel) {
        if (StringUtils.isEmpty(tel)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        return ServerResponse.createBySuccess(merchantMapper.selectByTel(tel));
    }


    @Override
    public ServerResponse<Merchant> login(String tel, String password) {
        if (StringUtil.isEmpty(tel) || StringUtil.isEmpty(password)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int resultCount = merchantMapper.checkUser(tel);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        Merchant merchant = merchantMapper.selectLogin(tel, password);
        if (merchant == null) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        return ServerResponse.createBySuccess("登录成功", merchant);
    }

    @Override
    public ServerResponse<Merchant> loginByFore(String tel,String password){
        if (StringUtil.isEmpty(tel) || StringUtil.isEmpty(password)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int resultCount = merchantMapper.checkUserByFore(tel);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        Merchant Merchant = merchantMapper.selectLogin(tel, password);
        if (Merchant == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc(), Merchant);
    }

    @Override
    public ServerResponse selectAllManage(int pageNum, int pageSize, Merchant us,String merchant_id) {
        if (pageNum < 0 || pageSize < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<Merchant> list = merchantMapper.selectAllManage(merchant_id);

        PageInfo pageInfo = new PageInfo(page);
        System.out.println(merchant_id);
        List<UserListVo> userListVos = Lists.newArrayList();
        for (Merchant user : list) {

            UserListVo userListVo = this.getUserListVo(user);
            userListVos.add(userListVo);
        }
        pageInfo.setList(userListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse update(Merchant user) {
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        try {
            int rowCount = merchantMapper.update(user);//仅更新普通用户信息
            return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc())
                    : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
    }

    @Override
    public ServerResponse updateByAdmin(Merchant user) {
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            int rowCount = merchantMapper.updateByAdmin(user);//更新所有人信息
            return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()) : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
    }

    @Override
    public ServerResponse updateRole(Merchant user) {
        return null;
    }


    @Override
    public ServerResponse deleteAdmin(int id) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> selectAllUser(Integer pageNum, Integer pageSize,String merchant_id) {
        Page page = PageHelper.startPage(pageNum, pageSize);

        List<Merchant> userList = merchantMapper.selectAllUser(merchant_id);
        List<UserListVo> userListVos = Lists.newArrayList();
        for (Merchant user : userList) {
            UserListVo userListVo = this.getUserListVo(user);
            userListVos.add(userListVo);
        }
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(userListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteById(int id, Merchant merchant) {

        if (id < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        try {
            if (this.checkSuperAdminRole(merchant).isSuccess()) {
                int rowCount = merchantMapper.deleteById(id);//可删除所有用户
                return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()) : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
            }
            int rowCount = merchantMapper.deleteAdminById(id);//仅删除普通用户
            return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()) : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());

        } catch (Exception e) {

        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    @Override
    public ServerResponse<String> register(Merchant user) {
        return null;
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        return null;
    }

    @Override
    public ServerResponse<String> selectQuestion(String tel) {
        return null;
    }

    @Override
    public ServerResponse<String> checkAnswer(String tel, String question, String answer) {
        return null;
    }

    @Override
    public ServerResponse<String> forgetRestPassword(String tel, String passwordNew, String forgetToker) {
        return null;
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordold, String passwordnew, Merchant user) {
        return null;
    }

    @Override
    public ServerResponse<Merchant> updateInformation(Merchant user) {
        return null;
    }

    @Override
    public ServerResponse<Merchant> getInformation(String tel) {
        return null;
    }

    @Override
    public ServerResponse checkAdminRole(Merchant user) {
        if (user.getRole().intValue() == Const.Role.ROLE_MERCHANT || user.getRole().intValue() == Const.Role.ROLE_MERCHANT_EMPLOYEE) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
    @Override
    public ServerResponse checkShopRole(Merchant user) {
        if (user.getRole().intValue() == Const.Role.ROLE_MERCHANT ) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
    @Override
    public ServerResponse checkSuperAdminRole(Merchant user) {
        if (user.getRole() == Const.Role.ROLE_MERCHANT) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    private UserListVo getUserListVo(Merchant merchant) {
        UserListVo userListVo = new UserListVo();

        userListVo.setUsername(merchant.getUsername());
        userListVo.setEmail(merchant.getEmail());
        userListVo.setTel(merchant.getTel());
        userListVo.setId(merchant.getId());
        userListVo.setMerchant_id(merchant.getMerchantId());
        userListVo.setStatus(merchant.getStatus());
        userListVo.setCreate_time(merchant.getCreate_time());
        userListVo.setUpdate_time(merchant.getUpdate_time());
        if (merchant.getRole() == Const.Role.ROLE_MERCHANT) {
            userListVo.setRole("店长");
        } else if (merchant.getRole() == Const.Role.ROLE_MERCHANT_EMPLOYEE) {
            userListVo.setRole("店员");
        }
        return userListVo;
    }

    @Override
    public Merchant selectByTel(String tel) {
        if (StringUtils.isEmpty(tel)) {
            return null;
        }
        return merchantMapper.selectByTel(tel);
    }
}
