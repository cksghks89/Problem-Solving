import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R1 = Integer.parseInt(st.nextToken());
        int C1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int R2 = Integer.parseInt(st.nextToken());
        int C2 = Integer.parseInt(st.nextToken());

        int[] dx = {-3, -3, -2, -2, 2, 2, 3, 3};
        int[] dy = {-2, 2, -3, 3, -3, 3, -2, 2};

        List<int[]>[] list = new List[8];
        for (int i = 0; i < 8; i++) list[i] = new ArrayList<>();
        list[0].add(new int[]{-1, 0});
        list[0].add(new int[]{-2, -1});
        list[1].add(new int[]{-1, 0});
        list[1].add(new int[]{-2, 1});
        list[2].add(new int[]{0, -1});
        list[2].add(new int[]{-1, -2});
        list[3].add(new int[]{0, 1});
        list[3].add(new int[]{-1, 2});
        list[4].add(new int[]{0, -1});
        list[4].add(new int[]{1, -2});
        list[5].add(new int[]{0, 1});
        list[5].add(new int[]{1, 2});
        list[6].add(new int[]{1, 0});
        list[6].add(new int[]{2, -1});
        list[7].add(new int[]{1, 0});
        list[7].add(new int[]{2, 1});

        boolean[][] visited = new boolean[10][9];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{R1, C1, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[0] == R2 && cur[1] == C2) {
                System.out.println(cur[2]);
                return;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny]) {
                    int[] mid1 = list[i].get(0);
                    int[] mid2 = list[i].get(1);
                    if ((cur[0] + mid1[0] == R2 && cur[1] + mid1[1] == C2) || (cur[0] + mid2[0] == R2 && cur[1] + mid2[1] == C2))
                        continue;

                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < 10 && 0 <= y && y < 9;
    }
}
