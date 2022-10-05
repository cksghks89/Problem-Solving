/*
    Boj 1101. 카드 정리 1
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1101_카드정리1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] card = new int[N][M];

        for(int i = 0; i < N; i++){
            card[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int rtn = Integer.MAX_VALUE;

        for(int joker = 0; joker < N; joker++){
            int[][] valCard = new int[N][M];
            int cnt = 0;

            for(int i = 0; i < N; i++){
                if(i == joker){
                    plus(card, valCard, i, i);
                }else{
                    int kind = kinds(card, i);
                    if(kind > 1){
                        cnt++;
                        plus(card, valCard, i, joker);
                    }else{
                        plus(card, valCard, i, i);
                    }
                }
            }

            int[] kindM = new int[M];
            for(int i = 0; i < N; i++){
                if(i != joker){
                    for(int j = 0; j < M; j++){
                        if(valCard[i][j] > 0){
                            kindM[j] += 1;
                        }
                    }
                }
            }
            for(int cur : kindM){
                if(cur - 1 >= 1){
                    cnt += cur - 1;
                }
            }
            rtn = Math.min(cnt, rtn);
        }

        System.out.println(rtn);
    }
    public static void plus(int[][] card, int[][] valCard, int a, int b){
        for(int i = 0; i < card[0].length; i++){
            valCard[b][i] += card[a][i];
        }
    }
    public static int kinds(int[][] card, int a){
        int cnt = 0;
        for(int i = 0; i < card[0].length; i++){
            if(card[a][i] > 0){
                cnt += 1;
            }
        }
        return cnt;
    }
}