/*
    Boj 1083. 소트
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Boj_1083_소트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        int S = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            int max = arr.get(i);
            int maxIdx = i;

            for(int j = i; j <= i+S; j++){
                if(j < arr.size() && max < arr.get(j)){
                    max = arr.get(j);
                    maxIdx = j;
                }
            }

            arr.remove(Integer.valueOf(max));
            arr.add(i, max);
            S -= maxIdx - i;
        }

        for(int cur : arr){
            System.out.print(cur + " ");
        }
    }
}
