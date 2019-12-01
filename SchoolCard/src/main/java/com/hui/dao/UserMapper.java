package com.hui.dao;

import com.hui.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public User findByOpenid(String openid);
    public int addUser(User user);
    public void updateUser(User user);
    public void updateCardId(User user);

}
