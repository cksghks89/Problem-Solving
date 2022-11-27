/*
    Boj 10971. 외판원 순회2
    level. silver 2
    solved by 송찬환
 */
package boj_bfs_dfs_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10971_외판원순회2 {
    static int[][] matrix;
    static boolean[] visited;
    static int N;

    static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0] = true;
        dfs(0, 0, 0);

        System.out.println(minCost);
    }

    static void dfs(int x, int cost, int depth) {
        if (depth == N - 1) {
            if (matrix[x][0] > 0) {
                minCost = Math.min(cost + matrix[x][0], minCost);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (matrix[x][i] > 0 && !visited[i]) {
                visited[x] = true;
                dfs(i, cost + matrix[x][i], depth + 1);
                visited[x] = false;
            }
        }
    }
}
