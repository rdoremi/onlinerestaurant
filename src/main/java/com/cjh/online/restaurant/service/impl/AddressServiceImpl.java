package com.cjh.online.restaurant.service.impl;



import com.cjh.online.restaurant.common.ResponseCode;
import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Address;
import com.cjh.online.restaurant.mapper.AddressMapper;
import com.cjh.online.restaurant.service.AddressService;
import com.cjh.online.restaurant.vo.AddressVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public ServerResponse add(Address address) {
        if (address == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
        }
        try {
            int rowCount  = addressMapper.insert(address);
            if (rowCount > 0){
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),"添加地址成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    @Override
    public ServerResponse<List<AddressVo>> getAddressByUserId(Integer userId) {
        if (userId < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
        }
        List<Address> list = addressMapper.selectAddressByUserId(userId);
        List<AddressVo> addressVoList = Lists.newArrayList();
        if (list == null){
            return ServerResponse.createByErrorMessage("没有地址信息");
        }
        for (Address address : list) {
            AddressVo addressVo = this.getAddressVo(address);
            addressVoList.add(addressVo);
        }
        return ServerResponse.createBySuccess(addressVoList);
    }

    @Override
    public ServerResponse deleteAddressById(Integer id) {
        if (id < 0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
        }
        try {
            int rowCount = addressMapper.deleteById(id);
            if (rowCount > 0){
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),"删除成功");
            }

        }catch (Exception e){

        }
        return null;
    }

    private AddressVo getAddressVo(Address address){
        AddressVo addressVo = new AddressVo();
        addressVo.setId(address.getId());
        addressVo.setReceiver(address.getReceiver());
        addressVo.setPostalcode(address.getPostalcode());
        addressVo.setTel(address.getTel());
        addressVo.setUserId(address.getUserId());
        addressVo.setCreateTime(address.getCreateTime());
        addressVo.setUpdateTime(address.getUpdateTime());
        addressVo.setDetail(address.getArea()+" "+address.getDetail());
        return addressVo;
    }
}
