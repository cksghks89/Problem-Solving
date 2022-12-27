/*
    Boj 16928. 뱀과 사다리 게임
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_16928_뱀과사다리게임 {
    static Map<Integer, Integer> map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        visited = new boolean[101];

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.put(x, y);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});

        int result = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int pos = cur[0];
            int cnt = cur[1];

            if(pos == 100){
                result = cnt;
                break;
            }

            if(visited[pos]){
                continue;
            }
            visited[pos] = true;

            for (int i = 1; i <= 6; i++) {
                int nextPos = pos + i;
                if (nextPos > 100) {
                    break;
                }

                if(!visited[nextPos]){
                    if(map.containsKey(nextPos)){
                        queue.offer(new int[]{map.get(nextPos), cnt + 1});
                        continue;
                    }
                    queue.offer(new int[]{nextPos, cnt + 1});
                }
            }
        }

        return result;
    }
}
