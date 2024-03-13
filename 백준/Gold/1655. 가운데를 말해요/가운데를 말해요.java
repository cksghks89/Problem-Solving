import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());
            if (maxHeap.isEmpty()) {
                maxHeap.offer(cur);
            } else if (minHeap.isEmpty()) {
                minHeap.offer(cur);
                if (maxHeap.peek() > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(maxHeap.poll());
                }
            } else if (cur > minHeap.peek()) {
                minHeap.offer(cur);
            } else {
                maxHeap.offer(cur);
            }

            if (maxHeap.size() == minHeap.size() + 2) {
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() + 1 == minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            sb.append(maxHeap.peek()).append('\n');
        }

        System.out.println(sb);
    }
}
