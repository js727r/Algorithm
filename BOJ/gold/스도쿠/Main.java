package gold.스도쿠;
// https://www.acmicpc.net/problem/2580

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int sdk[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sdk = new int[9][9];
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sdk[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bt(0, 0);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sdk.length; i++) {
			for (int j = 0; j < sdk.length; j++) {
				sb.append(sdk[i][j]).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		System.out.println(sb);

	}

	public static boolean bt(int r, int c) {
		if (!isValid(r, c))
			return true;

		if (sdk[r][c] == 0) {
			for (int n = 1; n <= 9; n++) {
				sdk[r][c] = n;
				if (!isWrong(r, c)) {
					if (c < 8) {
						if (bt(r, c + 1))
							return true;
							
					} else {
						if (bt(r+1, 0))
							return true;
					}
				}
			}
			sdk[r][c] = 0;
			return false;

		} else {
			if (c < 8)
				return bt(r, c + 1);
			else
				return bt(r + 1, 0);
		}
	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= 9 || c >= 9)
			return false;
		return true;
	}

	// r, c에 놓은 숫자가 룰에 맞는지 판단
	public static boolean isWrong(int r, int c) {
		// 가로줄 검사
		boolean[] used = new boolean[10];
		for (int i = 0; i < 9; i++) {
			if (sdk[r][i] == 0)
				continue;
			if (used[sdk[r][i]])
				return true;
			used[sdk[r][i]] = true;

		}
		// 세로줄 검사
		used = new boolean[10];
		for (int i = 0; i < 9; i++) {
			if (sdk[i][c] == 0)
				continue;
			if (used[sdk[i][c]])
				return true;
			used[sdk[i][c]] = true;
		}
		// 블록 검사
		used = new boolean[10];
		int br = (r / 3) * 3;
		int bc = (c / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sdk[br + i][bc + j] == 0)
					continue;
				if (used[sdk[br + i][bc + j]])
					return true;

				used[sdk[br + i][bc + j]] = true;
			}
		}
		return false;
	}
}
