package com.demo.service.TableService.Impl;

import com.demo.entity.Trend;
import com.demo.repository.TrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
public class TrendService {
    @Autowired
    private TrendRepository trendRepository;

    public Trend findTrendById(long id) {
        Trend trend = null;
        try {
            trend = trendRepository.findById(id);
        } catch (Exception e) {
        }
        return trend;
    }

    public List<Trend> findTrendsByInvestId(String investId) {
        List<Trend> trendList = null;
        try {
            trendList = trendRepository.findByInvestId(investId);
        } catch (Exception e) {
        }
        return trendList;
    }


    @Transactional
    public void saveTrend(Trend trend) {
        trendRepository.save(trend);
    }

    @Transactional
    public void deleteTrendById(long id) {
        trendRepository.delete(id);
    }

    @Transactional
    public void deleteTrendsByUserId(String userId) {
        trendRepository.deleteByUserId(userId);
    }


}
