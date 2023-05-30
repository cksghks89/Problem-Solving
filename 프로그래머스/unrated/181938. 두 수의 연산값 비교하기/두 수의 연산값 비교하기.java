class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        
        int ab = Integer.parseInt(a + "" + b);
        int _2ab = 2 * a * b;
        
        answer = Math.max(ab, _2ab);
        return answer;
    }
}