/*
    Boj 5904 Moo 게임
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj_5904_Moo게임 {
    static ArrayList<Integer> lenS = new ArrayList<>();
    static char result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        lenS.add(3);

        int idx = 1;
        while(true){
            int cur = lenS.get(idx-1) * 2 + idx + 3;
            lenS.add(cur);

            if(cur >= N){
                break;
            }

            idx++;
        }

        // divide and conquer
        divideConquer(1, lenS.get(idx), idx, N);
        System.out.println(String.valueOf(result));

    }

    public static void divideConquer(int p1, int p2, int k, int N){
        if(k == 0){
            if(N == p1){
                result = 'm';
            }else{
                result = 'o';
            }
            return;
        }

        int midM = p1 + lenS.get(k-1);

        if(midM <= N && N <= midM + k+2){
            if(N == midM){
                result = 'm';
            }else{
                result = 'o';
            }
            return;
        }

        if(N < midM){
            divideConquer(p1, midM-1, k-1, N);
        }else if(N > midM + k + 2){
            divideConquer(midM+k+3, p2, k-1, N);
        }
    }

}
