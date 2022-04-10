package gold.로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14503

public class Main {
	static int N, M;
	static int R, C, d;
	static int[][] map;
	static boolean[][] cleaned;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };
	static int cleanCount = 0;

	static class Cleaner {
		int r, c;

		public Cleaner(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public void clean() {
			if (!cleaned[r][c])
				cleanCount++;

			cleaned[r][c] = true;
		}

		public void turnLeft() {
			d = (d + 3) % 4;
		}

		public boolean shouldCleanFront() {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1)
				return false;

			return !cleaned[nr][nc];
		}

		public void moveFront() {
			r += dr[d];
			c += dc[d];
//			System.out.printf("move to (%d,%d)\n",r,c);
		}

		public boolean moveBack() {
			int nr = r - dr[d];
			int nc = c - dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1)
				return false;

			r = nr;
			c = nc;
			
//			System.out.printf("move to (%d,%d)\n",r,c);
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cleaned = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
			}
		}

		Cleaner cleaner = new Cleaner(R, C);
		while (true) {
			// 현위치 청소
			cleaner.clean();
			// 왼쪽 회전
			int turnCount = 0;
			// 바라보는 방향이 청소되지 않았으면 전진
			boolean tmp = false;
			do {
				cleaner.turnLeft();
				tmp = cleaner.shouldCleanFront();
				turnCount++;
			} while (!tmp && turnCount < 4);
			if (turnCount <= 4 && tmp)
				cleaner.moveFront();
			else if (!cleaner.moveBack()) {
				break;
			}
		}
		System.out.println(cleanCount);
	}

}
