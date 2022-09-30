/*
    Boj 1806. 부분합
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int p1 = 0;
        int p2 = 0;
        int sum = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while(true){
            if(sum >= S){
                pq.add(p2 - p1);
                sum -= arr[p1++];
            }else if(p2 == arr.length){
                break;
            }else{
                sum += arr[p2++];
            }
        }

        if(pq.isEmpty()){
            System.out.println("0");
        }else{
            System.out.println(pq.peek());
        }
    }
}
