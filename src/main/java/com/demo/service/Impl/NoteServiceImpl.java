package com.demo.service.Impl;

import com.demo.api.JuHeApiService;
import com.demo.entity.apiBody.NoteBody;
import com.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteServiceImpl implements NoteService {
    @Autowired
    JuHeApiService juHeApiService;

    @Override
    public NoteBody PhoneVerify(int code, String tel) {
        return juHeApiService.getNoteBody(code, tel);
    }
}
