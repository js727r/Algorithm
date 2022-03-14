package 연구소;

// https://www.acmicpc.net/problem/14502
import java.io.*;
import java.util.*;

public class Main {
	static int N, M, safe = 0;
	static int max = 0;
	static int map_origin[][];
	static int map[][];
	static int dr[] = { 0, 0, 1, -1 };
	static int dc[] = { 1, -1, 0, 0 };

	static class Position {
		int r, c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map_origin = new int[N][M];
		map = new int[N][M];
		List<Position> virus = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map_origin[i][j] = Integer.parseInt(st.nextToken());
				if (map_origin[i][j] == 2) {
					virus.add(new Position(i, j));
				}
				if (map_origin[i][j] == 0)
					safe++;
			}
		}

		// 벽을 3개 세우기(브루트 포스)
		// 1번 벽
		for (int i = 0; i < N * M - 2; i++) {
			initMap(); // 초기 맵 불러오기
			int r1 = i / M;
			int c1 = i % M;
			if (map[r1][c1] == 0) {
				// 2번 벽
				for (int j = i + 1; j < N * M - 1; j++) {
					initMap(); // 초기 맵 불러오기
					int r2 = j / M;
					int c2 = j % M;
					if (map[r2][c2] == 0) {
						// 3번 벽
						for (int k = j + 1; k < N * M; k++) {
							int r3 = k / M;
							int c3 = k % M;
							if (map[r3][c3] == 0) {
								// i,j,k로 벽 세우기
								initWall(r1, c1, r2, c2, r3, c3);
								// 바이러스 확산
								int currentSafe = safe-3;
								Queue<Position> queue = new LinkedList<>();
								for (Position v : virus) {
									queue.offer(v);
								}
								while (!queue.isEmpty()) {
									Position v = queue.poll();
									for (int d = 0; d < 4; d++) {
										int nr = v.r + dr[d];
										int nc = v.c + dc[d];
										if (isValid(nr, nc)) {
											if (map[nr][nc] == 0) {
												map[nr][nc] = 2;
												currentSafe--;
												queue.offer(new Position(nr, nc));
											}
										}
									}
								}
								max = Math.max(max, currentSafe);
								initMap(); // 초기 맵 불러오기
							}
						}
						initMap(); // 초기 맵 불러오기
					}
				}
				initMap(); // 초기 맵 불러오기
			}
		}
		System.out.println(max);
	}

	public static void initMap() {
		for (int i = 0; i < N; i++) // 초기 맵 불러오기
			map[i] = Arrays.copyOf(map_origin[i], M);
	}

	public static void initWall(int r1, int c1, int r2, int c2, int r3, int c3) {
		map[r1][c1] = 1;
		map[r2][c2] = 1;
		map[r3][c3] = 1;
	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= M)
			return false;
		return true;
	}

	public static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
