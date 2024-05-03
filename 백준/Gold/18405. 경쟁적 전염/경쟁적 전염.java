import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K, X, Y, S;
    private static int[][] fields;

    private static List<int[]>[] viruses;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fields = new int[N][N];

        viruses = new List[K + 1];
        for (int i = 1; i <= K; i++) viruses[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
                if (fields[i][j] != 0) {
                    viruses[fields[i][j]].add(new int[]{i, j});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        int time = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 1; i <= K; i++) {
            if (!viruses[i].isEmpty()) {
                int count = spread(i);
                if (count == -1) continue;
                if (count < time) {
                    answer = i;
                    time = count;
                }
            }
        }
        System.out.println(answer);
    }

    private static int spread(int id) {
        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < viruses[id].size(); i++) {
            queue.offer(new int[]{viruses[id].get(i)[0], viruses[id].get(i)[1], 0});
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[0] == X && cur[1] == Y) {
                if (cur[2] > S) return -1;
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (isInRange(nx, ny) && fields[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
