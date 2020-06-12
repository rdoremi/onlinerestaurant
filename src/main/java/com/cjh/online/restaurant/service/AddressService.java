package com.cjh.online.restaurant.service;



import com.cjh.online.restaurant.common.ServerResponse;
import com.cjh.online.restaurant.entity.Address;
import com.cjh.online.restaurant.vo.AddressVo;

import java.util.List;

public interface AddressService {
    ServerResponse add(Address address);
    ServerResponse<List<AddressVo>> getAddressByUserId(Integer userId);
    ServerResponse deleteAddressById(Integer id);
}
