package gold.주사위굴리기;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int map[][];
	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { 1, -1, 0, 0 };
	static int[][] diceMap = { { 4, 3, 5, 2 }, { 4, 3, 5, 6 }, { 2, 5, 1, 6 }, { 5, 2, 1, 6 }, { 4, 3, 6, 1 },
			{ 4, 3, 2, 5 }, };

	static class Dice {
		int r, c;
		int top;
		int numbers[];

		public Dice(int r, int c) {
			this.r = r;
			this.c = c;
			top = 1;
			numbers = new int[6];
		}

		public void rollDice(int d) {
			d--;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (isValid(nr, nc)) {
				r = nr;
				c = nc;
				if (map[r][c] == 0) {
					map[r][c] = 7-top;
				} else {
					top = diceMap[top - 1][d];
					numbers[7 - top - 1] = map[r][c];
					
				}
				System.out.println(numbers[top-1]);
			}
		}

		public boolean isValid(int r, int c) {
			if (r < 0 || c < 0 || r >= N || c >= M)
				return false;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x, y;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		Dice dice = new Dice(x, y);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int command[] = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			command[i] = Integer.parseInt(st.nextToken());
			dice.rollDice(command[i]);
		}

	}

}
