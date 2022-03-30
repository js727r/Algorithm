package gold.뱀;
// https://www.acmicpc.net/problem/3190

import java.io.*;
import java.util.*;

public class Main {

	static class Position {
		int r, c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class SnakeBody extends Position {
		SnakeBody front;
		SnakeBody back;

		public SnakeBody(int r, int c, SnakeBody front) {
			super(r, c);
			map[r][c] = -1;
			this.front = front;
			this.back = null;
		}

		public SnakeBody(int r, int c, SnakeBody front, SnakeBody back) {
			this(r, c, front);
			this.back = back;
		}

		public boolean equals(SnakeBody obj) {
			return this.r == obj.r && this.c == obj.c;
		}
	}

	static class Snake {
		SnakeBody head;
		SnakeBody tail;
		int len;

		public Snake() {
			head = new SnakeBody(1, 1, null, null);
			tail = null;
			len = 1;
		}

		public boolean move() {
			int mr = head.r + dr[dir];
			int mc = head.c + dc[dir];
			if (!isValid(mr, mc))
				return false;


			// 머리 이동후 중간몸통 생성
			SnakeBody mid = new SnakeBody(head.r, head.c, head, head.back);
			if (len > 1)
				head.back.front = mid;
			else
				tail = mid;
			head.back = mid;
			head.r = mr;
			head.c = mc;
			// 사과를 먹은게 아니라면 꼬리 삭제
			if (map[mr][mc] != 1) {
				
				map[tail.r][tail.c] = 0;
				if (len > 1)
					tail = tail.front;
				else
					tail = null;
			} else {
				len++;
				K--;
			}
			map[mr][mc] = -1;
			return true;

		}

		public void changeDirection(char command) {
			switch (command) {
			case 'L':
				dir = (dir + 3) % 4;
				break;
			case 'D':
				dir = (dir + 1) % 4;
				break;
			}
		}
	}

	static class Command {
		int time;
		char command;

		public Command(int time, char command) {
			super();
			this.time = time;
			this.command = command;
		}

	}

	public static boolean isValid(int r, int c) {
		if (r <= 0 || c<= 0 || r > N || c > N || map[r][c] == -1)
			return false;
		return true;
	}

	static int N, K, L;
	static int[][] map;
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };
	static int dir = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		Queue<Command> queue = new LinkedList<>();
		for (int i = 0; i < K; i++) {
			String tokens[] = br.readLine().split(" ");
			int r = Integer.parseInt(tokens[0]);
			int c = Integer.parseInt(tokens[1]);
			map[r][c] = 1;
		}
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			String tokens[] = br.readLine().split(" ");
			int t = Integer.parseInt(tokens[0]);
			char c = tokens[1].charAt(0);
			queue.offer(new Command(t, c));
		}
		Snake snake = new Snake();
		int time = 0;
		Command next = queue.poll();
		while (snake.move()) {
			if (++time == next.time) {
				snake.changeDirection(next.command);
				if (!queue.isEmpty()) {
					next = queue.poll();
				}
			}
		}
		System.out.println(++time);
	}

}
