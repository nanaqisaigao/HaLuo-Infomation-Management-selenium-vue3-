package com.example.utils;

import com.example.pojo.entity.User;

public class ThreadLocalUtils {

    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setCurrentUser(User user){
        threadLocal.set(user);
    }

    public static User getCurrentUser(){
        return threadLocal.get();
    }

    public static void removeCurrentUser(){
        threadLocal.remove();
    }

}
