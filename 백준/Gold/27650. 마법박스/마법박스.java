import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int N;
    private static List<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        setPrimeList(N);

        int p1 = 0;
        int p2 = primeList.size() - 1;

        int answer = p2;
        while (p1 <= p2) {
            int mid = (p1 + p2) / 2;

            System.out.println("? " + primeList.get(mid));
            System.out.flush();

            int input = sc.nextInt();

            if (input == 1) {
                p1 = mid + 1;
            } else if (input == 0) {
                p2 = mid - 1;
                answer = mid;
            } else {
                throw new IOException();
            }
        }

        System.out.println("! " + primeList.get(answer));
        System.out.flush();
    }

    private static void setPrimeList(int N) {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= N; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * 2; j <= N; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }
    }
}
