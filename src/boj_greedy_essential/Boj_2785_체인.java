/*
    Boj 2785. 체인
    level. silver 2
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2785_체인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Long> list = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(list);

        int count = 0;
        while (true) {
            if (list.size() <= 1) {
                break;
            }
            list.set(0, list.get(0) - 1);
            list.remove(list.size() - 1);
            if(list.get(0) == 0){
                list.remove(0);
            }
            count += 1;
        }
        System.out.println(count);
    }
}
