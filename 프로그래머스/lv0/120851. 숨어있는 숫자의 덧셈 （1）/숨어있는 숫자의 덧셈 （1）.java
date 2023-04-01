import java.util.Arrays;

class Solution {
    public int solution(String my_string) {
        my_string = my_string.replaceAll("[a-zA-Z]", "");
        return Arrays.stream(my_string.split("")).mapToInt(Integer::parseInt).sum();
    }
}