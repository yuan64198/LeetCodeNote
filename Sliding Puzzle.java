/**
 * We use Graph and BFS to solve this problem.
 * First, we assume each state of the board is a node of the graph, and an edge between two state means one can change to another by one swap.
 * Typically, we use hashmap to store nodes and it's neighbor.
 * But here, it's hard to store a 2D array into hashmap as a key.
 * Hence, we transform the 2D-array into a String.
 * And, we can still do the swapping in the String by index mapping.
 * Second, we use a queue to do bfs.
 * Start from the given starting state, we do bfs to find the target state, use a variable step to record the distance.
 * If, we found it, return step.
 * If, after searching the whole graph from the starting state, we still cannot reach the target state.
 * It means the starting state cannot be transformed to the target state, then return -1.
 */

class Solution {
    
    int[] dirX = new int[]{0, 0, 1, -1};
    int[] dirY = new int[]{1, -1, 0, 0};
    
    public int slidingPuzzle(int[][] board) {
        int x = 0, y = 0;
        String solved = "123450";
        String start = "";
        for(int i = 0; i < 2; ++ i) {
            for(int j = 0; j < 3; ++j) {
                start += board[i][j];
            }
        }
        
        Set<String> visited = new HashSet();
        Queue<String> queue = new LinkedList();
        queue.offer(start);
        int[][] dir = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; ++i) {
                String curr = queue.poll();
                if(curr.equals(solved)) return step;
                int zero = curr.indexOf('0');
                for(int next_step : dir[zero]) {
                    String next = swap(curr, zero, next_step);
                    if(!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                        //System.out.println(next);
                    }
                }
            }
            ++step;
        }
        //1 2 3
        //4 5 0
        return -1;
    }
    
    private String swap(String curr, int a, int b) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < curr.length(); ++i) {
            if(i == a) sb.append(curr.charAt(b));
            else if(i == b) sb.append(curr.charAt(a));
            else sb.append(curr.charAt(i));
        }
        return sb.toString();
    }
}
