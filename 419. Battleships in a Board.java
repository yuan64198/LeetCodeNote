/**
 * Go through the board, whenever we encounter a 'X', we check if it's connected to other 'X', if connected, we should only count them altogether as 1.
 * So, the approach here, we only count the most up left point, other parts of the ships are not counted.
 * Whenver we see a 'X', we check if it's connected to a left 'X' or a upper 'X'.
 */

class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int result = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(board[i][j] == 'X' && !isConnected(board, i, j)) {
                    result += 1;
                }
            }
        }
        return result;
    }
    
    int[] dirX = new int[] {0, -1};
    int[] dirY = new int[] {-1, 0};
    private boolean isConnected(char[][] board, int x, int y) {
        for(int i = 0; i < 2; ++i) {
            int nx = x + dirX[i];
            int ny = y + dirY[i];
            if(nx < 0 || ny < 0 ) continue;
            if(board[nx][ny] == 'X') return true;
        }
        return false;
    }
}
