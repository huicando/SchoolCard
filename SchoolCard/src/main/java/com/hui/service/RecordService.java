package com.hui.service;

import com.hui.pojo.Record;

import java.util.List;

public interface RecordService {
    public void  addRecord(Record record);
    public List<Record> findByCardId(String cardId);
    public float countMoneyByCardId(String cardId);
}
