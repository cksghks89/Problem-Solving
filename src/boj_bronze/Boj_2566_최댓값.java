package boj_bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2566_최댓값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[10][10];

        int max = 0;
        int x = 0, y = 0;
        for (int i = 1; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (max <= map[i][j]) {
                    x = i;
                    y = j;
                    max = map[i][j];
                }
            }
        }
        System.out.println(max);
        System.out.println(x + " " + y);
    }
}
