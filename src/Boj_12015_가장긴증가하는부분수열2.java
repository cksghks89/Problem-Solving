/*
    Boj 12015. 가장 긴 증가하는 부분수열 2
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_12015_가장긴증가하는부분수열2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] A = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] ans = new int[N];
        ans[0] = A[0];
        int idx = 0;

        for(int i = 1; i < N; i++){
            if(ans[idx] < A[i]){
                ans[++idx] = A[i];
            }else if(ans[idx] > A[i]){
                int cur = Arrays.binarySearch(ans,0, idx, A[i]);
                if(cur < 0){
                    ans[-cur - 1] = A[i];
                }
            }
        }
        int cnt = 0;
        for(int i : ans){
            if(i == 0){
                break;
            }
            cnt += 1;
        }
        System.out.println(cnt);
    }
}
