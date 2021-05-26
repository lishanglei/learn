package com.learn.aqs;

import org.springframework.context.annotation.Configuration;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/5/25 16:38
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
