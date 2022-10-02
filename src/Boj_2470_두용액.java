/*
    Boj 2470. 두 용액
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        int[] arr = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int p1 = 0;
        int p2 = arr.length - 1;

        int sum = 0;
        int[] rtn = {arr[p1], arr[p2]};

        while(p1 < p2){
            sum = arr[p1] + arr[p2];

            if(Math.abs(sum) < Math.abs(rtn[0] + rtn[1])){
                rtn[0] = arr[p1];
                rtn[1] = arr[p2];
            }

            if (sum < 0) {
                p1++;
            }else if(sum > 0){
                p2--;
            }else{
                break;
            }
        }
        System.out.println(rtn[0] + " " + rtn[1]);
    }
}
