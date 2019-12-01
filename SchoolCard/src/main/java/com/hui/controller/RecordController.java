package com.hui.controller;

import com.hui.dao.RecordMapper;
import com.hui.pojo.Record;
import com.hui.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping("/addRecord")
    @ResponseBody
    public void  addRecord(@RequestBody Record record){
        recordService.addRecord(record);
    }

    @RequestMapping("/findByCardId")
    @ResponseBody
    public List<Record> findByCardId(@RequestParam("cardId") String cardId){
        return  recordService.findByCardId(cardId);
    }


    @RequestMapping("/countMoneyByCardId")
    @ResponseBody
    public Record countMoneyByCardId(@RequestParam("cardId") String cardId){
        float f= recordService.countMoneyByCardId(cardId);
        Record record =new Record();
        record.setCardId(cardId);
        record.setPrice(f);
        return record;
    }

}
