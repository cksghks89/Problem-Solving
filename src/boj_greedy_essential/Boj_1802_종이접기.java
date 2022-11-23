/*
    Boj 1802. 종이접기
    level. silver 1
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1802_종이접기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String paper = br.readLine();

            if (isFold(paper)) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static boolean isFold(String paper) {
        return divide(0, paper.length()-1, paper);
    }

    static boolean divide(int s, int e, String paper) {
        if (s == e) {
            return true;
        }

        for (int i = 0; i < (e - s) / 2; i++) {
            if (paper.charAt(s + i) == paper.charAt(e - i)) {
                return false;
            }
        }

        boolean result1 =  divide(s, (e + s) / 2 - 1, paper);
        boolean result2 = divide((e + s) / 2 + 1, e, paper);

        return result1 && result2;
    }
}
