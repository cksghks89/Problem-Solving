class Solution {
    public int solution(String[] lines) {
        int answer = 0;

        int[][] lineTimes = new int[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String[] cur = lines[i].split(" ");
            cur[2] = cur[2].replace("s", "");

            lineTimes[i][1] = parseTime(cur[1]);
            lineTimes[i][0] = lineTimes[i][1] - (int) (Double.parseDouble(cur[2]) * 1000) + 1;
            if (lineTimes[i][0] < 0) lineTimes[i][0] = 0;
        }

        for (int i = 0; i < lineTimes.length; i++) {
            int cnt = 1;
            for (int j = i + 1; j < lineTimes.length; j++) {
                if (lineTimes[j][0] < lineTimes[i][1] + 1000) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    private int parseTime(String str) {
        String[] msParse = str.split("\\.");
        String hms = msParse[0];
        String ms = msParse[1];

        int time = Integer.parseInt(ms);

        String[] timeParse = hms.split(":");
        time += Integer.parseInt(timeParse[2]) * 1000;
        time += Integer.parseInt(timeParse[1]) * 60 * 1000;
        time += Integer.parseInt(timeParse[0]) * 60 * 60 * 1000;
        return time;
    }
}