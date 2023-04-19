import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTime = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreTime.put(genres[i], genreTime.getOrDefault(genres[i], 0) + plays[i]);
        }

        TreeSet<Integer> set = new TreeSet<>((o1, o2) -> {
            if (!genres[o1].equals(genres[o2])) {
                return genreTime.get(genres[o2]) - genreTime.get(genres[o1]);
            } else if (plays[o1] != plays[o2]) {
                return plays[o2] - plays[o1];
            } else {
                return o1 - o2;
            }
        });
        

        for (int i = 0; i < genres.length; i++) set.add(i);
        System.out.println(set);

        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> cnt = new HashMap<>();
        for (int idx : set) {
            if (!cnt.containsKey(genres[idx]) || cnt.get(genres[idx]) < 2) {
                cnt.put(genres[idx], cnt.getOrDefault(genres[idx], 0) + 1);
                answer.add(idx);
            }
        }

        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
}