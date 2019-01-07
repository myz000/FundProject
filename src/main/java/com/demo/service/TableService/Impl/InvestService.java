package com.demo.service.TableService.Impl;

import com.demo.entity.Invest;
import com.demo.repository.InvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Component
public class InvestService {
    @Autowired
    private InvestRepository investRepository;

    public Invest findInvestById(String id) {
        Invest invest = null;
        try {
            invest = investRepository.findById(id);
        } catch (Exception e) {
        }
        return invest;
    }


    public ArrayList findInvestByUserId(String userId) {
        ArrayList investList = null;
        try {
            investList = investRepository.findByUserId(userId);
        } catch (Exception e) {
        }
        return investList;
    }

    public void StopInvest(String investId) {
        investRepository.UpdateStateById(investId, 0);
    }


    @Transactional
    public void saveInvest(Invest invest) {
        investRepository.saveAndFlush(invest);
    }


    @Transactional
    public void deleteInvestById(long id) {
        investRepository.delete(id);
    }


}
