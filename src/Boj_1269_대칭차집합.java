/*
    Boj 1269. 대칭 차집합
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj_1269_대칭차집합 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int aLen = Integer.parseInt(st.nextToken());
        int bLen = Integer.parseInt(st.nextToken());

        Set<String> A = new HashSet<>();
        Set<String> B = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < aLen; i++){
            A.add(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < bLen; i++){
            B.add(st.nextToken());
        }

        Set<String> aMb = new HashSet<>(A);
        aMb.removeAll(B);
        Set<String> bMa = new HashSet<>(B);
        bMa.removeAll(A);

        System.out.println(aMb.size() + bMa.size());
    }
}
