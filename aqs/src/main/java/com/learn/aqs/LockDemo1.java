package com.learn.aqs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 将hashMap改造成一个并发安全的
 * 比hashTable的实现,效率提高,读取的适合并不会同步执行
 *
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/5/31 10:44
 */
public class LockDemo1 {

    private final Map<String, Object> map = new HashMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public Object[] allKeys(){
        r.lock();
        try {
            return map.keySet().toArray();
        } finally {
            r.unlock();
        }
    }

    public void put(String key,Object value){

        w.lock();
        try {
            map.put(key,value);
        } finally {
            w.unlock();
        }
    }
}
