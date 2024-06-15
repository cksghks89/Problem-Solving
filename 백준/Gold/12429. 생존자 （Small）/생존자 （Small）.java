import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] foods;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            foods = new int[N][2];
            visited = new boolean[N];

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                foods[j][0] = Integer.parseInt(st.nextToken());
                foods[j][1] = Integer.parseInt(st.nextToken());
            }

            int answer = bruteForce(0);
            sb.append("Case #").append(tc).append(": ").append(answer).append('\n');
        }
        System.out.println(sb);
    }

    private static int bruteForce(int hunger) {
        int max = hunger;
        for (int i = 0; i < foods.length; i++) {
            if (foods[i][0] < hunger || visited[i]) continue;
            visited[i] = true;
            max = Math.max(max, bruteForce(hunger + foods[i][1]));
            visited[i] = false;
        }
        return max;
    }
}
