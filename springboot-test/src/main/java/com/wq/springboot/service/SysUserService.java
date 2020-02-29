package com.wq.springboot.service;

import com.wq.springboot.domain.User;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/1/5 17:28
 * @Version 1.0
 */
public interface SysUserService {

    public User queryUser(String userId);
}
