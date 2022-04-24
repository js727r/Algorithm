package gold.배낭;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/12865
public class Main {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]); // 물건의 개수
		K = Integer.parseInt(tokens[1]); // 배낭의 무게
		int[][] items = new int[N][2];

		// 입력
		for (int i = 0; i < N; i++) {
			tokens = br.readLine().split(" ");
			items[i][0] = Integer.parseInt(tokens[0]);
			items[i][1] = Integer.parseInt(tokens[1]);
		}

		int dp[][] = new int[2][K + 1]; // prev, current 구분 / w무게만큼 남은 가방의 최적해
		int current = 1;
		int prev = 0;
		for (int i = 0; i < N; i++) { // 물건 0번부터 N-1번까지 고려하는 개수를 늘려간다
			int currentW = items[i][0]; // 현재 물건의 무게
			for (int w = 1; w <= K; w++) { // w만큼의 가방에 담는 경우
				// i번째 물건을 담는 절차를 수행한 후 남은 무게 구하기
				int rest = w; // 나머지 가방의 무게
				if (currentW <= w) {// 담을 수 있는 경우
					rest -= currentW; // 담고 난 후의 무게 설정

					// 나머지 무게로 얻을 수 있는 최적해에 현재 물건의 가치 더하기
					dp[current][w] = Math.max(dp[prev][rest] + items[i][1],dp[prev][w]);
				} else {
					dp[current][w] = dp[prev][rest];
				}
			}
			// dp배열 교체
			current = (current + 1) % 2;
			prev = (prev + 1) % 2;
		}
		System.out.println(dp[prev][K]);
	}
}
//1 2
//1 3
// -> 3
