import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static List<Integer> cards;
    private static int[] minsu;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cards = new ArrayList<>();
        parent = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            parent[i] = i;
            cards.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(cards);

        minsu = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int cur = minsu[i];

            int idx = Collections.binarySearch(cards, cur);
            int card = idx < 0 ? find(-idx - 1) : find(idx + 1);

            if (card + 1 != M) {
                union(card, card + 1);
            }
            sb.append(cards.get(card)).append('\n');
        }

        System.out.println(sb);
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        parent[pa] = pb;
    }
}
