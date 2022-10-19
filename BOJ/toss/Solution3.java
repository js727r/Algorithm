package toss;

public class Solution3 {
	public static void main(String[] args) {
		int answer = solution(80,new int[][] {{80, 20}, {50, 40}, {30, 10}});
		System.out.println(answer);
	}
	public static int solution(int k, int[][] dungeons) {
		int answer = -1;
		int dcount = dungeons.length;
		
		answer = permutation(0,dcount,new boolean[dcount],new int[dcount],dungeons,k);
		
		return answer;
	}

	public static int permutation(int current, int maxCount, boolean[] visit, int[] order,int[][] dungeons, int k) {
		if (current == maxCount) {
			// 정해진 순서로 던전 순회
			int count = 0;
			for (int i = 0 ;i<maxCount;i++) {
				if (k>=dungeons[order[i]][0]) { // 최소 체력 만족 시 탐사
					count++;
					k-= dungeons[order[i]][1];
				}
			}
			return count;
		}
		
		int result = -1;
		for (int i = 0; i < maxCount; i++) {
			if (!visit[i]) {// 사용한 인덱스가 아닐경우
				visit[i] = true;
				order[current] = i;
				result = Math.max(result, permutation(current+1,maxCount,visit,order,dungeons,k));
				visit[i] = false;
			} else {
				continue;
			}

		}
		return result;
	}

}
