import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 시간 합산
        Map<String, Integer> genreTime = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genreTime.put(genres[i], genreTime.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 1, 2, 3 번 조건으로 정렬한 값을 저장하는 pq 자료구조
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if (!genres[o1].equals(genres[o2])) {
                return genreTime.get(genres[o2]) - genreTime.get(genres[o1]);
            } else if (plays[o1] != plays[o2]) {
                return plays[o2] - plays[o1];
            } else {
                return o1 - o2;
            }
        });

        // pq 에 고유번호 넣기
        for (int i = 0; i < genres.length; i++) pq.add(i);

        // 장르별 2개씩만 결과 배열에 담기
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> cnt = new HashMap<>();
        while(!pq.isEmpty()) {
            int idx = pq.poll();
            if (!cnt.containsKey(genres[idx]) || cnt.get(genres[idx]) < 2) {
                cnt.put(genres[idx], cnt.getOrDefault(genres[idx], 0) + 1);
                result.add(idx);
            }
        }

        // 출력부
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = result.get(i);

        return answer;
    }
}