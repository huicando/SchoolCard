package com.hui.controller;

import com.hui.pojo.Card;
import com.hui.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/card")
@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/checkCard")
    @ResponseBody
    public Boolean checkCard(@RequestBody Card card){
        return cardService.checkCard(card).equals("绑定成功")?true : false;
    }

    @RequestMapping("/checkCard1")
    @ResponseBody
    public Boolean checkCard1(@RequestBody Card card){
        return cardService.checkCard1(card);
    }

}
