package com.hui.service.impl;

import com.hui.dao.UserMapper;
import com.hui.pojo.User;
import com.hui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceInpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 进入小程序时，获取用户的唯一标识openid来确定是否登陆过,如果没有就添加用户信息
     * @param openid
     * @return
     */
    @Override
    public User findByOpenid(String openid) {
        System.out.println("openid = " + openid);
        User user =(User) userMapper.findByOpenid(openid);
        System.out.println("user = " + user);
        if(user==null){
            user = new User();
            user.setOpenid(openid);
//            System.out.println("user = " + user);
            userMapper.addUser(user);   //如果没有用户信息就添加。
        }

        return user;
    }

    /**
     * 添加用户，进入之后就会根据openid，添加用户信息
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {

        return userMapper.addUser(user);
    }

    /**
     * 更新用户信息，保存用户信息后因为没有授权登陆，所以在授权之后需要修改用户信息。
     * @param user
     * @return
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
//          return user;
    }

    /**
     * 绑定或解绑校园卡。
     * @param user
     */
    @Override
    public void updateCardId(User user) {
        userMapper.updateCardId(user);
    }
}
