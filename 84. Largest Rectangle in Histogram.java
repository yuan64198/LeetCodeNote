/**
 * First, we put the index of the incoming histogram bar into the stack.
 * Second, whenever we encounter a value that's higher than the value of the peek of the stack,
 * we start to pop out the peek and calculate the area.
 * The formula is: area = val[stack.pop()]*(i-stack.peek()-1);
 * 
 * If after we go through the whole array, there's still some values in the stack,
 * we pop out them and calculate the area using this formula:
 * area = val[stack.pop()]*(val.length-stack.peek()-1);
 */

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        int result = 0;
        for(int i=0; i<heights.length; ++i) {
            while(stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                result = Math.max(result, heights[stack.pop()]*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        
        while(stack.peek() != -1) {
            result = Math.max(result, heights[stack.pop()]*(heights.length-stack.peek()-1));    
        }
        
        return result;
    }
}
