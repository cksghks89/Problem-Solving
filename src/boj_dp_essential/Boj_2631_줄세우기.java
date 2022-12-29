/*
    Boj 2631. 줄 세우기
    level. gold 4
    solved by 송찬환
 */
package boj_dp_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Boj_2631_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> dp = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int idx = Collections.binarySearch(dp, arr[i]);

            if (idx < 0) {
                if (dp.size() == -idx - 1) {
                    dp.add(-idx - 1, arr[i]);
                } else {
                    dp.set(-idx - 1, arr[i]);
                }
            } else {
                dp.set(idx, arr[i]);
            }
        }

        System.out.println(arr.length - dp.size());
    }
}
