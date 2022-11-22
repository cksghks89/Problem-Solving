/*
    Boj 12018. Yonsei TOTO
    level. silver 3
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_12018_YonseiTOTO {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] needMileage = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            needMileage[i] = calcMinMileage(p, l, arr);
        }

        int count = 0;
        Arrays.sort(needMileage);
        for(int cost : needMileage){
            if(m < cost){
                break;
            }

            m -= cost;
            count += 1;
        }

        System.out.println(count);
    }

    static int calcMinMileage(int p, int l, int[] arr) {
        Arrays.sort(arr);
        if(p < l){
            return 1;
        }
        return arr[p - l];
    }
}
