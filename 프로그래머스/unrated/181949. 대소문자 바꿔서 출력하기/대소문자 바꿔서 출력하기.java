import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String large = a.toUpperCase();
        
        String result = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == large.charAt(i)) {
                result += (char)(a.charAt(i) + 32);
            } else {
                result += large.charAt(i);
            }
        }
        System.out.println(result);
    }
}