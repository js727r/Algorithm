package level2.컬러링북;

import java.util.*;

class Solution {
	static class Position {
		int r, c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int dr[] = { 0, 0, 1, -1 };
	static int dc[] = { 1, -1, 0, 0 };
	public static void main(String[] args) {
		Solution sol = new Solution();
		int picture[][] = {{1,1,1,0},{1,2,2,0},{1,0,0,1},{0,0,0,1},{0,0,0,3},{0,0,0,3}};
		System.out.println(Arrays.toString(sol.solution(6, 4, picture)));
	}
	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		boolean[][] visit = new boolean[m][n];
		for (int i = 1; i <= m * n; i++) {
			int r = (i - 1) / n;
			int c = (i - 1) % n;
			if (visit[r][c] || picture[r][c] == 0)
				continue; // 방문한경우 제외
			numberOfArea++;
			// bfs 탐색
			Position start = new Position(r, c);
			Queue<Position> queue = new LinkedList<>();
			queue.offer(start);
			visit[r][c] = true;
			int size = 0;
			while (!queue.isEmpty()) {
				Position current = queue.poll();
				size++;
				for (int d = 0; d < 4; d++) {
					int nr = current.r + dr[d];
					int nc = current.c + dc[d];
					if (nr < 0 || nc < 0 || nr >= m || nc >= n)
						continue;
					if (!visit[nr][nc] && picture[nr][nc] == picture[current.r][current.c]) {
						visit[nr][nc] = true;
						queue.offer(new Position(nr, nc));
					}
				}
			}
			maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}
}