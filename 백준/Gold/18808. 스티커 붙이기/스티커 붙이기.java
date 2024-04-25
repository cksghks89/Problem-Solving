import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static int[][] grid;
    private static int[][][] stickers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        stickers = new int[K][][];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            stickers[i] = new int[R][C];
            for (int j = 0; j < R; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < C; k++) {
                    stickers[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }

            attach(stickers[i]);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                count += grid[i][j];
            }
        }

        System.out.println(count);
    }

    private static void attach(int[][] sticker) {
        // 1. 스티커 붙일 공간 탐색
        // 1-1. 공간이 없으면 90도 회전 (최대 4번) -> 90도 회전 시 R, C 크기가 뒤바뀔 수 있음
        // 2. 스티커 붙이기

        for (int i = 0; i < 4; i++) {
            int R = sticker.length;
            int C = sticker[0].length;

            for (int j = 0; j <= N - R; j++) {
                for (int k = 0; k <= M - C; k++) {
                    if (!isAttachPossible(sticker, j, k)) continue;
                    post(sticker, j, k);
                    return;
                }
            }

            sticker = rotate(sticker);
        }
    }

    // sticker 를 붙일 수 있는지 확인
    private static boolean isAttachPossible(int[][] sticker, int x, int y) {
        int R = sticker.length;
        int C = sticker[0].length;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[x + i][y + j] == 1 && sticker[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    // sticker 90도 회전
    private static int[][] rotate(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] newSticker = new int[C][R];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newSticker[j][R - 1 - i] = sticker[i][j];
            }
        }

        return newSticker;
    }

    // sticker 붙이기
    private static void post(int[][] sticker, int x, int y) {
        int R = sticker.length;
        int C = sticker[0].length;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                grid[x + i][y + j] |= sticker[i][j];
            }
        }
    }
}
