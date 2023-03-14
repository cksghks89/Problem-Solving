/*
    Boj 12738. 가장 긴 증가하는 부분수열 3
    level. gold 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_12738_가장긴증가하는부분수열3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            int idx = Collections.binarySearch(arr, cur);

            if (idx < 0 && -idx - 1 == arr.size()) {
                arr.add(cur);
            } else if (idx < 0) {
                arr.set(-idx - 1, cur);
            }
        }

        int cnt = 0;
        for (int n : arr) {
            if (n == 0) break;
            cnt++;
        }
        System.out.println(cnt);
    }
}
