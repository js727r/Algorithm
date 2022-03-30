package gold._2048Easy;
// https://www.acmicpc.net/problem/12100

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int max = 0;
	// 좌 상 하 우
	static int dr[] = { 0, -1, 1, 0 };
	static int dc[] = { -1, 0, 0, 1 };
	static int N;

	static class Value {
		int r, c;
		int val;

		public Value(int r, int c, int val) {
			super();
			this.r = r;
			this.c = c;
			this.val = val;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}

//		combination(0, new int[5]);
//		System.out.println(max);
		int dir = 1;
		if (dir== 0 || dir==3) {// 왼쪽, 오른쪽 밀기
			for (int r = 0; r < N; r++) { // 행우선 탐색
				if (dir == 0)
					tmpPush(map, r, 0, dir);
				else
					tmpPush(map, r, N - 1, dir);
			}
		} else {
			for (int c = 0; c < N; c++) { // 열우선 탐색
				if (dir == 1)
					tmpPush(map, 0, c, dir);
				else
					tmpPush(map, N - 1, c, dir);
			}
		}
		print(map);
		dir = 0;
		if (dir== 0 || dir==3) {// 왼쪽, 오른쪽 밀기
			for (int r = 0; r < N; r++) { // 행우선 탐색
				if (dir == 0)
					tmpPush(map, r, 0, dir);
				else
					tmpPush(map, r, N - 1, dir);
			}
		} else {
			for (int c = 0; c < N; c++) { // 열우선 탐색
				if (dir == 1)
					tmpPush(map, 0, c, dir);
				else
					tmpPush(map, N - 1, c, dir);
			}
		}
		print(map);
	}
	public static void print(int[][] copyMap) {
		StringBuilder sb = new StringBuilder();
		for (int c = 0; c < copyMap.length; c++) {
			for (int j = 0; j < copyMap.length; j++) {
				sb.append(copyMap[c][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void combination(int cnt, int[] arr) {
		if (cnt == 5) {
			// 방향 조합 구하기 완료, 배열에 저장된 순서로 최대값 계산
			int[][] copyMap = new int[N][N];

//			StringBuilder sb = new StringBuilder();
//			for (int is : arr)
//				sb.append(is).append(" ");

//			System.out.println(sb.toString());
			for (int i = 0; i < copyMap.length; i++) {
				copyMap[i] = Arrays.copyOf(map[i], N);
			}
			for (int i = 0; i < arr.length; i++) {
				
				int dir = arr[i];
				if (dir== 0 || dir==3)  {// 왼쪽, 오른쪽 밀기
					for (int r = 0; r < N; r++) { // 행우선 탐색
						if (dir == 0)
							tmpPush(copyMap, r, 0, dir);
						else
							tmpPush(copyMap, r, N - 1, dir);
					}
				} else {
					for (int c = 0; c < N; c++) { // 열우선 탐색
						if (dir == 1)
							tmpPush(copyMap, 0, c, dir);
						else
							tmpPush(copyMap, N - 1, c, dir);
					}
				}
//				print(copyMap);
			}

		} else {
			int[] current = Arrays.copyOf(arr, arr.length);
			for (int i = 0; i < 4; i++) {
				current[cnt] = i;
				combination(cnt + 1, current);
			}
		}
	}

	public static void tmpPush(int[][] copy, int r, int c, int dir) {
		Value tmp = null;
		Value v1 = null;
		Value v2 = null;
		boolean mergeTrigger = false;
		int nr = r, nc = c;
		while (((tmp = findValue(copy, nr, nc, 3 - dir)) != null)) {
			if (!mergeTrigger) {
				v1 = tmp;
				mergeTrigger = true;
			} else {
				v2 = tmp;
				// 두 값이 같다면 합치기
				if (v1.val == v2.val) {
					copy[v1.r][v1.c] *= 2;
					max = Math.max(max, copy[v1.r][v1.c]);
					copy[v2.r][v2.c] = 0;
					mergeTrigger = false;
				} else {
					v1 = v2;
				}
			}
			nr = tmp.r + dr[3 - dir];
			nc = tmp.c + dc[3 - dir];
		}
		// 합친 값 왼쪽으로 붙이기
		nr = r;
		nc = c;
		while (((tmp = findValue(copy, nr, nc, 3 - dir)) != null)) {
			v1 = findValue(copy, tmp.r + dr[dir], tmp.c + dc[dir], dir);
			copy[tmp.r][tmp.c] = 0;
			if (v1 == null) {
				copy[r][c] = tmp.val;
			} else {
				copy[v1.r + dr[3 - dir]][v1.c + dc[3 - dir]] = tmp.val;
			}
			nr = tmp.r + dr[3 - dir];
			nc = tmp.c + dc[3 - dir];
		}
	}

	public static Value findValue(int[][] copy, int r, int c, int d) {
		if (isValid(r, c)) {
			if (copy[r][c] > 0) { // 값 발견
				return new Value(r, c, copy[r][c]);
			} else { // 찾지 못하면 다음 방향으로 검사
				int nr = r + dr[d];
				int nc = c + dc[d];
				return findValue(copy, nr, nc, d);
			}
		} // 더 이상 없으면
		return null;
	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N)
			return false;
		return true;
	}

}
