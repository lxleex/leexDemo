package com.leex.dao.Impl;

import com.leex.dao.MyDao;

/**
 * @Author : 86167
 * @Description : MyDaoImpl 2021/3/15 23:15 86167
 */
public class MyMysqlDaoImpl implements MyDao {

    @Override
    public void doSomeThing(String str) {
        System.out.println("这是mysql的Dao层, 传入" + str);

    }
}
