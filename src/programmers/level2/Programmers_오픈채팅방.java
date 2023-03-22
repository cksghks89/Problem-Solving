package programmers.level2;

import java.util.*;

public class Programmers_오픈채팅방 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        System.out.println(Arrays.toString(sol.solution(record)));
    }

    static class Solution {
        Map<String, String> map = new HashMap<>();  // [userid, 닉네임] map

        public String[] solution(String[] record) {
            List<String> answer = new ArrayList<>();

            // 모든 로그에서 가장 나중에 변경된 닉네임을 map 에 덮어쓰기
            for (String log : record) {
                String[] cur = log.split(" ");
                if (cur.length == 2) continue;  // 나간 로그는 닉네임 변경이 없으므로 건너뛴다
                map.put(cur[1], cur[2]);    // 덮어 쓰는 부분
            }

            // map 의 [userid, 닉네임]을 기반으로 결과 만들기
            for (String log : record) {
                String[] cur = log.split(" ");
                if (cur[0].equals("Enter")) {
                    answer.add(map.get(cur[1]) + "님이 들어왔습니다.");
                } else if (cur[0].equals("Leave")) {
                    answer.add(map.get(cur[1]) + "님이 나갔습니다.");
                }
            }

            return answer.toArray(new String[0]);   // String[] 형태로 변환
        }
    }
}
