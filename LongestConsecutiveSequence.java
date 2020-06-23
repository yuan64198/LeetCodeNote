/**
 * Approach 1: Brute force
 * use a for loop to go through the entire array, for each num in the array, try to find the consecutive sequence starting from it.
 * Use a for loop to check if the array contains a specific element.
 * Time Complexity: O(n^3)
 */

class Solution {
    public int longestConsecutive(int[] nums) {
        int maxStreak = 1, currStreak = 0;
        if(nums.length == 0) return 0;
        Set<Integer> set = new HashSet();
        
        for(int num : nums) set.add(num);
        
        for(int num : nums) {
            int k = num;
            while(arrayContains(nums, k++)){
                currStreak++;
            }
            maxStreak = Math.max(currStreak, maxStreak);
            currStreak = 0;
        }
        return maxStreak;
    }
    
    private boolean arrayContains(int[] nums, int k) {
        for(int num : nums) {
            if(num == k) return true;
        }
        return false;
    }
}


/**
 * Approach 2: Use Hashset to search the next element, which decrease the time complexity of searching step to O(1)
 * And, To make sure we don't search from a number which already in a sequence, we make sure the number we start searching from is "the smallest number of a sequence"
 * In order to do so, we check the hashset if it contains a number just smaller than the current one (num-1).
 *Time Complexity: O(n)
 */

class Solution {
    public int longestConsecutive(int[] nums) {
        int maxStreak = 1, currStreak = 0;
        if(nums.length == 0) return 0;
        Set<Integer> set = new HashSet();
        
        for(int num : nums) set.add(num);
        
        for(int num : nums) {
            if(!set.contains(num-1)){
                int k = num;
                while(set.contains(k++)){
                    currStreak++;
                }
                maxStreak = Math.max(currStreak, maxStreak);
                currStreak = 0;
            }
        }
        return maxStreak;
    }
    
    private boolean arrayContains(int[] nums, int k) {
        for(int num : nums) {
            if(num == k) return true;
        }
        return false;
    }
}
