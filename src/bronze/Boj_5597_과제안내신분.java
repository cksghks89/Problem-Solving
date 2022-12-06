package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_5597_과제안내신분 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] students = new boolean[31];

        for(int i = 0; i < 28; i++){
            int cur = Integer.parseInt(br.readLine());
            students[cur] = true;
        }

        for(int i = 1; i < 31; i++){
            if(!students[i]){
                System.out.println(i);
            }
        }
    }
}
