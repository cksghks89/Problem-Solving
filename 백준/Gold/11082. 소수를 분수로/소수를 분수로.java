import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = br.readLine();

        long denominator = getDenominator(num);
        long numerator = getNumerator(num);

        StringBuilder sb = new StringBuilder();
        while (true) {
            long gcd = gcd(denominator, numerator);
            if (gcd == 1) {
                sb.append(denominator).append('/').append(numerator);
                break;
            } else {
                denominator /= gcd;
                numerator /= gcd;
            }
        }

        System.out.println(sb);
    }

    private static long gcd(long a, long b) {
        long big = Math.max(a, b);
        long small = Math.min(a, b);

        if (small == 0) return big;
        return gcd(small, big % small);
    }

    private static long getNumerator(String num) {
        int decimalLength = getDecimalLength(num);
        int repeatingLength = getRepeatingLength(num);

        if (repeatingLength == 0) {
            return (long) Math.pow(10, decimalLength);
        }

        long result = 0;
        for (int i = 0; i < repeatingLength; i++) {
            result *= 10;
            result += 9;
        }

        for (int i = 0; i < decimalLength - repeatingLength; i++) {
            result *= 10;
        }

        return result;
    }

    private static int getDecimalLength(String num) {
        int len = 0;
        boolean isStart = false;

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '.') {
                isStart = true;
            }

            if (isStart && '0' <= num.charAt(i) && num.charAt(i) <= '9') {
                len += 1;
            }
        }

        return len;
    }

    private static int getRepeatingLength(String num) {
        int count = 0;
        boolean isStart = false;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '(') {
                isStart = true;
            } else if (num.charAt(i) == ')') {
                isStart = false;
            }

            if (isStart && '0' <= num.charAt(i) && num.charAt(i) <= '9') {
                count += 1;
            }
        }

        return count;
    }


    private static long getDenominator(String num) {
        long total = 0;
        long minus = 0;

        boolean isStart = true;

        for (int i = 0; i < num.length(); i++) {
            if ('0' <= num.charAt(i) && num.charAt(i) <= '9') {
                total *= 10;
                total += num.charAt(i) - '0';
            }

            if (num.charAt(i) == '(') {
                isStart = false;
            } else if (num.charAt(i) == ')') {
                isStart = true;
            }

            if (isStart && '0' <= num.charAt(i) && num.charAt(i) <= '9') {
                minus *= 10;
                minus += num.charAt(i) - '0';
            }
        }

        return total - minus == 0 ? total : total - minus;
    }
}
