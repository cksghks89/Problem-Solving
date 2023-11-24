import java.util.*;

class Solution {
    private int[][] map;
    private boolean[][] visited;
    private int n, m;
    private Map<Integer, Integer> amount;
    private int[][] amountMap;
    
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] land) {
        int answer = 0;
        
        map = land;
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        amount = new HashMap<>();
        amountMap = new int[n][m];
        
        
        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    amount.put(idx, bfs(i, j, idx++));
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            Set<Integer> oilSet = new HashSet<>();
            int oilAmount = 0;
            
            for (int j = 0; j < n; j++) {
                if (amountMap[j][i] == 0) continue;
                oilSet.add(amountMap[j][i]);
            }
            
            for (int curOil : oilSet) {
                oilAmount += amount.get(curOil);
            }
            
            answer = Math.max(answer, oilAmount);
        }
        
        return answer;
    }
    
    private int bfs(int x, int y, int idx) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        
        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            count += 1;
            amountMap[cur[0]][cur[1]] = idx;
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (isInRange(nx ,ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return count;
    }
    
    private boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    
    private void debugPrintMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}