package level2.양궁대회;

import java.util.Arrays;

class Solution {
	static int maxScore = 0;
	static int[] lionShot = new int[11];

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 })));

	}

	public static int[] solution(int n, int[] info) {
		makeOverlabCombination(n, info, new int[11], 0, 0);
		if (maxScore == 0) {
			return new int[] { -1 };
		} else {
			return lionShot;
		}
	}

	// n개의 화살을 10곳에 놓는 중복 조합 구하기
	static void makeOverlabCombination(int n, int[] apeach, int[] lion, int current, int start) {
		if (n == current) {
			int apeachScore = 0;
			int lionScore = 0;
			for (int i = 0; i <= 10; i++) {
				if (apeach[i] > 0 || lion[i] > 0) {
					if (apeach[i] >= lion[i])
						apeachScore += 10 - i;
					else
						lionScore += 10 - i;
				}

			}
			int diff = lionScore - apeachScore;
			if (maxScore < diff) {
				maxScore = diff;
				lionShot = Arrays.copyOf(lion, 11);
			} else if (maxScore == diff) {
				// 가장 낮은 점수를 많이 맞혔는지 계산
				for (int i = 10; i >= 0; i--) {
					if (lionShot[i] < lion[i]) {
						lionShot = Arrays.copyOf(lion, 11);
						break;
					} else if (lionShot[i] > lion[i]) {
						break;
					}
				}
			}

		} else {
			for (int i = start; i <= 10; i++) {
				lion[i]++;
				makeOverlabCombination(n, apeach, lion, current + 1, i);
				lion[i]--;
			}
		}
	}
}