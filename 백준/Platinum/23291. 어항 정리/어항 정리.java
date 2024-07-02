import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[][] fishTank;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fishTank = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fishTank[N - 1][i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        while (true) {
            int diffCount = getDiffCount();
            if (diffCount <= K) break;

            count += 1;
            cleanFishTank();
        }

        System.out.println(count);
    }

    private static int getDiffCount() {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, fishTank[N - 1][i]);
            max = Math.max(max, fishTank[N - 1][i]);
        }
        return max - min;
    }

    private static void cleanFishTank() {
        // 1. 물고기 넣기
        addFish();

        // 2. 첫번쨰 공중부양 (롤케익)
        firstLevitation(0, 0, 1, N);

        // 3. 물고기 조절
        adjustFish();

        // 4. 바닥에 일렬로 놓기
        putFloor();

        // 5. 두번째 공중부양
        secondLevitation();

        // 6. 물고기 조절
        adjustFish();

        // 7. 바닥에 일렬로 놓기
        putFloor();

    }

    private static void addFish() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, fishTank[N - 1][i]);
        }

        for (int i = 0; i < N; i++) {
            if (fishTank[N - 1][i] == min) {
                fishTank[N - 1][i] += 1;
            }
        }
    }

    private static void firstLevitation(int s, int e, int h, int width) {
        if (h > width - (e - s + 1)) {
            return;
        }

        // 1. 회전
        int[][] rotateFishTank = new int[e - s + 1][h];
        for (int i = 0; i < e - s + 1; i++) {
            for (int j = 0; j < h; j++) {
                rotateFishTank[i][j] = fishTank[N - 1 - j][s + i];
                fishTank[N - 1 - j][s + i] = 0;
            }
        }

        // 3. 쌓기
        for (int i = 0; i < e - s + 1; i++) {
            for (int j = 0; j < h; j++) {
                fishTank[N - 1 - (e - s + 1) + i][e + 1 + j] = rotateFishTank[i][j];
            }
        }

        firstLevitation(e + 1, e + h, e - s + 2, width - (e - s + 1));
    }

    private static void adjustFish() {
        int[][] newFishTank = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newFishTank[i][j] = fishTank[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (fishTank[i][j] == 0) continue;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (!isInRange(nx, ny) || fishTank[nx][ny] == 0) continue;
                    if (fishTank[i][j] < fishTank[nx][ny]) continue;
                    if (Math.abs(fishTank[i][j] - fishTank[nx][ny]) < 5) continue;

                    int d = Math.abs(fishTank[i][j] - fishTank[nx][ny]) / 5;
                    newFishTank[i][j] -= d;
                    newFishTank[nx][ny] += d;
                }
            }
        }
        fishTank = newFishTank;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void putFloor() {
        int[][] newFishTank = new int[N][N];

        int idx = 0;

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (fishTank[j][i] == 0) continue;
                newFishTank[N - 1][idx++] = fishTank[j][i];
            }
        }
        fishTank = newFishTank;
    }

    private static void secondLevitation() {
        // 2단
        int[] twoOfRotate = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            twoOfRotate[i] = fishTank[N - 1][N / 2 - 1 - i];
            fishTank[N - 1][N / 2 - 1 - i] = 0;
        }
        for (int i = 0; i < N / 2; i++) {
            fishTank[N - 2][N / 2 + i] = twoOfRotate[i];
        }

        // 4단
        int[][] temp = new int[2][N / 4];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N / 4; j++) {
                temp[i][j] = fishTank[N - 2 + i][N / 2 + j];
                fishTank[N - 2 + i][N / 2 + j] = 0;
            }
        }
        int[][] rotate = new int[2][N / 4];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N / 4; j++) {
                rotate[i][j] = temp[1 - i][N / 4 - 1 - j];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N / 4; j++) {
                fishTank[N - 4 + i][N / 2 + N / 4 + j] = rotate[i][j];
            }
        }
    }
}
