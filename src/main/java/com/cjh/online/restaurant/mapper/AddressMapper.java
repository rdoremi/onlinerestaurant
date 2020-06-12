package com.cjh.online.restaurant.mapper;


import com.cjh.online.restaurant.entity.Address;


import java.util.List;


public interface AddressMapper {
    public int insert(Address address);
    public int update(Address address);
    public int deleteById(Integer id);
    public List<Address> selectAddressByUserId(Integer userId);
    public Address selectAddressById(Integer id);
}
