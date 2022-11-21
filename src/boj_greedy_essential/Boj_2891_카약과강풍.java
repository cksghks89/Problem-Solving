/*
    Boj 2891. 카약과 강풍
    level. silver 5
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2891_카약과강풍 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        boolean[] s = new boolean[N + 1];
        boolean[] r = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int idx = Integer.parseInt(st.nextToken());
            s[idx] = true;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int idx = Integer.parseInt(st.nextToken());
            r[idx] = true;
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (!s[i]) {
                continue;
            }

            if (r[i] || r[i - 1]) {
                r[i] = false;
                s[i] = false;
                continue;
            }
            if (i + 1 <= N && r[i + 1] && !s[i + 1]) {
                s[i] = false;
                r[i + 1] = false;
                continue;
            }

            count += 1;
        }

        System.out.println(count);
    }
}
