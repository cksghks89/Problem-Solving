import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] cupNoodle;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cupNoodle = new int[N][2];
        parent = new int[200001];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cupNoodle[i][0] = Integer.parseInt(st.nextToken());
            cupNoodle[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cupNoodle, (o1, o2) -> o2[1] - o1[1]);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += insert(cupNoodle[i][0], i);
        }

        System.out.println(answer);
    }

    private static int insert(int deadline, int idx) {
        int p = find(deadline);

        if (p != 0) {
            union(p - 1, p);
            return cupNoodle[idx][1];
        }

        return 0;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        parent[py] = px;
    }
}
