package com.test.service.impl;

import com.test.pojo.UserAddress;
import com.test.service.OrderService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liyu
 * 本地服务调用远程服务
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;  // 会把远程服务代理对象注入进去

    @Override
    public void initOrder(String userID) {
        System.out.println("输入的ID："+userID);
        List<UserAddress> userAddressList = userService.getUserAddressList(userID);
        System.out.println("所有的用户：");
        System.out.println(userAddressList);
    }
}
