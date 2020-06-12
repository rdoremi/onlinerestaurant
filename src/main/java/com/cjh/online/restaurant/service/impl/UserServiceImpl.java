package com.cjh.online.restaurant.service.impl;


import com.cjh.online.restaurant.common.Const;
import com.cjh.online.restaurant.common.ResponseCode;
import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.User;
import com.cjh.online.restaurant.mapper.UserMapper;
import com.cjh.online.restaurant.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse addUser(User user) {

        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        try {
            int rowCount = userMapper.insert(user);
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
        return ServerResponse.createBySuccess(userMapper.selectById(id));
    }

    @Override
    public ServerResponse<PageInfo> getUserList(int pageNum, int pageSize) {
        if (pageNum < 0 || pageSize < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Page page = PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(page);
        List<User> userList = userMapper.selectAllUser();
        List<UserListVo> userListVos = Lists.newArrayList();
        for (User user : userList) {
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

        return ServerResponse.createBySuccess(userMapper.selectByTel(tel));
    }


    @Override
    public ServerResponse<User> login(String tel, String password) {
        if (StringUtil.isEmpty(tel) || StringUtil.isEmpty(password)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int resultCount = userMapper.checkUser(tel);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        User user = userMapper.selectLogin(tel, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        return ServerResponse.createBySuccess("登录成功", user);
    }

    @Override
    public ServerResponse<User> loginByFore(String tel,String password){
        if (StringUtil.isEmpty(tel) || StringUtil.isEmpty(password)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int resultCount = userMapper.checkUserByFore(tel);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        User user = userMapper.selectLogin(tel, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc(), user);
    }

    @Override
    public ServerResponse selectAllManage(int pageNum, int pageSize, User us) {
        if (pageNum < 0 || pageSize < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAllManage();
        PageInfo pageInfo = new PageInfo(page);

        List<UserListVo> userListVos = Lists.newArrayList();
        for (User user : list) {
            UserListVo userListVo = this.getUserListVo(user);
            userListVos.add(userListVo);
        }
        pageInfo.setList(userListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse update(User user) {
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        try {
            int rowCount = userMapper.update(user);//仅更新普通用户信息
            return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc())
                    : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
    }

    @Override
    public ServerResponse updateByAdmin(User user) {
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            int rowCount = userMapper.updateByAdmin(user);//更新所有人信息
            return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()) : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
    }

    @Override
    public ServerResponse updateRole(User user) {
        return null;
    }


    @Override
    public ServerResponse deleteAdmin(int id) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> selectAllUser(Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);

        List<User> userList = userMapper.selectAllUser();
        List<UserListVo> userListVos = Lists.newArrayList();
        for (User user : userList) {
            UserListVo userListVo = this.getUserListVo(user);
            userListVos.add(userListVo);
        }
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(userListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteById(int id, User user) {

        if (id < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        try {
            if (this.checkSuperAdminRole(user).isSuccess()) {
                int rowCount = userMapper.deleteById(id);//可删除所有用户
                return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()) : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
            }
            int rowCount = userMapper.deleteAdminById(id);//仅删除普通用户
            return rowCount > 0 ? ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc()) : ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());

        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
    }

    @Override
    public ServerResponse<String> register(User user) {
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
    public ServerResponse<String> resetPassword(String passwordold, String passwordnew, User user) {
        return null;
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        return null;
    }

    @Override
    public ServerResponse<User> getInformation(String tel) {
        return null;
    }

    @Override
    public ServerResponse checkAdminRole(User user) {
        if (user.getRole().intValue() == Const.Role.ROLE_ADMIN || user.getRole().intValue() == Const.Role.ROLE_SUPER_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
    @Override
    public ServerResponse checkShopRole(User user) {
        if (user.getRole().intValue() == Const.Role.ROLE_MERCHANT ) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
    @Override
    public ServerResponse checkSuperAdminRole(User user) {
        if (user.getRole() == Const.Role.ROLE_SUPER_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    private UserListVo getUserListVo(User user) {
        UserListVo userListVo = new UserListVo();
        userListVo.setTel(user.getTel());
        userListVo.setId(user.getId());
        userListVo.setUsername(user.getUsername());
        userListVo.setEmail(user.getEmail());
        userListVo.setStatus(user.getStatus());
        userListVo.setCreate_time(user.getCreate_time());
        userListVo.setUpdate_time(user.getUpdate_time());
        if (user.getRole() == Const.Role.ROLE_ADMIN) {
            userListVo.setRole("管理员");
        } else if (user.getRole() == Const.Role.ROLE_SUPER_ADMIN) {
            userListVo.setRole("超级管理员");
        } else {
            userListVo.setRole("普通用户");
        }
        return userListVo;
    }

    @Override
    public User selectByTel(String tel) {
        if (StringUtils.isEmpty(tel)) {
            return null;
        }
        return userMapper.selectByTel(tel);
    }
}
