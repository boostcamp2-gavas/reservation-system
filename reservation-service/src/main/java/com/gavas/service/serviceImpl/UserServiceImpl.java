package com.gavas.service.serviceImpl;

import com.gavas.dao.UserDao;
import com.gavas.domain.User;
import com.gavas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(String snsId) {
        return userDao.selectUserBySnsId(snsId);
    }

    @Transactional
    @Override
    public Long addUser(User user) {
        return userDao.insertUser(user);
    }
}
