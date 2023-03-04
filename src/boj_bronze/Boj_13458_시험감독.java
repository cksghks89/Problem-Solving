/*
    Boj 13458. 시험 감독
    level. bronze 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_13458_시험감독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        A = Arrays.stream(A).map(x -> Math.max(x - B, 0)).toArray();
        long cnt = N;
        for (int i = 0; i < N; i++) cnt += Math.ceil(A[i] * 1.0 / C);

        System.out.println(cnt);
    }
}
