/*
    Boj 11723. 집합
    level. silver 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_11723_집합 {
    public static void main(String[] args) throws IOException {
        boolean[] set = new boolean[21];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            String[] input = br.readLine().split(" ");

            if(input[0].equals("add")){
                set[Integer.parseInt(input[1])] = true;
            }else if(input[0].equals("remove")){
                set[Integer.parseInt(input[1])] = false;
            }else if(input[0].equals("check")){
                if(set[Integer.parseInt(input[1])]){
                    sb.append("1").append('\n');
                }else{
                    sb.append("0").append('\n');
                }
            }else if(input[0].equals("toggle")){
                if(set[Integer.parseInt(input[1])]){
                    set[Integer.parseInt(input[1])] = false;
                }else{
                    set[Integer.parseInt(input[1])] = true;
                }
            }else if(input[0].equals("all")){
                for(int j = 1; j <= 20; j++){
                    set[j] = true;
                }
            }else if(input[0].equals("empty")){
                for(int j = 1; j <= 20; j++){
                    set[j] = false;
                }
            }
        }
        System.out.println(sb);
    }
}
