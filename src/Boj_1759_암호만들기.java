/*
    Boj 1759. 암호 만들기
    level. gold 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1759_암호만들기 {
    static int L, C;
    static String[] inputs;
    static boolean[] selected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        selected = new boolean[C];

        inputs = br.readLine().split(" ");
        Arrays.sort(inputs);

        subSet(0, 0);
        System.out.print(sb.toString());
    }

    static void subSet(int cnt, int select) {
        if (select == L) {
            if (isPossible()) {
                for (int i = 0; i < C; i++) {
                    if (selected[i]) {
                        sb.append(inputs[i]);
                    }
                }
                sb.append("\n");
            }

            return;
        }
        if (cnt == C) {
            return;
        }

        selected[cnt] = true;
        subSet(cnt + 1, select + 1);
        selected[cnt] = false;
        subSet(cnt + 1, select);
    }

    static boolean isPossible() {
        Set<String> moList = new HashSet<>(List.of("a", "e", "i", "o", "u"));
        int mo = 0, ja = 0;

        for (int i = 0; i < C; i++) {
            if (selected[i]) {
                if (moList.contains(inputs[i])) {
                    mo++;
                } else {
                    ja++;
                }
            }
        }
        return mo >= 1 && ja >= 2;
    }
}
