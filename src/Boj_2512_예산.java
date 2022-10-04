/*
    Boj 2512. 예산
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj_2512_예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] req = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine().trim());

        if(Arrays.stream(req).sum() <= M){
            System.out.println(Arrays.stream(req).max().getAsInt());
            return;
        }

        int left = 1;
        int right = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for(int item: req){
            right = Math.max(right, item);
        }

        while(left <= right){
            int mid = (left + right) / 2;
            int cur = 0;

            for(int item: req){
                cur += Math.min(mid, item);
            }

            if(cur <= M){
                list.add(mid);
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(list.stream().mapToInt(Integer::valueOf).max().getAsInt());
    }
}
