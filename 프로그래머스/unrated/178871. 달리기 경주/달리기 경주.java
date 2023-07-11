import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // map으로 등수 저장
        Map<Integer, String> idRank = new HashMap<>();
        Map<String, Integer> nameRank = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            idRank.put(i, players[i]);
            nameRank.put(players[i], i);
        }
        
        for (String call : callings) {
            swap(idRank, nameRank, call);
        }
        
        String[] answer = new String[players.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = idRank.get(i);
        }
        
        return answer;
    }
    
    private void swap(Map<Integer, String> idRank, Map<String, Integer> nameRank, String call) {
        int curRank = nameRank.get(call);
        nameRank.put(call, nameRank.get(call) - 1);
        
        String front = idRank.get(curRank - 1);
        idRank.put(curRank - 1, call);
        idRank.put(curRank, front);
        
        nameRank.put(front, curRank);
    }
}