package level3.파괴되지않은건물;

public class Solution {

	public static void main(String[] args) {
		int answer = solution(new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}, new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}} );
		System.out.println(answer);
		answer = solution(new int[][] {{1,2,3},{4,5,6},{7,8,9}}, new int[][] {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}} );
		System.out.println(answer);
	}

	public static int solution(int[][] board, int[][] skill) {
		int N = board.length; // 행
		int M = board[0].length; // 열
		int answer = 0;
		int[][] accSum = new int[N+1][M+1];

		for (int i = 0; i < skill.length; i++) {
			int dmg = skill[i][0] == 1 ? -skill[i][5] : skill[i][5];
			int r1 = skill[i][1];
			int c1 = skill[i][2];
			int r2 = skill[i][3];
			int c2 = skill[i][4];

			accSum[r1][c1] += dmg;
			accSum[r1][c2+1] -= dmg;
			accSum[r2+1][c1] -= dmg;
			accSum[r2+1][c2+1] += dmg;

		}

		// 열방향 진행
		for (int c = 0; c < M; c++) {
			for (int r = 0; r < N; r++) {
				accSum[r][c+1] += accSum[r][c];
			}
		}
		// 행방향 진행
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				accSum[r + 1][c] += accSum[r][c];
			}
		}
		
		for(int r = 0; r<N;r++) {
			for (int c = 0; c<M;c++) {
				board[r][c] += accSum[r][c];
				if (board[r][c] > 0) {
					answer++;
				}
			}
		}

		return answer;
	}

}
