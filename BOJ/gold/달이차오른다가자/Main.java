package gold.달이차오른다가자;

// https://www.acmicpc.net/problem/1194
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int dr[] = { 0, 0, 1, -1 };
    static int dc[] = { 1, -1, 0, 0 };
    static boolean visit[][][];

    static class Position {
        int r, c;
        int time;
        int key;

        public Position(int r, int c, int time, int key) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.key = key;
        }

        public void obtainKey(char key) {
            this.key |= 1 << (key - 'a');
        }

        public boolean hasKey(char door) {
            if ((key & (1 << (door - 'A'))) != 0)
                return true;
            return false;
        }

        @Override
        public String toString() {
            return "Position [r=" + r + ", c=" + c + ", time=" + time + ", key=" + key + "]";
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Position start = null;
        map = new char[N][M];
        visit = new boolean[N][M][64];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0')
                    start = new Position(i, j, 0, 0);
            }
        }

        Queue<Position> queue = new LinkedList<Position>();
        visit[start.r][start.c][0] = true;
        queue.offer(start);

        int time = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if (isValid(nr, nc)) {
                    if (visit[nr][nc][current.key]) // 같은 지점 방문 체크
                        continue;
                    // 벽
                    char next = map[nr][nc];
                    if (next == '#')
                        continue;
                    Position nextPosition = null;
                    if (next == '1') {
                        time = Math.min(time, current.time + 1);
                        continue;
                    } else if (next == '.' || next == '0') {
                        nextPosition = new Position(nr, nc, current.time + 1, current.key);
                    } else if (isKey(next)) { // 키 지점 방문시
                        // 같은 키 습득시
                        nextPosition = new Position(nr, nc, current.time + 1, current.key);
                        nextPosition.obtainKey(next);

                    } else { // 문일 경우
                        if (current.hasKey(next))
                            nextPosition = new Position(nr, nc, current.time + 1, current.key);
                        else
                            continue;

                    }
                    visit[nr][nc][nextPosition.key] = true;
                    queue.offer(nextPosition);
                }
            }
        }
        if (time == Integer.MAX_VALUE)
            time = -1;
        System.out.println(time);
    }

    private static boolean isValid(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= M)
            return false;
        return true;
    }

    static boolean isKey(char letter) {
        if (letter >= 'a' && letter <= 'f')
            return true;
        return false;
    }

}
