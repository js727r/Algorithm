package gold.주사위;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int dice[] = new int[6];
		int temp[] = new int[6];
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		// 주사위의 show table 계산
		long[] show = new long[6 + 1]; // show[n] : n개의 인접한 면의 최솟값
		Arrays.fill(show, Integer.MAX_VALUE);

		// E A B F 기준 계산
		for (int i = 0; i < 4; i++) {
			calcShow(dice, show);

			// 라인 밀기
			temp = Arrays.copyOf(dice, 6);
			dice[4] = temp[0];
			dice[0] = temp[1];
			dice[1] = temp[5];
			dice[5] = temp[4];
		}

		// C, D 기준 계산

		// C 기준
		temp = Arrays.copyOf(dice, 6);
		dice[0] = temp[2];
		dice[2] = temp[5];
		dice[5] = temp[3];
		dice[3] = temp[0];
		calcShow(dice, show);

		// D 기준
		dice[0] = temp[3];
		dice[3] = temp[5];
		dice[5] = temp[2];
		dice[2] = temp[0];
		calcShow(dice, show);

		if (N == 1) {
			long answer = 0;
			long max = dice[0];
			for (int v : dice) {
				answer += v;
				max = Math.max(max, v);
			}

			System.out.println(answer - max);
		} else {
			long cube[] = new long[4]; // 보이는 면의 수에 따른 최솟값
			long base;// 중간층 구하기
			// 꼭짓점 : show[2]*4;
			cube[2] = show[2] * 4;
			// 모서리 : show[1]*(N-2)*4;
			cube[1] = show[1] * (N - 2) * 4;
			base = cube[1] + cube[2];

			long top;// 최상층 구하기
			// 꼭짓점 : show[3]*4;
			cube[3] = show[3] * 4;
			// 모서리 : show[2]*(N-2)*4;
			cube[2] = show[2] * (N - 2) * 4;
			// 윗면 : show[1]*(N-2)^2;
			cube[1] = show[1] * (N - 2) * (N - 2);

			top = cube[1] + cube[2] + cube[3];

			// 결과 : 중간층*(N-1) + 최상층
			System.out.println(base * (N - 1) + top);
		}

	}

	static void calcShow(int[] dice, long[] show) {
		int calc[] = new int[4];
		calc[1] = dice[0];
		show[1] = Math.min(show[1], calc[1]);
		
		int queue[] = new int[4];
		queue[0] = dice[1];
		queue[1] = dice[2];
		queue[2] = dice[4];
		queue[3] = dice[3];
		// 기준숫자 돌리기
		// show[1~3] 최소값 계산 + 기준값 포함
		for (int i = 0; i < 4; i++) {
			calc[2] = calc[1]+queue[0];
			calc[3] = calc[2]+queue[1];
			
			for(int n = 2;n<=3;n++)
				show[n] = Math.min(show[n], calc[n]);
			
			
			// 회전
			int tmp = queue[0];
			for(int n = 0;n<3;n++)
				queue[n] = queue[n+1];
			queue[3] = tmp;
		}

	}

}
