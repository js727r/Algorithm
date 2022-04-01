package gold.녹색옷입은애가젤다지;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/4485
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;

    static class Position implements Comparable<Position> {
        int r, c;
        int w;

        public Position(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Position o) {
            return this.w - o.w;
        }
    }

    static int dr[] = { 0, 0, 1, -1 };
    static int dc[] = { 1, -1, 0, 0 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        do {
            cnt++;
            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 다익스트라 알고리즘
            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = map[0][0];
            PriorityQueue<Position> pq = new PriorityQueue<>();
            pq.offer(new Position(0, 0, dp[0][0]));
            while (!pq.isEmpty()) {
                Position current = pq.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if (isValid(nr, nc)) {
                        int w = current.w + map[nr][nc];
                        if (dp[nr][nc] > w) {
                            dp[nr][nc] = w;
                            pq.offer(new Position(nr, nc, w));
                        }
                    }
                }
            }
            System.out.printf("Problem %d: %d\n", cnt, dp[N - 1][N - 1]);
        } while (N != 0);
    }

    private static boolean isValid(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= N)
            return false;
        return true;
    }
}
