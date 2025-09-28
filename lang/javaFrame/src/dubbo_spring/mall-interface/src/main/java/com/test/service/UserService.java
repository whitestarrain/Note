package com.test.service;

import com.test.pojo.UserAddress;

import java.util.List;

/**
 * @author liyu
 * 用户服务
 */
public interface UserService {
    /**
     * 按照用户id返回所有的收货地址
     *
     * @param userId
     * @return
     */
    public List<UserAddress> getUserAddressList(String userId);
}
