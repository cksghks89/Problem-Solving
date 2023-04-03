import java.util.ArrayDeque;

class Solution {
    public long solution(String expression) {
        long answer = 0;

        char[][] possible = {
                {'+', '-', '*'},
                {'+', '*', '-'},
                {'-', '+', '*'},
                {'-', '*', '+'},
                {'*', '+', '-'},
                {'*', '-', '+'}
        };

        for (char[] op : possible) {
            answer = Math.max(answer, calc(expression, op));
        }

        return answer;
    }

    long calc(String exp, char[] op) {
        ArrayDeque<Long> num = new ArrayDeque<>();
        ArrayDeque<Character> ops = new ArrayDeque<>();

        String n = "";
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*') {
                if (!"".equals(n)) num.add(Long.parseLong(n));
                n = "";
                ops.offer(exp.charAt(i));
                continue;
            }
            n += exp.charAt(i);
        }
        num.offer(Long.parseLong(n));

        for (char c : op) {
            int k = ops.size();

            for (int i = 0; i < k; i++) {
                char cur = ops.poll();
                if (cur == c) {
                    if (cur == '*') {
                        num.addFirst(num.poll() * num.poll());
                    } else if (cur == '-') {
                        num.addFirst(num.poll() - num.poll());
                    } else if (cur == '+') {
                        num.addFirst(num.poll() + num.poll());
                    }
                } else {
                    ops.offer(cur);
                    num.offer(num.poll());
                }
            }
            num.offer(num.poll());
        }

        return Math.abs(num.poll());
    }
}