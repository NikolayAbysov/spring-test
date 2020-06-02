package com.dev.springapp;

import com.dev.springapp.appconfig.AppConfig;
import com.dev.springapp.model.User;
import com.dev.springapp.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("reimu.hakurei@i.ua", "1234"));
        System.out.println(userService.listUsers());
    }
}
