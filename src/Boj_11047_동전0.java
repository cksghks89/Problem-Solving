/*
    Boj 11047. 동전 0
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11047_동전0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];

        for(int i = 0; i < N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        for(int i = coins.length - 1; i >= 0; i--){
            cnt += K / coins[i];
            K %= coins[i];
        }

        System.out.println(cnt);
    }
}
