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

    public Invest findInvestById(long id) {
        Invest invest = null;
        try {
            invest = investRepository.findById(id);
        } catch (Exception e) {
        }
        return invest;
    }


    public ArrayList findInvestByUserId(long userId) {
        ArrayList investList = null;
        try {
            investList = investRepository.findByUserId(userId);
        } catch (Exception e) {
        }
        return investList;
    }

    public ArrayList findValidInvestByUserId(long userId) {
        ArrayList investList = null;
        try {
            investList = investRepository.findInvestedByUserId(userId);
        } catch (Exception e) {
        }
        return investList;
    }


    public Invest findInvestByUserIdFundcode(long userId, String fundcode) {
        Invest investList = null;
        try {
            investList = investRepository.findInvestedByUserIdFundcode(userId, fundcode);
        } catch (Exception e) {
        }
        return investList;
    }



    public void StopInvest(long userId, String fundcode) {
        investRepository.UpdateStateByUserIdFundCode(userId, fundcode, 0);
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
