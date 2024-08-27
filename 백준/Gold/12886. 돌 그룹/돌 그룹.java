import java.io.*;
import java.util.*;

public class Main {
    private static class Stone {
        int A, B, C;

        public Stone(int o1, int o2, int o3) {
            int[] arr = new int[]{o1, o2, o3};
            Arrays.sort(arr);

            this.A = arr[2];
            this.B = arr[1];
            this.C = arr[0];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Stone stone = (Stone) o;
            return A == stone.A && B == stone.B && C == stone.C;
        }

        @Override
        public int hashCode() {
            return Objects.hash(A, B, C);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Stone start = new Stone(A, B, C);

        int answer = bfs(start);

        System.out.println(answer);
    }

    private static int bfs(Stone stone) {
        Queue<Stone> queue = new ArrayDeque<>();
        queue.offer(stone);

        Set<Stone> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Stone cur = queue.poll();

            if (visited.contains(cur)) continue;
            visited.add(cur);

            if (cur.A == cur.B && cur.B == cur.C) return 1;

            int aMinusB = cur.A - cur.B;
            int bDouble = cur.B + cur.B;
            queue.offer(new Stone(aMinusB, bDouble, cur.C));

            int aMinusC = cur.A - cur.C;
            int cDouble = cur.C + cur.C;
            queue.offer(new Stone(aMinusC, cur.B, cDouble));
        }

        return 0;
    }
}