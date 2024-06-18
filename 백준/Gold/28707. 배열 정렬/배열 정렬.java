import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static int[][] manipulate;

    private static int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        manipulate = new int[M][3];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            manipulate[i][0] = Integer.parseInt(st.nextToken()) - 1;
            manipulate[i][1] = Integer.parseInt(st.nextToken()) - 1;
            manipulate[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = getSortCount();
        System.out.println(answer);
    }

    private static int getSortCount() {
        PriorityQueue<Sequence> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Sequence(arr, 0));

        Set<Sequence> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Sequence cur = pq.poll();

            if (visited.contains(cur)) continue;
            visited.add(cur);

            if (isAsc(cur)) return cur.cost;

            for (int i = 0; i < M; i++) {
                Sequence next = swap(cur, manipulate[i][0], manipulate[i][1], manipulate[i][2]);
                if (visited.contains(next)) continue;
                pq.offer(next);
            }
        }

        return -1;
    }

    private static Sequence swap(Sequence seq, int l, int r, int c) {
        int[] arr = Arrays.copyOf(seq.arr, seq.arr.length);
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;

        return new Sequence(arr, seq.cost + c);
    }

    private static boolean isAsc(Sequence sequence) {
        for (int i = 1; i < sequence.arr.length; i++) {
            if (sequence.arr[i - 1] > sequence.arr[i]) return false;
        }
        return true;
    }

    private static class Sequence {
        int[] arr;
        int cost;

        public Sequence(int[] arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Sequence sequence = (Sequence) o;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != sequence.arr[i]) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            for (int i = 0; i < arr.length; i++) {
                hash += prime[i] * arr[i];
            }
            return hash;
        }
    }
}
