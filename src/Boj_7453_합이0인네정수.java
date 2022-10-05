/*
    Boj 7453. 합이 0인 네 정수
    level. gold 2
    solved by 송찬환

    투 포인터 알고리즘으로 문제를 풀었으나 이분탐색으로 문제를 푸는 방법도 있음.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_7453_합이0인네정수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[n*n];
        int[] CD = new int[n*n];

        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);

        long cnt = 0;

        int p1 = 0;
        int p2 = CD.length - 1;
        long sum = 0;

        while(p1 < n*n && p2 >= 0){
            sum = AB[p1] + CD[p2];
            if(sum < 0){
                p1++;
            }else if(sum > 0){
                p2--;
            }else{
                long ABcnt = 0;
                long CDcnt = 0;
                int ab = AB[p1];
                int cd = CD[p2];
                while(p1 < n * n && AB[p1] == ab){
                    p1++;
                    ABcnt++;
                }
                while(p2 >= 0 && CD[p2] == cd){
                    p2--;
                    CDcnt++;
                }
                cnt += ABcnt * CDcnt;
            }
        }

        System.out.println(cnt);
    }
}
