import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[][] map;
    private static int[][] A;

    private static PriorityQueue<int[]> trees;
    private static Queue<int[]> deadTrees;

    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        // 초기화 --------- start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        trees = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        deadTrees = new ArrayDeque<>();

        map = new int[N + 1][N + 1];
        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = 5;
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            trees.offer(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
        // 초기화 --------- end


        // K 년 동안 반복
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(trees.size());
    }

    private static void spring() {
        // 나이가 어린 나무 순으로 정렬 : 나이 어린 나무부터 양분 섭취
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        while (!trees.isEmpty()) {
            int[] cur = trees.poll();

            if (map[cur[0]][cur[1]] >= cur[2]) {
                map[cur[0]][cur[1]] -= cur[2];
                cur[2] += 1;
                pq.offer(cur);
            } else {
                deadTrees.offer(cur);
            }
        }
        trees = pq;
    }

    private static void summer() {
        // 죽은 나무는 양분으로
        while (!deadTrees.isEmpty()) {
            int[] cur = deadTrees.poll();
            map[cur[0]][cur[1]] += cur[2] / 2;
        }
    }

    private static void autumn() {
        // 나무 번식
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        while (!trees.isEmpty()) {
            int[] cur = trees.poll();

            if (cur[2] % 5 == 0) {
                for (int j = 0; j < 8; j++) {
                    int nx = cur[0] + dx[j];
                    int ny = cur[1] + dy[j];
                    if (isInRange(nx, ny)) {
                        pq.offer(new int[]{nx, ny, 1});
                    }
                }
            }
            pq.offer(cur);
        }
        trees = pq;
    }

    private static void winter() {
        // 양분 추가
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }
}
