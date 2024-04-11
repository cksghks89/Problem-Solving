import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[][] map;
    private static int[][] likes;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        likes = new int[N * N + 1][5];

        for (int i = 0; i < N * N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            likes[input[0]] = input;
            insert(input);
        }

        System.out.println(getSatisfaction());
    }

    private static int getSatisfaction() {
        int[] points = {0, 1, 10, 100, 1000};
        int satisfaction = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int likeCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isInRange(nx, ny) && isLike(map[nx][ny], likes[map[i][j]])) {
                        likeCnt += 1;
                    }
                }

                satisfaction += points[likeCnt];
            }
        }
        return satisfaction;
    }

    private static void insert(int[] input) {
        int fitX = 0;
        int fitY = 0;
        int emptyCnt = -1;
        int likeCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) continue;
                int curEmptyCnt = 0;
                int curLikeCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (isInRange(nx, ny)) {
                        if (map[nx][ny] == 0) curEmptyCnt += 1;
                        else if (isLike(map[nx][ny], input)) curLikeCnt += 1;
                    }
                }

                if ((curLikeCnt > likeCnt) || (curLikeCnt == likeCnt && curEmptyCnt > emptyCnt)) {
                    fitX = i;
                    fitY = j;
                    likeCnt = curLikeCnt;
                    emptyCnt = curEmptyCnt;
                }
            }
        }

        map[fitX][fitY] = input[0];
    }

    private static boolean isLike(int studentId, int[] likeStudents) {
        for (int i = 1; i < 5; i++) {
            if (studentId == likeStudents[i]) return true;
        }
        return false;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
