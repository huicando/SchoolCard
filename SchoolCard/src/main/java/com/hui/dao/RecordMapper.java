package com.hui.dao;

import com.hui.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    public void  addRecord(Record record);
    public List<Record> findByCardId(String cardId);
    public float countMoneyByCardId(String cardId);
    public int countByCardId(String cardId);
}
