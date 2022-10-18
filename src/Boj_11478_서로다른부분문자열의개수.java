/*
    Boj 11478. 서로 다른 부분 문자열의 개수
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Boj_11478_서로다른부분문자열의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        Set<String> set = new HashSet<>();

        for(int i = 1; i <= S.length(); i++){
            for(int j = 0; j <= S.length() - i; j++){
                set.add(S.substring(j, j+i));
            }
        }
        System.out.println(set.size());
    }
}
