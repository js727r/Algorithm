package 구슬탈출;

// https://www.acmicpc.net/problem/13460
import java.io.*;
import java.util.*;

public class Main {
	static class Position {
		int r, c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public boolean equals(Position obj) {
			return this.r == obj.r && this.c == obj.c;
		}
	}

	static class Ball extends Position {
		int moveCount = 0;

		public Ball(int r, int c) {
			super(r, c);
		}
	}

	static class Capture {
		Ball R, B;
		int count;
		int prevDir;

		public Capture(Ball R, Ball B, int count, int prevDir) {
			this.R = new Ball(R.r, R.c);
			this.B = new Ball(B.r, B.c);
			this.count = count;
			this.prevDir = prevDir;
		}
	}

	static int N, M;
	static Ball R, B;
	static Position O;
	static char map[][];
	static int dr[] = { 0, -1, 1, 0 };
	static int dc[] = { -1, 0, 0, 1 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char symbol = map[i][j] = line.charAt(j);
				switch (symbol) {
				case 'R':
					R = new Ball(i, j);
					break;
				case 'B':
					B = new Ball(i, j);
					break;
				case 'O':
					O = new Position(i, j);
					break;
				}
			}
		}
		// bfs 진행
		// 왼오위아래 순
		// 매 순간 공의 위치를 기록해야함
		// 현재 순간 기록
		int min = Integer.MAX_VALUE;
		Capture current = new Capture(R, B, 0, -1);
		Queue<Capture> queue = new LinkedList<>();
		queue.offer(current);
		while (!queue.isEmpty()) {
			current = queue.poll();
			// 네 방향 기울여보고 움직임이 있으면 큐에 넣기
			for (int dir = 0; dir < 4; dir++) {
				// 아까 했던 방향의 반대로는 진행 안함
				if (current.prevDir + dir == 3)
					continue;

				// 공 굴리기
				Ball NR = new Ball(current.R.r, current.R.c);
				Ball NB = new Ball(current.B.r, current.B.c);
				move(dir, NR, true);
				move(dir, NB, true);


				// 만약 골인된 구슬중
				// 파란공이 있다면 안됨
				if (NB.equals(O))
					continue;
				// 빨간구슬만일 경우 기록
				if (NR.equals(O)) {
					min = Math.min(min, current.count + 1);
					continue;
				}
				// 같은 위치면 조정
				if (NR.equals(NB)) {
					if (NR.moveCount > NB.moveCount)
						move(3 - dir, NR, false);
					else
						move(3 - dir, NB, false);
				}
				// 구슬에 움직임이 있었는지 체크
				// 움직임이 없었으면 큐에 넣지 않음
				if (NR.moveCount + NB.moveCount == 0)
					continue;
				// 현재 상황 큐에 넣기
				if (current.count + 1 < 10)
					queue.offer(new Capture(NR, NB, current.count + 1, dir));
			}
		}
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	public static void move(int dir, Ball ball, boolean recursive) {
		int nr = ball.r + dr[dir];
		int nc = ball.c + dc[dir];
		if (isValid(nr, nc)) {
			if (map[nr][nc] != '#') {
				ball.r = nr;
				ball.c = nc;
				if (recursive && map[nr][nc] != 'O') {
					ball.moveCount++;
					move(dir, ball, recursive);
				} else {
					ball.moveCount--;
				}

			}
		}
	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= M)
			return false;
		return true;
	}
}
