package com.example;

import com.example.pojo.entity.HistoryPassword;
import com.example.service.CheckPwdService;
import com.example.service.impl.CheckPwdServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;


import java.time.LocalDateTime;

@SpringBootTest
class HaloProjectApplicationTests {

    @Test
    void contextLoads() {
        CheckPwdService checkpwd=new CheckPwdServiceImpl();
        String password = "123@!a";
        if (checkpwd.checkpwd1(password)) {
            System.out.println("符合规则");
        }else {
            System.out.println("不符合规则");
        }
    }
    @Test
    void testMD5(){
        String s = DigestUtils.md5DigestAsHex("1112223".getBytes());
        System.out.println("s = " + s);
        String s1 = DigestUtils.md5DigestAsHex("C12345678".getBytes());
        System.out.println("s1 = " + s1);
        String a=md5Hex("H92982395982");
        System.out.println(a);
//        HistoryPassword hp=new HistoryPassword();
//        List<HistoryPassword> hps=new ArrayList<>();
//        if(hps==null)
//            System.out.println("hps:null");
//
//        System.out.println( hps.size());
//        System.out.println(hps);
//        List<String> abc=new ArrayList<>();
//        abc.set(1,"aaa");
//        System.out.println();
    }
}
