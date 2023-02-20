package programmers;

public class Programmers_다항식더하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("3x + 7 + x"));
    }

    static class Solution {
        public String solution(String polynomial) {
            String[] split = polynomial.split("\\+");

            int _x = 0;
            int constant = 0;
            for (int i = 0; i < split.length; i++) {
                if (split[i].contains("x")) {
                    String cur = split[i].trim().replace("x", "");
                    if (cur.length() == 0) {
                        _x += 1;
                    } else {
                        _x += Integer.parseInt(cur);
                    }
                } else {
                    constant += Integer.parseInt(split[i].trim());
                }
            }

            if (_x == 0 && constant == 0) {
                return "0";
            }
            if (_x == 0) {
                return constant + "";
            }
            return (_x == 1 ? "x" : _x + "x") + (constant == 0 ? "" : " + " + constant);
        }
    }
}
