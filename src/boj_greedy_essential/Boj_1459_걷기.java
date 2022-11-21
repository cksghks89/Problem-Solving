/*
    Boj 1459. 걷기
    level. silver 4
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1459_걷기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int min = Math.min(X, Y);

        long time = 0;
        if (2 * W <= S) {
            time = 2L * W * min;
        } else {
            time = (long)S * min;
        }

        int dest = Math.max(X, Y) - min;
        if (W <= S) {
            time += (long)W * dest;
        } else {
            time += (dest / 2L) * 2 * S;
            if(dest % 2 == 1){
                time += W;
            }
        }

        System.out.println(time);
    }
}
