import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<int[]>[] graph;
    private static List<int[]> points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        graph = new List[10];
        for (int i = 0; i < 10; i++) graph[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int xs = Integer.parseInt(st.nextToken());
        int ys = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int xe = Integer.parseInt(st.nextToken());
        int ye = Integer.parseInt(st.nextToken());

        points = new ArrayList<>();
        points.add(new int[]{xs, ys});
        points.add(new int[]{xe, ye});

        int idx = 2;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            points.add(new int[]{x1, y1});
            points.add(new int[]{x2, y2});

            graph[idx].add(new int[]{idx + 1, 10});
            graph[idx + 1].add(new int[]{idx, 10});
            idx += 2;
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                graph[i].add(new int[]{j, getDistance(i, j)});
                graph[j].add(new int[]{i, getDistance(i, j)});
            }
        }

        long answer = dijkstra();
        System.out.println(answer);
    }

    private static long dijkstra() {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] < o2[1]) return -1;
            else if (o1[1] > o2[1]) return 1;
            else return 0;
        });
        pq.offer(new long[]{0, 0});

        long[] dist = new long[10];
        Arrays.fill(dist, Long.MAX_VALUE);

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            if (cur[0] == 1) {
                return cur[1];
            }

            for (int i = 0; i < graph[(int) cur[0]].size(); i++) {
                int[] next = graph[(int) cur[0]].get(i);
                if (cur[1] + next[1] < dist[next[0]]) {
                    dist[next[0]] = cur[1] + next[1];
                    pq.offer(new long[]{next[0], dist[next[0]]});
                }
            }
        }

        return -1;
    }

    private static int getDistance(int i, int j) {
        return Math.abs(points.get(i)[0] - points.get(j)[0])
                + Math.abs(points.get(i)[1] - points.get(j)[1]);
    }
}
