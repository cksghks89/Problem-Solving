import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for (int i = 0; i < N; i++) {
            parent[i + 1] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        int max = 0;
        for (int i = 1; i <= N; i++) {
            int count = getCount(i, new boolean[N + 1]);
            if (max < count) {
                answer = i;
                max = count;
            }
        }

        System.out.println(answer);
    }

    private static int getCount(int num, boolean[] visited) {
        if (parent[num] == num) return 1;
        if (visited[num]) return 0;
        visited[num] = true;

        return getCount(parent[num], visited) + 1;
    }
}
