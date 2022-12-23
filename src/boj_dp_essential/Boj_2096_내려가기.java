/*
    Boj 2096. 내려가기
    level. gold 5
    solved by 송찬환
 */
package boj_dp_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2096_내려가기 {
    static int N;
    static int[][] score;
    static int[][] maxDp;
    static int[][] minDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        score = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            score[i][0] = Integer.parseInt(st.nextToken());
            score[i][1] = Integer.parseInt(st.nextToken());
            score[i][2] = Integer.parseInt(st.nextToken());

            Arrays.fill(minDp[i], Integer.MAX_VALUE);
        }
        minDp[0] = maxDp[0] = score[0];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (isInRange(j + k)) {
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][j + k] + score[i][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][j + k] + score[i][j]);
                    }
                }
            }
        }

        int maxScore = Arrays.stream(maxDp[N-1]).max().getAsInt();
        int minScore = Arrays.stream(minDp[N-1]).min().getAsInt();

        System.out.println(maxScore + " " + minScore);

    }

    static boolean isInRange(int x) {
        return (0 <= x && x < 3);
    }
}
