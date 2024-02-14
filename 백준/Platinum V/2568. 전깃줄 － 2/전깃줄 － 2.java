import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] arr;
    private static int[] tracking;
    private static List<Integer> lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        lis = new ArrayList<>();
        tracking = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < N; i++) {
            int[] cur = arr[i];

            int idx = Collections.binarySearch(lis, cur[1]);

            idx = -idx - 1;
            if (idx == lis.size()) {
                lis.add(cur[1]);
                tracking[i] = lis.size();
            } else {
                lis.set(idx, cur[1]);
                tracking[i] = idx + 1;
            }
        }

        int cnt = lis.size();
        Set<Integer> set = new HashSet<>();
        for (int i = N - 1; i >= 0; i--) {
            if (tracking[i] == cnt) {
                set.add(arr[i][0]);
                cnt--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N - lis.size()).append('\n');
        for (int i = 0; i < N; i++) {
            if (!set.contains(arr[i][0])) sb.append(arr[i][0]).append('\n');
        }

        System.out.println(sb);
    }
}
