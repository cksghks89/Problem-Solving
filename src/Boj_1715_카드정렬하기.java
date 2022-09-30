/*
    Boj 1715. 카드 정렬하기
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj_1715_카드정렬하기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        if(pq.size() <= 1){
            System.out.println("0");
        }else{
            int total = 0;
            while(pq.size() >= 2){
                int sum = pq.poll() + pq.poll();
                total += sum;
                pq.add(sum);
            }
            System.out.println(total);
        }
    }
}
