package boj_bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11382_꼬마정민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long sum = Long.parseLong(st.nextToken());
        sum += Long.parseLong(st.nextToken());
        sum += Long.parseLong(st.nextToken());

        System.out.println(sum);
    }
}
