class Solution {
    public int solution(String s) {
        int answer = s.length();


        for (int i = 1; i <= s.length() / 2; i++) {
            answer = Math.min(answer, getStrPress(s, i));
        }

        return answer;
    }

    private int getStrPress(String s, int n) {
        char[] cur = new char[n];
        for (int i = 0; i < n; i++) cur[i] = s.charAt(i);
            
        int rtn = 0;

        int repeat = 1;
        for (int i = n; i + n - 1 < s.length(); i += n) {
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if (cur[j] != s.charAt(i + j)) check = false;
                cur[j] = s.charAt(i + j);
            }

            if (check) {
                repeat += 1;
            } else {
                rtn += repeat == 1 ? n : (repeat + "").length() + n;
                repeat = 1;
            }
        }

        if (repeat == 1) rtn += n;
        else rtn += (repeat + "").length() + n;

        return rtn + s.length() % n;
    }
}