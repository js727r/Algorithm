package gold.치즈;

import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int map[][];
	static int cheese;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	static class Position {
		int r, c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cheese = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheese++;
				}
			}

		}

		// 0,0 에서 BFS, cheese > 0이면 반복
		int time = 0;
		int lastCheese = 0;
		while (cheese > 0) {
			lastCheese = cheese;
			time++;
			// BFS
			Queue<Position> queue = new LinkedList<>();
			queue.offer(new Position(0, 0));

			while (!queue.isEmpty()) {
				Position current = queue.poll();
				int r = current.r;
				int c = current.c;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (isValid(nr, nc)) {
						if (map[nr][nc] > -time && map[nr][nc] <= 0) { // 다음지점 큐에 넣기
							queue.offer(new Position(nr, nc));
						} else if (map[nr][nc] == 1) { // 치즈 증발
							cheese--;
						}
						map[nr][nc] = -time;
					}
				}
			}
		}
		System.out.println(time);
		System.out.println(lastCheese);
	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}
