package com.leex.service.impl;

import com.leex.dao.MyDao;
import com.leex.service.MyService;

/**
 * @Author : 86167
 * @Description : MyServiceImpl 2021/3/15 23:17 86167
 */
public class MyServiceImpl implements MyService {

    private MyDao myDao;

    private String str;

    @Override
    public void process() {
        myDao.doSomeThing(str);
    }

    public void setMyDao(MyDao myDao) {
        this.myDao = myDao;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
