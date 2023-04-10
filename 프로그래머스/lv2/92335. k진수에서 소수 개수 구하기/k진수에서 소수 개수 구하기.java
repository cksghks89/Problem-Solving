class Solution {
    public int solution(int n, int k) {
        String str = Integer.toString(n, k);
        String[] primes = str.replaceAll("0+", "0").split("0");

        int answer = 0;
        for (String prime : primes) {
            if (!isPrime(Long.parseLong(prime))) continue;
            answer++;
        }

        return answer;
    }

    private boolean isPrime(long num) {
        if (num == 1) return false;

        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}