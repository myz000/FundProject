package com.demo.service.TableService.Impl;

import com.demo.entity.Inform;
import com.demo.repository.InformRepository;
import com.demo.service.TableService.InformService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;

public class InformServiceImpl implements InformService {
    @Autowired
    InformRepository informRepository;

    @Override
    public Inform findInfromById(Long id) {
        return informRepository.findById(id);
    }

    @Override
    public ArrayList<Inform> getInformList() {
        return informRepository.findList();
    }

    @Transactional
    public void saveInform(Inform inform) {
        informRepository.save(inform);
    }

    @Transactional
    public void deleteInformById(Long id) {
        informRepository.delete(id);
    }
}
