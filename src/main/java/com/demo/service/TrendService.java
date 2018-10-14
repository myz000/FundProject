package com.demo.service;

import com.demo.entity.Trend;
import com.demo.repository.TrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class TrendService {
    @Autowired
    private TrendRepository trendRepository;

    public Trend findTrendById(long id){
        Trend trend=null;
        try{
           trend=trendRepository.findById(id);
        }catch (Exception e){}
        return trend;
    };

    public ArrayList findTrendByUserId(long userId){
        ArrayList trendList=null;
        try{
            trendList=trendRepository.findByUserId(userId);
        }catch (Exception e){}
        return trendList;
    };

    public Trend findLatestByUserIdFundCode(long userId, String fundCode){
       Trend trend=null;
        try{
            trend=trendRepository.findLatestByUserIdFundCode(userId,fundCode);
        }catch (Exception e){}
        return trend;
    };

    public void UpdateStateByUserIdFundCode(long userid,String fundcode,int state){
        trendRepository.UpdateStateByUserIdFundCode(userid,fundcode,state);
    }


    @Transactional
    public void saveTrend(Trend invest){trendRepository.save(invest);};

    @Transactional
    public void deleteTrendById(long id){trendRepository.delete(id);}



}
