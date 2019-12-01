package com.hui;


import com.hui.dao.CardMapper;
import com.hui.dao.RecordMapper;
import com.hui.dao.UserMapper;
import com.hui.pojo.Card;
import com.hui.pojo.Record;
import com.hui.pojo.User;
import com.hui.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CardApplication.class)
public class MybatisTest {
    @Autowired
    private UserService userMapper;

    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Test
    public  void testList(){
        User users = userMapper.findByOpenid("2");
        System.out.println(users);
    }

    @Test
    public void testCard(){
        Card card =new Card();
        card.setCardId("1111");
        card.setPassword("22222");
//        cardMapper.addCard(card);
        String password = cardMapper.checkCard(card);
        System.out.println("password = " + password);
        System.out.println("card = " + card);
        System.out.println("password.equals(card.getPassword()) = " + password.equals(card.getPassword()));
    }
    @Test
    public void testRecord(){
        Record record =new Record();
        record.setCardId("1111");
        record.setMark("11215");
        record.setPrice((float) -121.5);
        record.setWay("1151");
//        recordMapper.addRecord(record);
        System.out.println(recordMapper.findByCardId("1111"));
        Float k =recordMapper.countMoneyByCardId("1111");
        System.out.println("k = " + k);
    }


}
