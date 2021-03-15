package com.leex.算法.二分查找;

/**
 * @Author : 86167
 * @Description : dsad 2021/3/11 21:24 86167
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,3,5,9,12};
        int target = -1;
        System.out.println(findingNumbersThroughDichotomy(nums, 0, nums.length -1, target));
    }

    private static int findingNumbersThroughDichotomy(int[] nums, int left, int right, int target){
        if(null == nums || left > right){
            return -1;
        }

        int center = (right - left)/2 + left;

        if(nums[center] == target){
            return center;
        }else if(nums[center] > target){
            return findingNumbersThroughDichotomy(nums, left, center - 1, target);
        } else {
            return findingNumbersThroughDichotomy(nums,center + 1, right, target);
        }

    }
}
