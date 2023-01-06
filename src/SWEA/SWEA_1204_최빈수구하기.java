package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1204_최빈수구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            br.readLine();

            int[] score = Arrays.stream(br.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int mode = getMode(score);

            sb.append("#" + tc + " ").append(mode).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int getMode(int[] score) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < score.length; i++) {
            map.put(score[i], map.getOrDefault(score[i], 0) + 1);
        }

        ArrayList<Map.Entry<Integer, Integer>> li = new ArrayList<>(map.entrySet());
        li.sort((x, y) -> {
            if (!Objects.equals(x.getValue(), y.getValue())) {
                return y.getValue() - x.getValue();
            } else {
                return y.getKey() - x.getKey();
            }
        });

        return li.get(0).getKey();
    }
}
