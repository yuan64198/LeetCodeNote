/**
 * Approach 1: Stack
 * The main idea of this approach is to use the variable newInterval to store the latest time interval;
 * We use a for-loop to go through the whole array, if an interval has intersection with newInterval, we merge them.
 * If they have no intersection, we put the time interval with earlier time into the stack, and store the later in newInterval.
 * After we go through the whole array, we then put newInterval, which is the time interval with latest time, into the stack.
 * when generating the result, we then pop stack and put the popped time interval into the result array from the end.
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        Stack<int[]> stack = new Stack();
        
        for(int[] interval :intervals) {
            if(interval[1] < newInterval[0]) {
                stack.add(interval);
            }
            else if(interval[0] > newInterval[1]) {
                stack.add(newInterval);
                newInterval = interval;
            }
            else {
                newInterval = new int[]{Math.min(interval[0], newInterval[0]), Math.max(interval[1], newInterval[1])};
            }
        }
        
        stack.add(newInterval);
        
        int[][] result = new int[stack.size()][2];
        int index = result.length-1;
        while(!stack.isEmpty()) {
            int[] tmp = stack.pop();
            result[index][0] = tmp[0];
            result[index][1] = tmp[1];
            index -= 1;
        }
        
        return result;
    }
    
}
