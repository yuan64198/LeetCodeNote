/**
 * Approach1 : dynamic programming
 * Use an arry to store the computational results of each number num.
 * If a number is already calculated, we can directly access the array the get the result.
 * time complexity: O(n*sqrt(n))
 */

class Solution {
    public int numSquares(int n) {
        int[] memo = new int[n+1];
        for(int i=0; i<n+1; ++i) memo[i] = -1;
        memo[0] = 0;
        memo[1] = 1;
        
        return helper(n, memo);
        
    }
    private int helper(int n, int[] memo) {
        if(memo[n] != -1) return memo[n];
        int result = Integer.MAX_VALUE;
        for(int i=1; i <= Math.sqrt(n); ++i) {
            result = Math.min(result, helper(n-i*i, memo)+1);
        }
        memo[n] = result;
        return result;
    }
}
