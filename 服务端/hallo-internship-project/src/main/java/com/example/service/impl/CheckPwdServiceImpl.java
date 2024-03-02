package com.example.service.impl;

import com.example.mapper.HistoryPwdMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.entity.HistoryPassword;
import com.example.pojo.entity.PwdParams;
import com.example.service.CheckPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CheckPwdServiceImpl implements CheckPwdService {
    @Autowired
    HistoryPwdMapper historyPwdMapper;
    @Autowired
    UserMapper userMapper;
    private static String regEx = "^(?:(?=.*[a-z])(?=.*[A-Z])|(?=.*[a-z])(?=.*\\d)|(?=.*[A-Z])(?=.*\\d)|(?=.*[a-z])(?=.*[@#$%^&+=!])|(?=.*[A-Z])(?=.*[@#$%^&+=!])|(?=.*\\d)(?=.*[@#$%^&+=!]))[a-zA-Z0-9@#$%^&+=!]{8,}$";
    @Override
    public boolean checkpwd1(String password){//密码规则判断
        Pattern Password_Pattern = Pattern.compile(regEx);
        Matcher matcher = Password_Pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    @Override
    public String checkpwd2(PwdParams password) {
        String msg="密码修改成功";
        String check=userMapper.selectByPrimaryKey(password.getUserId()).getPassword();
        password.setHistoricalPassword(DigestUtils.md5DigestAsHex(password.getHistoricalPassword().getBytes()));
        password.setPassword(DigestUtils.md5DigestAsHex(password.getPassword().getBytes()));
        if(check.equals(password.getHistoricalPassword())){//当前密码是否正确
            List<HistoryPassword> hps=historyPwdMapper.getpwd(password.getUserId());
            HistoryPassword hp=new HistoryPassword();
            hp.setUserId(password.getUserId());
            hp.setHistoricalPassword(password.getHistoricalPassword());
            int size= hps.size();
            Collections.sort(hps, Comparator.comparing(HistoryPassword::getId));//从小到大
            for(int i=0;i<size;i++)
                if(hps.get(i).getHistoricalPassword().equals(password.getPassword())){//历史三次密码不能重复
                    msg="修改密码与最近3次历史密码重复";
                    break;
                }
            if(!msg.equals("修改密码与最近3次历史密码重复")) {
                if(size>=3)
                    historyPwdMapper.update(hps.get(0).getId());
                historyPwdMapper.insert(hp);
                userMapper.updatePwd(password);
            }
        }
        else msg="当前密码错误";
        return msg;
    }
}
