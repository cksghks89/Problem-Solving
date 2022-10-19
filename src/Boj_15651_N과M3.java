/*
    Boj 15651. N과 M (3)
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_15651_N과M3 {
    static StringBuilder sb;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();
        arr = new int[M];

        permutationWithRepetition(0, N, M);

        System.out.print(sb.toString());
    }

    public static void permutationWithRepetition(int depth, int N, int M){
        if(depth == M){
            for(int i = 0; i < M; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++){
            arr[depth] = i;
            permutationWithRepetition(depth + 1, N, M);
        }
    }
}
