package com.hui.dao;

import com.hui.pojo.Card;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CardMapper {

    public void addCard(Card card);
    public String checkCard(Card card);


}
