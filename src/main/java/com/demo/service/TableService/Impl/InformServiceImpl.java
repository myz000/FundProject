package com.demo.service.TableService.Impl;

import com.demo.entity.Inform;
import com.demo.repository.InformRepository;
import com.demo.service.TableService.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Component
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
