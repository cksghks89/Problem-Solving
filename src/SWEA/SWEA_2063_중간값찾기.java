package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_2063_중간값찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int ans = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray()[N / 2];

        System.out.println(ans);
    }
}
