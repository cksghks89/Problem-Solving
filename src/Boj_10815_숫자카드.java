/*
    Boj 10815. 숫자카드
    level. silver 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_10815_숫자카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] nArr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine().trim());
        int[] mArr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(nArr);
        StringBuilder sb = new StringBuilder();
        for(int m = 0; m < M; m++){
            int left = 0;
            int right = N-1;
            boolean findFlag = false;

            while(left <= right){
                int mid = (left + right) / 2;

                if(mArr[m] == nArr[mid]){
                    findFlag = true;
                    break;
                }else if(mArr[m] < nArr[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }

            if(findFlag){
                sb.append("1 ");
            }else{
                sb.append("0 ");
            }
        }

        System.out.println(sb);
    }
}
