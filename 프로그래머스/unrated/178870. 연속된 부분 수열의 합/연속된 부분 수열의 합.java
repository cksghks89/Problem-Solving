class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int length = 11_000_000;
        int sum = 0;
        int p1 = -1;
        int p2 = 0;
        while (p1 < p2) {
            if (p1 == p2) break;
            
            if (sum < k) {
                if (p2 == sequence.length) break;
                sum += sequence[p2++];
            } else if (sum > k) {
                sum -= sequence[++p1];
            } else if (sum == k) {
                if (p2 - p1 - 1 < length) {
                    length = p2 - p1 - 1;
                    answer = new int[] {p1 + 1, p2 - 1};
                }
                sum -= sequence[++p1];
            }
        }
        
        return answer;
    }
}