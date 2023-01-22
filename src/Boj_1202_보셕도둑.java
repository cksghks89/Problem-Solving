/*
    Boj 1202. 보석 도둑
    level. gold 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1202_보셕도둑 {
    static int N, K;

    static ArrayList<Bosuk> list;
    static ArrayList<Integer> bags;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        bags = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            list.add(new Bosuk(M, V));
        }

        list.sort((x, y) -> {
            if (x.m != y.m) {
                return x.m - y.m;
            } else {
                return y.v - x.v;
            }
        });

        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);

        System.out.println(getMaxSum());
    }

    static long getMaxSum() {
        PriorityQueue<Bosuk> pq = new PriorityQueue<>((x, y) -> {
            if (x.v != y.v) {
                return y.v - x.v;
            } else {
                return x.m - y.m;
            }
        });

        long sum = 0;
        int idx = 0;
        for (int c : bags) {
            for (int i = idx; i < list.size(); i++) {
                if (list.get(i).m > c) {
                    break;
                }

                pq.offer(list.get(i));
                idx += 1;
            }

            if(!pq.isEmpty()){
                sum += pq.poll().v;
            }
        }
        return sum;
    }

    static class Bosuk {
        int m;
        int v;

        public Bosuk(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}
