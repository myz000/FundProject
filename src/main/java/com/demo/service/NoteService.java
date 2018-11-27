package com.demo.service;

import com.demo.entity.NoteBody;

public interface NoteService {
    public NoteBody PhoneVerify(int code, String tel);
}
