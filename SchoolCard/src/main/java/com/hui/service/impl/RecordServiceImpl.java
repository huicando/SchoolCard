package com.hui.service.impl;

import com.hui.dao.RecordMapper;
import com.hui.pojo.Record;
import com.hui.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 添加消费记录,根据方式判断需要存入的是正数还是负数。
     * @param record
     */
    @Override
    public void addRecord(Record record) {
        if(!record.getWay().equals("充值")){
            record.setPrice((float) (record.getPrice()*(-1.0)));
        }
        recordMapper.addRecord(record);
    }

    /**
     * 根据卡号查询记录。
     * @param cardId
     * @return
     */
    @Override
    public List<Record> findByCardId(String cardId) {
        return recordMapper.findByCardId(cardId);
    }

    /**
     * 根据卡号，查询剩余的钱数,如果还没有记录就返回0。
     * @param cardId
     * @return
     */
    @Override
    public float countMoneyByCardId(String cardId) {
        if(recordMapper.countByCardId(cardId)>0){
            return recordMapper.countMoneyByCardId(cardId);
        }
        return 0;
    }
}
