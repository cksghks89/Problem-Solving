import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private Set<Integer> nodeSet;
    private boolean[] visited;
    private int[] answer;

    public int[] solution(int[][] edges) {
        answer = new int[4];

        // 그래프 초기화 ---- start
        int maxIndex = 0;
        for (int[] edge : edges) {
            maxIndex = Math.max(maxIndex, Math.max(edge[0], edge[1]));
        }

        graph = new List[maxIndex + 1];
        nodeSet = new HashSet<>();
        for (int i = 0; i <= maxIndex; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[maxIndex + 1];
        int[] outDegree = new int[maxIndex + 1];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            nodeSet.add(edge[0]);
            nodeSet.add(edge[1]);

            inDegree[edge[1]] += 1;
            outDegree[edge[0]] += 1;
        }

        int startNode = 0;  // 생성한 정점 번호 계산
        for (int i = 0; i <= maxIndex; i++) {
            if (inDegree[i] == 0 && nodeSet.contains(i) && outDegree[i] >= 2) {
                startNode = i;
                break;
            }
        }
        answer[0] = startNode;
        // 그래프 초기화 ---- end


        // 구현부 ----  start
        // 생성한 정점에 연결된 노드를 순차적으로 방문
        visited = new boolean[maxIndex + 1];
        for (int i = 0; i < graph[startNode].size(); i++) {
            int idx = graph[startNode].get(i);
            if (!visited[idx]) {
                bfs(idx);
            }
        }
        // 구현부 ----  end

        return answer;
    }


    // 그래프의 모양을 파악하는 bfs
    // 연결된 모든 정점을 순회하여 정점과 간선의 수를 뽑아 비교한다.
    private void bfs(int idx) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(idx);

        int nodeCnt = 0;
        int edgeCnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) continue;
            visited[cur] = true;
            nodeCnt += 1;
            edgeCnt += graph[cur].size();

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                if (!visited[next]) {
                    queue.offer(next);
                }
            }
        }

        if (nodeCnt == edgeCnt) answer[1] += 1;     // 정점수 = 간선수 : 도넛 모양
        else if (nodeCnt - 1 == edgeCnt) answer[2] += 1;    // 정점수 - 1 = 간선수 : 막대 모양
        else answer[3] += 1;                        // 그 외 : 8자 모양
    }
}