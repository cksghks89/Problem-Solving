/*
    Boj 1244. 스위치 켜고 끄기
    level. silver 4
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1244_스위치켜고끄기 {
    static int N;
    static int[] swit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        swit = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            swit[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                man(idx);
            } else if (gender == 2) {
                woman(idx);
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(swit[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }

    static void man(int idx) {
        for (int i = idx; i <= N; i += idx) {
            swit[i] = swit[i] == 0 ? 1 : 0;
        }
    }

    static void woman(int idx) {
        swit[idx] = swit[idx] == 0 ? 1 : 0;

        int d = 1;
        while (true) {
            if (isInRange(idx + d) && isInRange(idx - d) && swit[idx + d] == swit[idx - d]) {
                swit[idx + d] = swit[idx + d] == 0 ? 1 : 0;
                swit[idx - d] = swit[idx - d] == 0 ? 1 : 0;
                d += 1;
            } else {
                break;
            }
        }
    }

    static boolean isInRange(int x) {
        return 1 <= x && x <= N;
    }
}
