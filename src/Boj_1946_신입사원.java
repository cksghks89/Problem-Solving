/*
    Boj 1946. 신입사원
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1946_신입사원 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++){
            int N = Integer.parseInt(br.readLine());
            int[][] score = new int[N][2];

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                score[i][0] = Integer.parseInt(st.nextToken());
                score[i][1] = Integer.parseInt(st.nextToken());
            }

            if(score.length == 1){
                System.out.println("1");
                continue;
            }

            Arrays.sort(score, (o1, o2) -> o1[0] - o2[0]);

            int min = score[0][1];

            int cnt = 1;
            for(int i = 1; i < score.length; i++){
                min = Math.min(score[i][1], min);

                if(min == score[i][1]){
                    cnt += 1;
                }
            }
            System.out.println(cnt);
        }
    }
}
