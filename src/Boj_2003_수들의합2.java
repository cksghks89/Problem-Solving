/*
    Boj 2003. 수들의 합 2
    level. silver 4
    Solved By 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int p1 = 0;
        int p2 = 0;

        int sum = 0;
        int cnt = 0;
        while(true){
            if(sum > M){
                sum -= A[p1++];
            }else if(p2 == A.length){
                break;
            }else{
                sum += A[p2++];
            }

            if(sum == M){
                cnt++;
                if(p1 + 1 < A.length){
                    p1 += 1;
                    p2 = p1;
                    sum = 0;
                }else{
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
