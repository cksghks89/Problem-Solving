import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            List<Integer> lis = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int cur = Integer.parseInt(st.nextToken());
                int idx = Collections.binarySearch(lis, cur);
                if (idx < 0 && -idx - 1 == lis.size()) {
                    lis.add(cur);
                    continue;
                }
                lis.set(idx < 0 ? -idx - 1 : idx, cur);
            }
            sb.append('#').append(tc).append(' ').append(lis.size()).append('\n');
        }
        System.out.print(sb.toString());
    }
}
