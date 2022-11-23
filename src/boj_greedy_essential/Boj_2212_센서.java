/*
    Boj 2212. 센서
    level. gold 5
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Boj_2212_센서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        List<Integer> list = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).sorted().collect(Collectors.toList());

        List<Integer> distance = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            distance.add(list.get(i) - list.get(i - 1));
        }
        Collections.sort(distance);

        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += distance.get(i);
        }

        System.out.println(sum);
    }
}
