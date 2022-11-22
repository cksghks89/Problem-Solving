/*
    Boj 1911. 흙길 보수하기
    level. silver 1
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1911_흙길보수하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{s, e});
        }

        int idx = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (idx >= cur[1]) {
                continue;
            } else if (idx < cur[0]) {
                int cnt = (cur[1] - cur[0] + L - 1) / L;
                count += cnt;
                idx = cur[0] + cnt * L - 1;
            } else {
                int cnt = (cur[1] - (idx + 1) + L - 1) / L;
                count += cnt;
                idx = idx + cnt * L;
            }
        }
        System.out.println(count);
    }
}
