package com.example.yytian.simplemocktest;

import android.util.Log;

import com.example.yytian.simplemocktest.data.bean.User;
import com.example.yytian.simplemocktest.data.ormlite.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.sql.SQLException;
import java.util.List;

/**
 * author:  yytian
 * time:    2016/8/30 23:20
 * des:
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class OrmliteTest {
    @Test
    public void testAddUser() {

        User u1 = new User("tian", "2B青年");
        DatabaseHelper helper = DatabaseHelper.getHelper(BaseApplication.getApplication());
        try {
            Dao<User, Integer> userDao = helper.getUserDao();
            userDao.create(u1);
            u1 = new User("tian2", "2B青年");
            userDao.create(u1);
            u1 = new User("tian3", "2B青年");
            userDao.create(u1);
            u1 = new User("tian4", "2B青年");
            userDao.create(u1);
            u1 = new User("tian5", "2B青年");
            userDao.create(u1);
            u1 = new User("tian6", "2B青年");
            userDao.create(u1);

            testList();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testList() {
        DatabaseHelper helper = DatabaseHelper.getHelper(BaseApplication.getApplication());
        try {
            User u1 = new User("tian-android", "2B青年");
            u1.setId(2);
            List<User> users = helper.getUserDao().queryForAll();
            Log.e("TAG", users.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
