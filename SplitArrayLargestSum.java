/**
 * Approach 1: brute force.
 * Use recursion to calculate the sum of each divided section.
 * And in each section, we recursively calculate the sum of the rest of the array.
 * We use a variable tmp to record [The return value of current division method].
 * But what we need is to find a division method that can minimize the max value of each division.
 * Hence, we use another variable result to record [The value of the division method with minimum max value].
 * Time complexity: O(n^m)
 *
 * In this approach, we have many redundant calculation, so in order to make the time complexity lower, we need to do dynamic programming.
 * The code is almost the same, but we use a hashmap to record the value which we have already calculated.
 * Note that we cannot only remember the value from each starting point, because the m value (how many division remain) matters too.
 * So for the key of hashamp, we have to consider both the m value and start value.
 * Here I use a function to generate the key value for each situation (each m and start combination).
 * Time Complexity: O(n*m)
 */


class Solution {
    public int splitArray(int[] nums, int m) {
        return helper(nums, m, 0, new HashMap<Integer, Integer>());
    }
    private int produceKey(int m, int start) {
        return m*1000+start;
    }
    private int helper(int[] nums, int m, int start, Map<Integer, Integer> memo) {
        int key = produceKey(m, start);
        if(memo.containsKey(key) == true) {
            return memo.get(key);
        }
        
        if(m == 1) {
            int result = 0;
            for(int i=start; i<nums.length; ++i) result += nums[i];
            
            memo.put(key, result);
            
            return result;
        }
        
        int result = Integer.MAX_VALUE;
        int tmp = 0;
        int sum = 0;
        
        for(int i=start; i<=nums.length-m; ++i) {
            sum += nums[i];
            tmp = Math.max(sum, helper(nums, m-1, i+1, memo));
            result = Math.min(tmp, result);
        }
        
        memo.put(key, result);
        
        return result;
    }
}
