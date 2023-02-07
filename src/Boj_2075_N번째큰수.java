/*
    Boj 2075. N번째 큰 수
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_2075_N번째큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer>[] pqList = new PriorityQueue[N];
        for (int i = 0; i < N; i++) {
            pqList[i] = new PriorityQueue<>((x, y) -> y - x);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                pqList[j].add(Integer.parseInt(st.nextToken()));
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            int idx = 0;
            int cur = Integer.MIN_VALUE;
            for (int j = 0; j < N; j++) {
                if(cur < pqList[j].peek()){
                    cur = pqList[j].peek();
                    idx = j;
                }
            }
            max = pqList[idx].poll();
        }

        System.out.println(max);
    }
}
