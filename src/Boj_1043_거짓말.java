/*
    Boj 1043. 거짓말
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1043_거짓말 {
    static int N, M;
    static int[][] graph;
    static Set<Integer> trueSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> trueList = new ArrayList<>();
        trueSet = new HashSet<>();
        graph = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            trueList.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<ArrayList<Integer>> partyList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            ArrayList<Integer> cur = new ArrayList<>();
            cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                cur.add(Integer.parseInt(st.nextToken()));
            }
            partyList.add(cur);
        }

        setGraph(partyList);
        for (int i = 0; i < trueList.size(); i++) {
            bfs(trueList.get(i));
        }

        int ans = getLyingPartyCnt(partyList);
        System.out.println(ans);

    }

    static int getLyingPartyCnt(ArrayList<ArrayList<Integer>> partyList) {
        int cnt = 0;

        for (ArrayList<Integer> cur : partyList) {
            boolean check = true;

            for (Integer integer : cur) {
                if (trueSet.contains(integer)) {
                    check = false;
                    break;
                }
            }

            if (check) {
                cnt++;
            }
        }

        return cnt;
    }

    static void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (trueSet.contains(cur)) {
                continue;
            }
            trueSet.add(cur);

            for (int i = 1; i <= N; i++) {
                if (graph[cur][i] == 1) {
                    queue.add(i);
                }
            }
        }
    }

    static void setGraph(ArrayList<ArrayList<Integer>> partyList) {
        for (int i = 0; i < M; i++) {
            ArrayList<Integer> cur = partyList.get(i);

            for (int j = 0; j < cur.size(); j++) {
                for (int k = j + 1; k < cur.size(); k++) {
                    graph[cur.get(j)][cur.get(k)] = 1;
                    graph[cur.get(k)][cur.get(j)] = 1;
                }
            }
        }
    }
}
