/*
    Boj 11728. 배열 합치기
    level. silver 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11728_배열합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int p1 = 0;
        int p2 = 0;

        StringBuilder sb = new StringBuilder();

        while(true){
            if(p1 < N && p2 < M){
                if(A[p1] < B[p2]){
                    sb.append(A[p1++]).append(" ");
                }else if(A[p1] > B[p2]){
                    sb.append(B[p2++]).append(" ");
                }else{
                    sb.append(A[p1++]).append(" ");
                    sb.append(B[p2++]).append(" ");
                }
            }else if(p1 < N){
                sb.append(A[p1++]).append(" ");
            }else if(p2 < M){
                sb.append(B[p2++]).append(" ");
            }

            if(p1 == N && p2 == M){
                break;
            }
        }
        System.out.println(sb);
    }
}
