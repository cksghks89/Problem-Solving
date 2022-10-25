/*
    Boj 2981. 검문
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Boj_2981_검문 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int gcd = Math.abs(arr[1] - arr[0]);

        for(int i = 2; i < N; i++){
            int dist = Math.abs(arr[i] - arr[i-1]);

            gcd = getGcd(Math.max(dist, gcd), Math.min(dist, gcd));
        }

        List<Integer> divList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= Math.sqrt(gcd); i++){
            if(gcd % i == 0){
                if(i * i == gcd){
                    divList.add(i);
                }else{
                    divList.add(i);
                    divList.add(gcd / i);
                }
            }
        }

        divList.sort(Comparator.naturalOrder());

        for(int i = 1; i < divList.size(); i++){
            sb.append(divList.get(i)).append(" ");
        }

        System.out.println(sb.toString());
    }

    static int getGcd(int a, int b){
        int r = a % b;
        if(r == 0){
            return b;
        }
        return getGcd(b, r);
    }
}
