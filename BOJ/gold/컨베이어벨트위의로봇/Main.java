package gold.컨베이어벨트위의로봇;
// https://www.acmicpc.net/problem/20055

import java.io.*;
import java.util.*;

public class Main {
	static class Belt {
		int dur;
		Robot robot;

		public Belt(int dur) {
			this.dur = dur;
		}

		public void setRobot(Robot robot) {
			if (robot != null) {
				this.robot = robot;
				reduceDur();
			}
		}

		public void reduceDur() {
			if (--dur == 0) {
				destroyed++;
			}
		}
	}

	static class Robot {

	}

	static int N, K;
	static int destroyed = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		List<Belt> belts = new LinkedList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			int dur = Integer.parseInt(st.nextToken());
			belts.add(new Belt(dur));
		}

		int count = 0;

		while (destroyed < K) {
			// 1. 벨트 회전
			Belt back = belts.get(belts.size() - 1);
			belts.remove(belts.size() - 1);
			belts.add(0, back);
			count++;
			// 2. 로봇 움직임
			for (int i = N - 1; i >= 0; i--) {
				Belt current = belts.get(i);
				Belt next = belts.get(i + 1);
				if (i == N - 1)
					current.robot = null;
				// 한칸씩 전진
				if (next.dur > 0 && next.robot == null) {
					next.setRobot(current.robot);
					current.robot = null;
					if (i == N-2)
						next.robot = null;
				}
			}
			// 3. 로봇 올리기
			Belt front = belts.get(0);
			if (front.dur > 0 && front.robot == null) {
				front.setRobot(new Robot());
			}

			// 4. 벨트 체크
		}
		System.out.println(count);
	}

}
