package com.hui.service.impl;

import com.hui.dao.CardMapper;
import com.hui.dao.RecordMapper;
import com.hui.dao.UserMapper;
import com.hui.pojo.Card;
import com.hui.pojo.User;
import com.hui.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void addCard(Card card) {

        cardMapper.addCard(card);
    }

    /**
     * 检查是否存在该卡，如果有，就校对校园卡密码，密码正确就绑定，错误就不绑定，如果没有存在该卡，就添加该卡并绑定。
     * @param card
     * @return
     */
    @Override
    public String checkCard(Card card) {
        System.out.println("card = " + card);
        String password=cardMapper.checkCard(card);
        User user = new User();
        user.setOpenid(card.getOpenid());
        user.setCardId(card.getCardId());
        if(password==null||password.equals("")){
            cardMapper.addCard(card);
            userMapper.updateCardId(user);
            return "绑定成功";
        }else if(card.getPassword().equals(password)){
            userMapper.updateCardId(user);
            return "绑定成功";
        }
        return "卡号或密码错误";
    }

    @Override
    public boolean checkCard1(Card card) {
        String password=cardMapper.checkCard(card);
        if(card.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }

    }
}
