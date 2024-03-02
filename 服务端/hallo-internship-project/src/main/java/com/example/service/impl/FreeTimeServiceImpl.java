package com.example.service.impl;

import com.example.mapper.FreeTimeMapper;
import com.example.pojo.entity.TFree;
import com.example.pojo.entity.TFreetimes;
import com.example.service.FreeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FreeTimeServiceImpl implements FreeTimeService {
    @Autowired
    private FreeTimeMapper freeTimeMapper;

    LocalDate date;
    LocalDate thisWeekMondayDate;
    LocalDate thisWeekFridayDate ;
    LocalDate nextWeekFridayDate ;
    LocalDate nextWeekMondayDate ;
    DateTimeFormatter formatter ;

    @Override
    public int[][] getOne(int userId, int index){
        int[][] now=new int[9][5];
        int[][] next=new int[9][5];
        List<String> list =freeTimeMapper.getOne(userId);
        getBoundary();
        for (String i:list) {
            LocalDateTime dateTime = LocalDateTime.parse(i, formatter);
            LocalDate date1 = dateTime.toLocalDate();
            int hour=dateTime.getHour();
            if(hour>=9&&hour<=11) hour-=9;
            else if(hour>=14&&hour<=17) hour-=11;
            else hour-=12;
            if (!date1.isBefore(thisWeekMondayDate)&&!date1.isAfter(thisWeekFridayDate))
                    now[hour][date1.getDayOfWeek().getValue()-1]=1;
            if (!date1.isBefore(nextWeekMondayDate)&&!date1.isAfter(nextWeekFridayDate))
                    next[hour][date1.getDayOfWeek().getValue()-1]=1;
        }
        if(index==1) return now;
        else  return next;
    }

    @Override
    public void changeTime(int userId,int index,int day,int time,int status){
        LocalDate date=LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate nowdate,nextdate;
        LocalDateTime dateTime;
        if(index==1) {
            nowdate = date.plusDays(day - 1);
            dateTime=nowdate.atStartOfDay();
        } else{
            nextdate=date.plusDays(7+day-1);
            dateTime=nextdate.atStartOfDay();
        }
        int period=0;
        switch (time){
            case 1:period=9;break;
            case 2:period=10;break;
            case 3:period=11;break;
            case 4:period=14;break;
            case 5:period=15;break;
            case 6:period=16;break;
            case 7:period=17;break;
            case 8:period=19;break;
            case 9:period=20;break;
        }
        LocalDateTime freeTime = dateTime.withHour(period).withMinute(0).withSecond(0);
        if(status==1) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime update = LocalDateTime.now();
            freeTimeMapper.add(userId, freeTime, now, update, 0);
        }
        else
            freeTimeMapper.change(userId,freeTime);
    }

    @Override
    public TFreetimes getAll(int index){
        int sum=freeTimeMapper.count();
        TFreetimes tFreetimes;
        List<TFree> tFrees=new ArrayList<>();
        int[][] now=new int[9][5];
        int[][] next=new int[9][5];
        List<String> list =freeTimeMapper.getAll();
        getBoundary();
        for (String i:list) {
            LocalDateTime dateTime = LocalDateTime.parse(i, formatter);
            LocalDate date1 = dateTime.toLocalDate();
            int hour=dateTime.getHour();
            if(hour>=9&&hour<=11) hour-=9;
            else if(hour>=14&&hour<=17) hour-=11;
            else hour-=12;
            if (!date1.isBefore(thisWeekMondayDate)&&!date1.isAfter(thisWeekFridayDate))
                now[hour][date1.getDayOfWeek().getValue()-1]+=1;
            if (!date1.isBefore(nextWeekMondayDate)&&!date1.isAfter(nextWeekFridayDate))
                next[hour][date1.getDayOfWeek().getValue()-1]+=1;
        }
        List<String> timeStrings = Arrays.asList(
                "9:00-10:00", "10:00-11:00", "11:00-12:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00","19:00-20:00","20:00-21:00"
        );
        if(index==1)
            for(int i=1;i<=9;i++){
                TFree tFree=new TFree();
                tFree.setId(i);tFree.setTime(timeStrings.get(i-1));tFree.setMon(now[i-1][0]);
                tFree.setTues(now[i-1][1]);tFree.setWednes(now[i-1][2]);
                tFree.setThurs(now[i-1][3]);tFree.setFri(now[i-1][4]);
                tFrees.add(tFree);
            }
        else
            for(int i=1;i<=9;i++){
                TFree tFree=new TFree();
                tFree.setId(i);tFree.setTime(timeStrings.get(i-1));tFree.setMon(next[i-1][0]);
                tFree.setTues(next[i-1][1]);tFree.setWednes(next[i-1][2]);
                tFree.setThurs(next[i-1][3]);tFree.setFri(next[i-1][4]);
                tFrees.add(tFree);
            }
        tFreetimes = new TFreetimes(sum,tFrees);
        return tFreetimes;
    }

    public void getBoundary(){
        date = LocalDate.now().with(DayOfWeek.MONDAY);
        thisWeekMondayDate = date;
        thisWeekFridayDate = date.plusDays(4);
        nextWeekFridayDate = date.plusDays(11);
        nextWeekMondayDate = date.plusDays(7);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

}
