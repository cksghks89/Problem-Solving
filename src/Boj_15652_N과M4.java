/*
    Boj 15652. N과 M (4)
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_15652_N과M4 {
    static int[] answer;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        answer = new int[M];
        sb = new StringBuilder();

        combinationWithRepetition(0, N, M, 0);

        System.out.print(sb.toString());
    }
    static void combinationWithRepetition(int depth, int N, int M, int start){
        if(depth == M){
            for(int i = 0; i < M; i++){
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1 + start; i <= N; i++){
            answer[depth] = i;
            combinationWithRepetition(depth + 1, N, M, start);
            start++;
        }
    }
}
