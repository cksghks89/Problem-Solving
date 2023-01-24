package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트 {
    static int N, L;
    static int[][] ingredients;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            ingredients = new int[N][];
            result = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }

            getMaxScore(0, 0, 0);
            sb.append("#" + tc + " ").append(result).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void getMaxScore(int idx, int score, int cal) {
        if(cal > L){
            return;
        }
        if(idx == N){
            result = Math.max(result, score);
            return;
        }

        getMaxScore(idx + 1, score, cal);
        getMaxScore(idx + 1, score + ingredients[idx][0], cal + ingredients[idx][1]);
    }
}
