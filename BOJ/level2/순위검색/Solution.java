package level2.순위검색;

import java.util.*;
import java.util.Map.Entry;

class Solution {

	public static void main(String[] args) {
		int[] result = solution(new String[] { "java backend junior pizza 150"}, new String[] {
				"java and backend and junior and pizza 150","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 150","- and - and - and - 150" });
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		
		Map<String, List<Integer>> map = new HashMap<String,List<Integer>>();
		// 데이터 입력
		for (String s : info) {
			String[] tokens = s.split(" ");
			int score = Integer.parseInt(tokens[4]);
			for (int i = 0; i<16;i++) {
				String tmp = "";
				for (int j = 0; j<4;j++) {
					tmp += (i & 1<<j) != 0 ? tokens[j] : "-"; 
				}
				map.putIfAbsent(tmp, new ArrayList<Integer>());
				map.get(tmp).add(score);
				
			}
		}
		
		for (String key : map.keySet()) {
			Collections.sort(map.get(key));
		}
		
		for (int i = 0; i<query.length;i++) {
			String q = query[i].replaceAll(" and ", "");
			String[] tokens = q.split(" ");
			answer[i] = map.containsKey(tokens[0]) ? binarySearch(tokens[0], Integer.parseInt(tokens[1]),map.get(tokens[0])) : 0;
		}
		
		
		return answer;
	}
    // 이분 탐색
    private static int binarySearch(String key, int score, List<Integer> list) {
        int start = 0, end = list.size() - 1;
 
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }
 
        return list.size() - start;
    }
}