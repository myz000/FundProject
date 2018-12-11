package com.demo.service.TableService;

import com.demo.entity.Inform;

import java.util.ArrayList;

public interface InformService {
    Inform findInfromById(Long id);

    ArrayList<Inform> getInformList();

    void saveInform(Inform inform);

    void deleteInformById(Long id);
}
