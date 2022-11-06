/*
    Boj 9251. LCS
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_9251_LCS {
    static int[][] lcs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        lcs = new int[a.length()+1][b.length()+1];

        calcLcs(a, b);

        System.out.println(lcs[a.length()][b.length()]);
    }
    static void calcLcs(String a, String b){
        for(int i = 1; i < a.length() + 1; i++){
            for(int j = 1; j < b.length() + 1; j++){
                char lastCharA = a.charAt(i - 1);
                char lastCharB = b.charAt(j - 1);

                if(lastCharA == lastCharB){
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                }else{
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }
    }
}
