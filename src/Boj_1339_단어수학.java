/*
    Boj 1339. 단어 수학
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Boj_1339_단어수학 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<Character, Integer> alpha = new HashMap<>();

        for(int i = 0; i < N; i++){
            String num = br.readLine().trim();

            int idx = 0;
            for(int j = num.length() - 1; j >= 0; j--){
                alpha.put(num.charAt(j), alpha.getOrDefault(num.charAt(j), 0) + (int)Math.pow(10, idx));
                idx++;
            }
        }

        ArrayList<Integer> li = new ArrayList<>(alpha.values());
        Collections.sort(li, (a, b) -> b - a);

        int sum = 0;
        int mul = 9;
        for(int i = 0; i < li.size(); i++){
            sum += li.get(i) * mul;
            mul--;
        }
        System.out.println(sum);

    }
}
