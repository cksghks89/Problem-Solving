/*
    Boj 14003. 가장 긴 증가하는 부분 수열 5
    level. platinum 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj_14003_가장긴증가하는부분수열5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 초기화
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = getLis(arr);

        StringBuilder sb = new StringBuilder();
        sb.append(lis.length).append("\n");
        for(int n : lis){
            sb.append(n).append(" ");
        }
        System.out.print(sb.toString());
    }

    static int[] getLis(int[] arr) {
        ArrayList<Integer> indexArr = new ArrayList<>();
        ArrayList<Integer> lisArr = new ArrayList<>();

        lisArr.add(arr[0]);
        indexArr.add(0);

        for (int i = 1; i < arr.length; i++) {
            if (lisArr.get(lisArr.size() - 1) < arr[i]) {
                lisArr.add(arr[i]);
                indexArr.add(lisArr.size() - 1);
            } else {
                int idx = Collections.binarySearch(lisArr, arr[i]);
                if(idx >= 0){
                    indexArr.add(idx);
                }else{
                    lisArr.set(-idx - 1, arr[i]);
                    indexArr.add(-idx - 1);
                }
            }
        }

        int maxIdx = indexArr.stream().mapToInt(Integer::valueOf).max().getAsInt();
        int[] lis = new int[lisArr.size()];
        int idx = lisArr.size() - 1;
        for(int i = indexArr.size() - 1; i >= 0; i--){
            if(indexArr.get(i) == maxIdx){
                lis[idx--] = arr[i];
                maxIdx--;
                if(idx < 0){
                    break;
                }
            }
        }
        return lis;
    }
}
