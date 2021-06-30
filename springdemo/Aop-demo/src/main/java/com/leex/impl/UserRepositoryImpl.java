package com.leex.impl;

import com.leex.UserRepository;

/**
 * @Author : 86167
 * @Description : UserRepositoryImpl 2021/3/29 15:16 86167
 */
public class UserRepositoryImpl implements UserRepository {
    @Override
    public void add() {
        System.out.println("add执行，新增了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("delete执行，删除了一个用户");
    }

    @Override
    public void update() {
        System.out.println("update执行，更新用户");
    }

    @Override
    public void search() {
        System.out.println("search执行，查询到了用户");
    }
}
