package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_2071_평균값구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++) {
            double avg = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .average().getAsDouble();
            sb.append("#"+tc+" ").append(Math.round(avg)).append("\n");
        }
        System.out.print(sb.toString());
    }
}
