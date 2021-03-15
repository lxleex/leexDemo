package com.leex.算法.两数之和;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @Author : 86167
 * @Description : dsadsa 2021/3/11 21:48 86167
 */
public class SumOfTwoNumbers {

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] out = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    out = new int[]{i, j};
                }
            }
        }

        System.out.println(Arrays.toString(out));


        Map<Integer, Integer> hashMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                out = new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }

        System.out.println(Arrays.toString(out));
    }







}
