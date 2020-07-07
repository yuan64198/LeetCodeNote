class Solution {
    int[] dirX = new int[]{0, 0, 1, -1};
    int[] dirY = new int[]{1, -1, 0, 0};
    
    public int islandPerimeter(int[][] grid) {
        int result = 0, connected = 0;
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {
                if(grid[i][j] == 1) {
                    result += 4;
                    connected += surroundedIslands(grid, i, j);
                    //System.out.println(connected);
                }
            }
        }
        return result - connected;
    }
    
    private int surroundedIslands(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        int result = 0;
        
        for(int i = 0; i < 4; ++i) {
            int new_x = x + dirX[i];
            int new_y = y + dirY[i];
            if(new_x >=0 && new_x < m && new_y >= 0 && new_y < n && grid[new_x][new_y] == 1) {
                result += 1;
            }
        }
        return result;
    }
}
