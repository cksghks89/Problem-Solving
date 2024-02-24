import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) deque.offer(i);

        int[] answer = new int[N];
        int status = N;
        for (int i = 0; i < N; i++) {
            if (arr[i] == 1) {
                answer[deque.poll()] = status--;
            } else if (arr[i] == 2) {
                Integer first = deque.poll();
                answer[deque.poll()] = status--;
                deque.offerFirst(first);
            } else if (arr[i] == 3) {
                answer[deque.pollLast()] = status--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(answer[i]).append(' ');

        System.out.println(sb);
    }
}
