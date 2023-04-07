import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class Solution {
    Map<String, Integer> str1Map;
    Map<String, Integer> str2Map;

    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        str1Map = makeMap(str1);
        str2Map = makeMap(str2);

        int retain = 0;
        int union = 0;

        for (Map.Entry<String, Integer> entry : str1Map.entrySet()) {
            if (str2Map.containsKey(entry.getKey())) {
                retain += Math.min(entry.getValue(), str2Map.get(entry.getKey()));
                union += Math.max(entry.getValue(), str2Map.get(entry.getKey()));
                str2Map.remove(entry.getKey());
            } else {
                union += entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : str2Map.entrySet()) {
            union += entry.getValue();
        }

        int answer = 65536;
        if (union != 0) {
            answer = (int) (1.0 * retain / union * 65536);
        }

        return answer;
    }

    private Map<String, Integer> makeMap(String str) {
        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile("^[a-z][a-z]$");

        for (int i = 0; i < str.length() - 1; i++) {
            String cur = str.substring(i, i + 2);
            if(pattern.matcher(cur).find()){
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
        }
        return map;
    }
}