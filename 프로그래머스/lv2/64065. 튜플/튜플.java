import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(String s) {
        List<List<Integer>> parseList = parse(s);
        int[] answer = new int[parseList.get(parseList.size() - 1).size()];

        List<Integer> prev = new ArrayList<>();
        for (int i = 0; i < parseList.size(); i++) {
            parseList.get(i).removeAll(prev);
            answer[i] = parseList.get(i).get(0);
            prev.add(answer[i]);
        }

        return answer;
    }

    private List<List<Integer>> parse(String s) {
        List<List<Integer>> list = new ArrayList<>();

        String[] parse = s.split("},");

        for (int i = 0; i < parse.length; i++) {
            parse[i] = parse[i].replaceAll("\\{", "");
            parse[i] = parse[i].replaceAll("}", "");

            String[] nums = parse[i].split(",");
            list.add(new ArrayList<>());
            for (String num : nums) {
                list.get(i).add(Integer.parseInt(num));
            }
        }

        list.sort((o1, o2) -> o1.size() - o2.size());
        return list;
    }
}