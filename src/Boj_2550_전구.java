/*
    Boj 2550. 전구
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Boj_2550_전구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] switchArr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] bulbArr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] lis = new int[N];
        for(int i = 0; i < N; i++){
            int idx = -1;
            for(int j = 0; j < N; j++){
                if(switchArr[i] == bulbArr[j]){
                    idx = j;
                    break;
                }
            }
            lis[i] = idx;
        }

        ArrayList<Integer> rtn = makeLis(lis);

        int max = rtn.stream().mapToInt(Integer::valueOf).max().getAsInt();
        System.out.println(max+1);

        ArrayList<Integer> temp = new ArrayList<>();
        int idx = rtn.size()-1;
        while(max >= 0){
            for(int i = idx; i >= 0; i--){
                if(rtn.get(i) == max){
                    max--;
                    idx = i;
                    temp.add(switchArr[i]);
                    break;
                }
            }
        }

        Collections.sort(temp);
        StringBuilder sb = new StringBuilder();
        for(int n : temp){
            sb.append(n).append(" ");
        }
        System.out.print(sb.toString());
    }

    static ArrayList<Integer> makeLis(int[] lis){
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();

        for(int i = 0; i < lis.length;i++){
            int idx = Collections.binarySearch(temp, lis[i]);

            if(-idx-1 >= temp.size()){
                temp.add(lis[i]);
            }else{
                temp.set(-idx-1, lis[i]);
            }

            temp2.add(-idx-1);
        }

        return temp2;
    }
}
