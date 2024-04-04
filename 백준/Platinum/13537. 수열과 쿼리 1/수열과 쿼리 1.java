import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tree = new List[4 * N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4 * N; i++) tree[i] = new ArrayList<>();

        init(0, N - 1, 1);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int answer = sum(0, N - 1, 1, a - 1, b - 1, k);
            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    private static List<Integer> init(int start, int end, int node) {
        if (start == end) {
            tree[node].add(arr[start]);
            return tree[node];
        }

        int mid = (start + end) / 2;
        List<Integer> l = init(start, mid, node * 2);
        List<Integer> r = init(mid + 1, end, node * 2 + 1);

        tree[node] = mergeSort(l, r);
        return tree[node];
    }

    private static List<Integer> mergeSort(List<Integer> l, List<Integer> r) {
        List<Integer> result = new ArrayList<>();

        int p1 = 0;
        int p2 = 0;

        while (p1 < l.size() && p2 < r.size()) {
            if (l.get(p1) < r.get(p2)) {
                result.add(l.get(p1++));
            } else {
                result.add(r.get(p2++));
            }
        }

        while (p1 < l.size()) result.add(l.get(p1++));
        while (p2 < r.size()) result.add(r.get(p2++));

        return result;
    }

    private static int sum(int start, int end, int node, int left, int right, int k) {
        if (left > end || start > right) return 0;

        if (left <= start && end <= right) {
            int idx = Collections.binarySearch(tree[node], k);
            if (idx < 0) {
                return tree[node].size() + idx + 1;
            } else {
                // 중복 체크
                while (idx < tree[node].size()) {
                    if (tree[node].get(idx) != k) break;
                    idx++;
                }
                return tree[node].size() - idx;
            }
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right, k) + sum(mid + 1, end, node * 2 + 1, left, right, k);
    }
}
