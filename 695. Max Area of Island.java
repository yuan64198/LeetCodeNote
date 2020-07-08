/**
 * Use for-loop to go through the whole grid, whenever we encounter a 1, we do bfs to it.
 * In the meantime, we maintain a visited array to record the spots we've already visited.
 * the bfs function return the area of the current island.
 * After going through the whole grid, we can find the island with biggest area.
 */

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        int result = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(visited[i][j] == false && grid[i][j] == 1) {
                    result = Math.max(result, bfs(grid, i, j, visited));
                }
            }
        }
        return result;
    }
    
    int[] dirX = new int[]{0, 0, 1, -1};
    int[] dirY = new int[]{1, -1, 0, 0};
    
    private int bfs(int[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int result = 0;
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int i = 0; i < 4; ++i) {
                int next_x = curr[0] + dirX[i];
                int next_y = curr[1] + dirY[i];
                if(inBound(next_x, next_y, m, n) && grid[next_x][next_y] == 1 && visited[next_x][next_y] == false) {
                    queue.offer(new int[]{next_x, next_y});
                    visited[next_x][next_y] = true;
                }
            }
            result += 1;
        }
        
        return result;
    }
    
    private boolean inBound(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
