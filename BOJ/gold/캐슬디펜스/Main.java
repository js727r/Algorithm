import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17135

public class Main {
	static int N, M, D, enemyCount = 0;
	static boolean[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = tokens[j].equals("1") ? true : false;
				if (map[i][j])
					enemyCount++;
			}
		}
		int tower[] = new int[M];
		Arrays.fill(tower, M - 3, M, 1);
		int max = 0;
		do {
			boolean[][] enemies = getCopy();
			int enemy = enemyCount;
			int totalKill = 0;
			while (enemy > 0) {
				// 타워 작동
				int kill = depense(enemies, tower);
				totalKill += kill;
				enemy -= kill;
				// 적 진군
				enemy -= attack(enemies);

			}
			max = Math.max(max, totalKill);
		} while (np(tower));
		System.out.println(max);
	}

	static class Position implements Comparable<Position> {
		int r, c;
		int d;

		public Position(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Position o) {
			if (this.d == o.d)
				return this.c - o.c;
			return this.d - o.d;
		}
	}

	static int dr[] = { 0, -1, 0 };
	static int dc[] = { -1, 0, 1 };

	static int depense(boolean[][] enemies, int[] tower) {
		Queue<Position> targets = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			if (tower[i] == 0)
				continue;

			// bfs탐색으로 적 하나 찾으면 탈출
			Queue<Position> queue = new LinkedList<>();
			Position start = new Position(N - 1, i, 1);
			queue.offer(start);
			while (!queue.isEmpty()) {
				Position current = queue.poll();
				if (current.d > D)
					break;
				int r = current.r;
				int c = current.c;
				if (enemies[r][c]) {
					targets.offer(current);
					break;
				}
				for (int d = 0; d < 3; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!isValid(nr, nc))
						continue;
					queue.offer(new Position(nr, nc, current.d + 1));
				}
			}
		}
		int kill = 0;
		for (Position p : targets) {
			if (enemies[p.r][p.c]) {
				enemies[p.r][p.c] = false;
				kill++;
			}
		}
		return kill;
	}

	static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= M)
			return false;
		return true;
	}

	static int attack(boolean[][] enemies) {
		int attack = 0;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (i == N - 1) {
					if (enemies[i][j])
						attack++;
				} else {
					enemies[i + 1][j] = enemies[i][j];
				}
			}
		}
		Arrays.fill(enemies[0], false);
		return attack;
	}

	static boolean[][] getCopy() {
		boolean[][] copy = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(map[i], M);
		}
		return copy;
	}

	static boolean np(int arr[]) {
		// 1. 바꿀지점 구하기
		int n = arr.length;
		int i = n - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;
		if (i == 0)
			return false;

		// 2. 바꿀 값 찾기
		int j = n - 1;
		while (arr[i - 1] >= arr[j])
			j--;

		// 3. 바꾸기
		swap(arr, i - 1, j);

		// 4. 교환위치 뒤부터 오름차순으로
		int k = n - 1;
		while (k > i)
			swap(arr, i++, k--);

		return true;
	}

	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
