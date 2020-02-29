package com.wq.springboot.service.impl;

import com.wq.springboot.domain.User;
import com.wq.springboot.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/1/5 17:29
 * @Version 1.0
 */
@Service
public class SysuserServiceImpl  implements SysUserService {
    @Override
    public User queryUser(String userId) {

        User user  =new User ("001","wq",12);
        return user;
    }

    public  void deleteMoth(List<String> paramList){
        String s = paramList.get(0);
        System.out.println("当前元素========"+s);
        paramList.remove("12");
    }
}
