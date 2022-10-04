/*
    Boj 2110. 공유기 설치
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj_2110_공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] N_C = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] xArr = new int[N_C[0]];

        for(int i = 0; i < xArr.length; i++){
            xArr[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(xArr);

        int left = Integer.MAX_VALUE;
        int right = xArr[xArr.length-1] - xArr[0];

        for(int i = 1; i < xArr.length; i++){
            left = Math.min(left, xArr[i] - xArr[i-1]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        while(left <= right){
            int mid = (left + right) / 2;
            int cur = xArr[0];
            int cnt = 1;
            boolean check = false;

            for(int i = 1; i < xArr.length; i++){
                if(xArr[i] - cur >= mid){
                    if(mid == xArr[i] - cur){
                        check = true;
                    }
                    cnt += 1;
                    cur = xArr[i];
                }
            }

            if(cnt >= N_C[1]){
                if(check){
                    list.add(mid);
                }
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(list.stream().mapToInt(Integer::valueOf).max().getAsInt());
    }
}
