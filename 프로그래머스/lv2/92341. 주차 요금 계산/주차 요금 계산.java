import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> in = new HashMap<>();
        Map<String, Integer> time = new TreeMap<>();

        for (String record : records) {
            String[] cur = record.split(" ");

            if ("IN".equals(cur[2])) {
                in.put(cur[1], cur[0]);
            } else if ("OUT".equals(cur[2])) {
                time.put(cur[1], time.getOrDefault(cur[1], 0) + calcTime(in.get(cur[1]).split(":"), cur[0].split(":")));
                in.remove(cur[1]);
            }
        }

        for (String key : in.keySet()) {
            time.put(key, time.getOrDefault(key, 0) + calcTime(in.get(key).split(":"), new String[]{"23", "59"}));
        }

        int[] answer = new int[time.size()];
        int idx = 0;
        for (int value : time.values()) {
            answer[idx++] = calcFee(fees, value);
        }
        return answer;
    }

    private int calcFee(int[] fees, int time) {
        if (fees[0] >= time) return fees[1];
        return fees[1] + (int) Math.ceil(1.0 * (time - fees[0]) / fees[2]) * fees[3];
    }

    private int calcTime(String[] in, String[] out) {
        int outTime = Integer.parseInt(out[0]) * 60 + Integer.parseInt(out[1]);
        int inTime = Integer.parseInt(in[0]) * 60 + Integer.parseInt(in[1]);
        return outTime - inTime;
    }
}