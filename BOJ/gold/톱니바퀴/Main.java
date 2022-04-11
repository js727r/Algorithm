package gold.톱니바퀴;
// https://www.acmicpc.net/problem/14891
import java.util.*;
import java.io.*;

public class Main {

	
	static class Gear {
		/**
		 * 0 : N, 1 : S
		 */
		boolean gear[];
		int top;
		int num;
		public Gear(int num, String gearString) {
			this.num = num;
			gear = new boolean[8];
			for (int i = 0; i < 8; i++) {
				gear[i] = gearString.charAt(i) == '0' ? false : true;
			}
			
			this.top = 0;
		}
		
		void Rotate(boolean clockwise, int callFrom) {
			// 좌우 연동
			if (isConnectedToLeft() && callFrom != -1)
				gears[num-1].Rotate(!clockwise, 1);
			if (isConnectedToRight() && callFrom != 1)
				gears[num+1].Rotate(!clockwise, -1);
			
			if (clockwise) {	
				top = (top+7)%8;
			} else {
				
				top = (top+1)%8;
			}
		}
		
		boolean isConnectedToLeft() {
			if (num == 0)
				return false;
			return this.getLeft() != gears[num-1].getRight();
		}
		boolean isConnectedToRight() {
			if (num == 3)
				return false;
			return this.getRight() != gears[num+1].getLeft();
		}
		
		boolean getRight() {
			return gear[(top+2)%8];
		}
		boolean getLeft() {
			return gear[(top+6)%8];
		}
		boolean getTop() {
			return gear[top];
		}

		@Override
		public String toString() {
			return "Gear [gear=" + Arrays.toString(gear) + ", top=" + top + ", num=" + num + "]";
		}
		
	}
	static int K;
	static Gear[] gears;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gears = new Gear[4];
		for (int i = 0; i < 4; i++) {
			String gearStr = br.readLine();
			gears[i] = new Gear(i, gearStr);
		}
		// 회전 입력
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken())-1;
			boolean clockwise = Integer.parseInt(st.nextToken()) == 1 ? true : false;
			gears[num].Rotate(clockwise, 0);
			
		}
		// 점수계산
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (gears[i].getTop()) {
				score += 1<<i;
			}
		}
		System.out.println(score);
	}

}
