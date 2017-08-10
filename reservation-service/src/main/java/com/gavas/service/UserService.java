package com.gavas.service;

import com.gavas.domain.User;

public interface UserService {
    User getUser(String snsId);
    Long addUser(User user);

}
