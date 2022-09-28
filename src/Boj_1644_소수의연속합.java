/*
    Boj 1644. 소수의 연속 합
    level. gold 3
    Solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj_1644_소수의연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        boolean[] primeSet = new boolean[N + 1];
        Arrays.fill(primeSet, true);

        for(int i = 2; i <= N; i++){
            if(primeSet[i]){
                for(int j = i + i; j <= N; j += i){
                    primeSet[j] = false;
                }
            }
        }

        ArrayList<Integer> prime = new ArrayList<>();

        for(int i = 2; i <= N; i++){
            if(primeSet[i]){
                prime.add(i);
            }
        }

        int p1 = 0;
        int p2 = 0;

        int sum = 0;
        int cnt = 0;
        while(true){
            if(sum > N){
                sum -= prime.get(p1++);
            }else if(p2 == prime.size()){
                break;
            }else{
                sum += prime.get(p2++);
            }

            if(sum == N){
                cnt++;
                sum -= prime.get(p1++);
            }
        }
        System.out.println(cnt);

    }
}
