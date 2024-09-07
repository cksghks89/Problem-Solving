import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Map<Integer, Integer> colors;
    private static int Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        colors = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            colors.put(cur, colors.getOrDefault(cur, 0) + 1);
        }

        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            boolean check = true;
            for (int j = 1; j < arr.length; j++) {
                if (colors.getOrDefault(arr[j], 0) == 0) {
                    for (int k = 1; k < j; k++) {
                        colors.put(arr[k], colors.get(arr[k]) + 1);
                    }
                    check = false;
                    break;
                }
                colors.put(arr[j], colors.get(arr[j]) - 1);
            }


            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (!check) continue;

            for (int j = 1; j < arr.length; j++) {
                colors.put(arr[j], colors.getOrDefault(arr[j], 0) + 1);
            }
        }


        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Integer key : colors.keySet()) {
            int curCnt = colors.get(key);
            count += curCnt;

            for (int i = 0; i < curCnt; i++) {
                sb.append(key).append(' ');
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }
}
