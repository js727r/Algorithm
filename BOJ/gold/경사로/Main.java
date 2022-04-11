package gold.경사로;
// https://www.acmicpc.net/problem/14890
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, X;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		X = Integer.parseInt(input[1]);
		int map[][] = new int[N + 1][N + 1];
		int dp[][][] = new int[N][N][2];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				// x축 비교
				int pc = c - 1; // 행의 이전 좌표
				if (!isValid(r, pc)) { // 행의 처음값
					dp[r][c][0] = 1;
				} else { // 행의 두번째 값 이후
					int prev_h = map[r][pc];
					if (prev_h + 1 == map[r][c]) {// 이전 값과 비교하여 올라간 경우
						if (dp[r][pc][0] < X) // 이전 평지에 경사로 설치가 불가능하다면
							setInvalidRow(map, r); // 현재 행은 활주로 설치 불가능
						else
							dp[r][c][0] = 1;
					} else if (prev_h - 1 == map[r][c]) { // 이전 값보다 내려간경우
						if (dp[r][pc][0] < 0)
							setInvalidRow(map, r);
						else
							dp[r][c][0] = 1 - X;
					} else if (prev_h == map[r][c]) { // 높이가 같은경우
						dp[r][c][0] = dp[r][pc][0] + 1;
					} else { // 차이가 너무 많이 나면 에러
						setInvalidRow(map, r); // 현재 행은 활주로 설치 불가능
					}
				}
				// 마지막 값까지 확인
				if (dp[r][N - 1][0] < 0)
					setInvalidRow(map, r);

				// y축 비교, x축과 동일함
				int pr = r - 1;
				if (!isValid(pr, c)) {
					dp[r][c][1] = 1;
				} else { // 행의 두번째 값 이후
					int prev_h = map[pr][c];
					if (prev_h + 1 == map[r][c]) {
						if (dp[pr][c][1] < X)
							setInvalidColumn(map, c);
						else
							dp[r][c][1] = 1;
					} else if (prev_h - 1 == map[r][c]) {
						if (dp[pr][c][1] < 0)
							setInvalidColumn(map, c);
						else
							dp[r][c][1] = 1 - X;
					} else if (prev_h == map[r][c]) {
						dp[r][c][1] = dp[pr][c][1] + 1;
					} else {
						setInvalidColumn(map, c);
					}
				}
				// 마지막 값까지 확인
				if (dp[N - 1][c][1] < 0)
					setInvalidColumn(map, c);
			}
		}
		int validLine = 0;
		for (int i = 0; i < N; i++) {
			if (map[i][N] == 0)
				validLine++;
			if (map[N][i] == 0)
				validLine++;
		}
		System.out.println(validLine);

	}

	static void setInvalidRow(int[][] map, int r) {
		map[r][map.length - 1] = -1;
	}

	static void setInvalidColumn(int[][] map, int c) {
		map[map.length - 1][c] = -1;
	}

	static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N)
			return false;
		return true;
	}

}
