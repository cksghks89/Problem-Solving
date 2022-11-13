/*
    Boj 5397. 키로거
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Boj_5397_키로거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            String key = br.readLine();

            sb.append(getPassword(key)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static String getPassword(String key) {
        // LinkedList 는 중간에서 삽입, 삭제시 시간복잡도 면에서 유리함
        // ArraysList 는 끝에서 삽입, 삭제시 시간복잡도 면에서 유리함
        // 따라서 이 문제는 중간에서 삽입, 삭제하는 작업이 많으므로 LinkedList 자료구조를 사용해야 시간초과가 나지 않음
        List<Character> password = new LinkedList<>();
        int idx = 0;

        for (int i = 0; i < key.length(); i++) {
            if(key.charAt(i) == '<'){
                idx = validationIdx(idx - 1, password);
            }else if(key.charAt(i) == '>'){
                idx = validationIdx(idx + 1, password);
            }else if(key.charAt(i) == '-'){
                if(idx == 0){
                    continue;
                }
                password.remove(--idx);
            }else{
                password.add(idx++, key.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        password.forEach(sb::append);
        return sb.toString();
    }

    static int validationIdx(int x, List<Character> password) {
        if (x < 0) {
            return 0;
        } else if (x > password.size()) {
            return password.size();
        } else {
            return x;
        }
    }
}
