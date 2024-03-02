package com.example.service;

import com.example.pojo.entity.HistoryPassword;
import com.example.pojo.entity.PwdParams;

public interface CheckPwdService {
    boolean checkpwd1(String pwd);
    String checkpwd2(PwdParams password);
}
