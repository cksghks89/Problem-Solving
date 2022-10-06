/*
    Boj 18870. 좌표 압축
    level. silver 2
    solved by 송찬환
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Boj_18870_좌표압축 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        HashSet<Integer> set = new HashSet<>();

        int N = Integer.parseInt(sc.nextLine().trim());
        int[] input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i: input){
            set.add(i);
        }
        int[] cloneInput = set.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.sort(cloneInput);

        int[] prime = new int[N];

        for(int i = 0; i < N; i++){
            prime[i] = Arrays.binarySearch(cloneInput, input[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i: prime){
            sb.append(String.valueOf(i)).append(' ');
        }
        System.out.println(sb);
    }
}
