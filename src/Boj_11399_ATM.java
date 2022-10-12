/*
    boj 11399. ATM
    level. silver 4
    solved by 송찬환
 */
import java.util.Arrays;
import java.util.Scanner;

public class Boj_11399_ATM {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine().trim());
        int[] pi = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(pi);

        int sum = 0;
        int answer = 0;
        for(int i = 0; i < pi.length; i++){
            sum += pi[i];
            answer += sum;
        }
        System.out.println(answer);
    }
}
