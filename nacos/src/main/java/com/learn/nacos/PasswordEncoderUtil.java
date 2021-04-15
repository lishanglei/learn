package com.learn.nacos;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: lishanglei
 * @createDate: 2021/4/1
 */
public class PasswordEncoderUtil {
    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
