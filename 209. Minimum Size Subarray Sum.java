/**
 * Use two pointers. Both start from 0, when the sum smaller than s, move the right pointer.
 * When the sum bigger than s, move the left pointer.
 */

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
        
        int[] sumBefore = new int[nums.length + 1];
        int left = 0, right = 1;
        sumBefore[0] = nums[0];
        for(int i = 1; i <= nums.length; ++i) {
            sumBefore[i] = sumBefore[i-1] + nums[i-1];
        }
        
        int result = Integer.MAX_VALUE;
        // nums[i] = sumBefore[i+1] - sumBefore[i];
        while(left < right && right <= nums.length) {
            while(right < nums.length && sumBefore[right] - sumBefore[left] < s) right++;
            int sum = sumBefore[right] - sumBefore[left];
            //System.out.println(sum);
            if(sum >= s) result = Math.min(result, right - left);
            left++;
        }
        
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
