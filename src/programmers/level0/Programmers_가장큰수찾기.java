package programmers.level0;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Programmers_가장큰수찾기 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int[] solution(int[] array) {
            int[] answer = {Arrays.stream(array).max().getAsInt(), 0};
            IntStream.range(0, array.length).forEach(x -> {
                if (array[x] == answer[0]) answer[1] = x;
            });
            return answer;
        }
    }
}
