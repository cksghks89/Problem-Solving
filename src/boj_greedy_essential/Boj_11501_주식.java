/*
    Boj 11501. 주식
    level. silver 2
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_11501_주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 0; testCase < T; testCase++) {
            ArrayList<Integer> list = new ArrayList<>();

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            sb.append(getMaxProfit(N, list)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static long getMaxProfit(int N, ArrayList<Integer> list) {
        long profit = 0;

        int cur = list.get(N - 1);
        for (int i = list.size() - 2; i >= 0; i--) {
            if(cur > list.get(i)){
                profit += (long)cur - list.get(i);
            }else if(cur < list.get(i)){
                cur = list.get(i);
            }
        }
        return profit;
    }
}
