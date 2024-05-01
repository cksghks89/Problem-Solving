import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int r;
        int c;
        int d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    private static int N, K;
    private static int[][] fields;
    private static Node[] nodes;
    private static List<Node>[][] nodeFields;

    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fields = new int[N + 1][N + 1];
        nodeFields = new List[N + 1][N + 1];
        nodes = new Node[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
                nodeFields[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(r, c, d);
            nodeFields[r][c].add(nodes[i]);
        }


        for (int i = 1; i <= 1000; i++) {
            boolean isEnd = turn();
            if (isEnd) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    private static boolean turn() {
        // 턴 진행 중 말이 4개 이상 쌓이는 순간 게임 종료
        for (int i = 1; i <= K; i++) {
            Node cur = nodes[i];

            int nx = cur.r + dx[cur.d];
            int ny = cur.c + dy[cur.d];

            List<Node> list = new ArrayList<>();
            for (int j = nodeFields[cur.r][cur.c].indexOf(cur); j < nodeFields[cur.r][cur.c].size(); j++) {
                Node node = nodeFields[cur.r][cur.c].get(j);
                list.add(node);
            }
            for (Node node : list) nodeFields[cur.r][cur.c].remove(node);

            if (!isInRange(nx, ny) || fields[nx][ny] == 2) {
                if (cur.d == 1) cur.d = 2;
                else if (cur.d == 2) cur.d = 1;
                else if (cur.d == 3) cur.d = 4;
                else cur.d = 3;
            }

            nx = cur.r + dx[cur.d];
            ny = cur.c + dy[cur.d];

            if (!isInRange(nx, ny) || (isInRange(nx, ny) && fields[nx][ny] == 2)) {
                nodeFields[cur.r][cur.c].addAll(list);
            } else if (fields[nx][ny] == 0) {
                for (Node node : list) {
                    node.r = nx;
                    node.c = ny;
                    nodeFields[nx][ny].add(node);
                }
                if (nodeFields[nx][ny].size() >= 4) return true;
            } else if (fields[nx][ny] == 1) {
                for (int j = list.size() - 1; j >= 0; j--) {
                    list.get(j).r = nx;
                    list.get(j).c = ny;
                    nodeFields[nx][ny].add(list.get(j));
                }
                if (nodeFields[nx][ny].size() >= 4) return true;
            }
        }

        return false;
    }

    private static boolean isInRange(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }
}
