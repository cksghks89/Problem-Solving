import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Map<Integer, Integer> idxMap;
    private static int[] firstArr;
    private static int[] secondArr;
    private static int[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        idxMap = new HashMap<>();
        firstArr = new int[N];
        secondArr = new int[N];
        segmentTree = new int[N * 4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            firstArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            secondArr[i] = Integer.parseInt(st.nextToken());
            idxMap.put(secondArr[i], i);
        }

        init(0, N - 1, 1);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            int idx = idxMap.get(firstArr[i]);
            answer += sum(0, N - 1, 1, 0, idx - 1);
            update(0, N - 1, 1, idx);
        }

        System.out.println(answer);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = 1;
        int mid = (start + end) / 2;
        return segmentTree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static int sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int index) {
        if (index < start || end < index) return;

        segmentTree[node] -= 1;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index);
        update(mid + 1, end, node * 2 + 1, index);
    }
}
