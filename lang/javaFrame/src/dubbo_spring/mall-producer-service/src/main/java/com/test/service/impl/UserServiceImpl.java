package com.test.service.impl;

import com.test.pojo.UserAddress;
import com.test.service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * @author liyu
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {

        System.out.println("被调用，输入的id：" + userId);

        UserAddress address1 = new UserAddress(1, "河南省郑州巩义市宋陵大厦2F", "1", "安然", "150360313x", "Y");
        UserAddress address2 = new UserAddress(2, "北京市昌平区沙河镇沙阳路", "1", "情话", "1766666395x", "N");

        return Arrays.asList(address1, address2);
    }
}
