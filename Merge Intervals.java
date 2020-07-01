/**
 * We first sort the input array to make it its starting point arranged from smallest to largest.
 * Use a for-loop to go through the whole array, if the incoming interval has intersection with the peek of stack, 
 * we merge them and put the new interval back to the stack.
 * Otherwise, we put the interval into the stack straightly.
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        Comparator<int[]> intervalComp = (int[] a, int[] b) -> a[0] - b[0];
        Arrays.sort(intervals, intervalComp);
        Stack<int[]> stack = new Stack();
        
        for(int[] interval : intervals) {
            if(!stack.isEmpty() && stack.peek()[1] >= interval[0]) {
                int[] prev = stack.pop();
                stack.push(new int[]{Math.min(prev[0], interval[0]), Math.max(prev[1], interval[1])});
            }
            else {
                stack.push(interval);
            }
        }
        int[][] result = new int[stack.size()][2];
        for(int i = result.length - 1; i >= 0; --i) {
            result[i] = stack.pop();
        }
        return result;
    }
}
