package com.hui.service;

import com.hui.pojo.User;

public interface UserService {

    public User findByOpenid(String openid);
    public int addUser(User user);
    public void updateUser(User user);
    public void updateCardId(User user);

}
