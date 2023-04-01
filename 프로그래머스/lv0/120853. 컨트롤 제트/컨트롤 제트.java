class Solution {
    public int solution(String s) {
        int answer = 0;

        String[] input = s.split(" ");
        int prev = 0;
        for (String n : input) {
            if ("Z".equals(n)) {
                answer -= prev;
            } else {
                prev = Integer.parseInt(n);
                answer += prev;
            }
        }

        return answer;
    }
}