package silver.silver1.상어초등학교;
// https://www.acmicpc.net/problem/21608

import java.util.*;
import java.io.*;

public class 상어초등학교 {
	static class Position implements Comparable<Position> {
		int r, c;
		int love;
		int vacancy;

		public Position(int r, int c, int love, int vacancy) {
			super();
			this.r = r;
			this.c = c;
			this.love = love;
			this.vacancy = vacancy;
		}

		@Override
		public int compareTo(Position o) {
			if (this.love == o.love) {
				if (this.vacancy == o.vacancy) {
					if (this.r == o.r)
						return this.c - o.c;
					else
						return this.r - o.r;
				} else {
					return o.vacancy - this.vacancy;
				}
			} else {
				return o.love - this.love;
			}
		}

		@Override
		public String toString() {
			return String.format("(%d,%d),(%d,%d)", r, c, love, vacancy);
		}
	}

	static int N;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int loveInOrder[][] = new int[N * N][5];
		int love[][] = new int[N*N+1][5];
		int space[][] = new int[N][N];
		StringTokenizer st;
		// 입력
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int input[] = new int[5];
			for (int j = 0; j < 5; j++) {
				input[j] = Integer.parseInt(st.nextToken());
			}
			loveInOrder[i] = input;
			love[input[0]] = input;
		}

		// 자리배치
		for (int k = 0; k < N * N; k++) { // 입력받은 순서대로 학생 배치
			PriorityQueue<Position> spot = new PriorityQueue<>();
			// 1. 모든 공간의 탐색으로 후보공간 넣기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 2. 빈 공간인지 체크
					if (space[r][c] != 0)
						continue;

					// 이 위치에 인접한 좋아하는 사람, 빈 공간의 수 추가
					int lover = 0;
					int vacancy = 0;
					for (int d = 0; d < 4; d++) { // 네 방향 체크
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (isValid(nr, nc)) { // 유효한 좌표면 체크
							if (space[nr][nc] == 0) { // 인접 위치가 비었으면
								vacancy++;
							} else {
								for (int l = 1; l <= 4; l++) { // 좋아하는 사람인지 체크
									// 체크
									if (space[nr][nc] == loveInOrder[k][l])
										lover++;
								}
							}
						}
					}
					spot.add(new Position(r, c, lover, vacancy));
				}
			}
			// 가장 우선순위의 위치에 학생 앉히기
			Position best = spot.poll();
			space[best.r][best.c] = loveInOrder[k][0];

		}

		// 만족도 계산
		int totalSatis = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int satis = 0;
				int student = space[r][c];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (isValid(nr, nc)) {

						for (int l = 1; l <= 4; l++) { // 좋아하는 사람인지 체크
							// 체크
							if (space[nr][nc] == love[student][l]) {
								satis++;
								break;
							}
						}

					}
				}
				totalSatis += Math.pow(10, satis-1);
			}
		}
		System.out.println(totalSatis);

	}

	static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N)
			return false;
		return true;
	}

}
