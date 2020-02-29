package com.wq.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/1/5 16:26
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private  String userId;

    private  String userName;

    private  int age;
}
