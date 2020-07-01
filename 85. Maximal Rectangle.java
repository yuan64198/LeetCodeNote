/**
 * We can apply the idea behind the problem "Largest Rectangle in Histogram" here.
 * In the above mentioned problem, it use a stack to calculate the area of rectangles.
 * How?
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
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] count = new int[m][n];
        
        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(matrix[i][j] == '1') count[i][j] = 1;
                if(j > 0 && matrix[i][j] == '1')  count[i][j] = count[i][j-1] + 1;
            }
        }
        int result = 0;
        for(int column = 0; column < n; ++column) {
            result = Math.max(result, maxHistogramArea(count, column));
        }
        return result;
    }
    
    private int maxHistogramArea(int[][] count, int column) {
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        int result = 0;
        for(int i=0; i<count.length; ++i) {
            while(stack.peek() != -1 && count[stack.peek()][column] > count[i][column]) {
                //System.out.println(count[0][0]);
                int area = count[stack.pop()][column]*(i-stack.peek()-1);
                result = Math.max(result, area);
            }
            stack.push(i);
        }
        
        while(stack.peek() != -1) {
            int area = count[stack.pop()][column]*(count.length-stack.peek()-1);
            result = Math.max(result, area);
        }
        
        return result;
    }
}
