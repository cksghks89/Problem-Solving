/*
    Boj 9375. 패션왕 신해빈
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_9375_패션왕신해빈 {
    static Map<String, Integer> cloths;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test = 0; test < T; test++) {
            cloths = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();

                String kind = st.nextToken();
                cloths.put(kind, cloths.getOrDefault(kind, 0) + 1);
            }

            int answer = 1;
            for (Map.Entry<String, Integer> entry : cloths.entrySet()) {
                answer *= (entry.getValue() + 1);
            }
            sb.append(answer - 1).append("\n");
        }
        System.out.print(sb.toString());
    }
}
