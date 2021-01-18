package com.learn.arithmetic;

import org.junit.Test;

import java.util.Arrays;

/**
 * java  常规算法
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/28
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/28              lishanglei      v1.0.0           Created
 */
public class ArithmeticDemo {

    int[] a = {2, 5, 6, 3, 78, 62, 23, 15, 48, 16, 4, 56};

    /**
     * 冒泡排序
     */
    @Test
    public void test1() {
        for (int i = 0; i < a.length - 1; i++) {
            for (int x = 0; x < a.length - 1 - i; x++) {
                //每次循环,取出最大的一个放到最后边
                if (a[x] > a[x + 1]) {
                    int temp = a[x];
                    a[x] = a[x + 1];
                    a[x + 1] = temp;
                }
            }
        }

        System.out.println("冒泡排序结果: " + Arrays.toString(a));
    }


    /**
     * 选择排序
     */
    @Test
    public void test2() {

        for (int i = 0; i < a.length - 1; i++) {
            for (int x = i + 1; x < a.length; x++) {
                //每次循环,取出最小的放在最左边
                if (a[i] > a[x]) {
                    int temp = a[x];
                    a[x] = a[i];
                    a[i] = temp;
                }
            }
        }

        System.out.println("选择排序结果: " + Arrays.toString(a));
    }


    /**
     * 插入排序
     */
    @Test
    public void test3() {

        for (int i = 0; i < a.length - 1; i++) {
            //取出下一个元素，在已经排序的元素序列中从后向前扫描
            for (int j = i + 1; j > 0; j--) {
                //如果该元素（已排序）大于新元素，将该元素移到下一位置
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }

        System.out.println("插入排序结果: " + Arrays.toString(a));

    }


}
