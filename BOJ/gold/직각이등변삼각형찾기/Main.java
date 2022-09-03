package gold.직각이등변삼각형찾기;
// https://www.acmicpc.net/problem/2658
/*
0000000000
1111000000
1110000000
1100000000
1000000000
0000000000
0000000000
0000000000
0000000000
0000000000

gg쳤음 위 케이스 통과 못함
이유 : 대각방향 진행할때 짝수번째 체크 비어버림
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class Coord implements Comparable {
        int r, c;

        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Object o) {
            Coord other = (Coord) o;
            if (this.r == other.r) return this.c - other.c;
            else return this.r - other.r;
        }
    }

    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[10][10];

        int count = 0;  // 1인 부분의 개수
        for (int r = 0; r < 10; r++) {  // 배열 입력받기
            String line = br.readLine();
            for (int c = 0; c < 10; c++) {
                arr[r][c] = line.charAt(c);
                if (arr[r][c] == '1') count++;
            }
        }

        List<Coord> points = checkTriangle(arr, count);   // 이등변 삼각형의 꼭짓점 구하기
        if (points == null) {   // 이등변 삼각형이 아닐경우
            // 0 출력
            System.out.println(0);
        } else {
            // 결과 출력
            Collections.sort(points);
            for (Coord p : points) {
                System.out.printf("%d %d\n", p.r + 1, p.c + 1);
            }
        }

    }

    // 직각 이등변 삼각형을 띄는지 체크
    public static List<Coord> checkTriangle(int[][] arr, int count) {
        if (!oneCountCheck(arr, count)) return null;

        // 직각 부분의 꼭짓점을 찾기 -> 3x3 영역에 1의 개수가 4인 지점(없다면 종료)
        // 근데 1 3개로 이루어진 삼각형일때는 다른 방법으로 구해야 함
        Coord top = findTop(arr, count);
        if (top == null) return null;

        // 밑변의 방향으로 추정되는 d값 찾기
        int d = getDirectionToBottom(arr, top.r, top.c, count);
        // 해당 방향으로 1, 3, 5, 7... 개를 겁사하면서 1의 개수와 딱 떨어지는지 확인
        int checked = 0;
        // 대칭점 좌표(밑변부터 높이를 따라 찍히는 좌표)
        int r = top.r;
        int c = top.c;

        Coord coord1 = new Coord(r, c);
        Coord coord2 = new Coord(r, c);

        if (d % 2 == 1) { // 대각선 방향 진행일 경우 아래 반복문에서 체크하지 못하는 부분 직접 확인
            // top 양 옆의 점 체크해주기
            int d1 = (7 + d) % 8;
            int d2 = (d + 1) % 8;
            coord1.r = r + dr[d1];
            coord1.c = c + dc[d1];
            coord2.r = r + dr[d2];
            coord2.c = c + dc[d2];
            if (!isValid(coord1.r, coord1.c) || !isValid(coord2.r, coord2.c) || arr[coord1.r][coord1.c] == '0' || arr[coord2.r][coord2.c] == '0')
                return null;
            checked += 2;
        }

        for (int step = 1; checked < count; step++) {
            if (!isValid(r, c) || arr[r][c] == '0') return null;

            checked++;
            for (int delta = 1; delta < step; delta++) {
                int d1 = (6 + d) % 8;
                int d2 = (d + 2) % 8;
                coord1.r = r + dr[d1] * delta;
                coord1.c = c + dc[d1] * delta;
                coord2.r = r + dr[d2] * delta;
                coord2.c = c + dc[d2] * delta;
                if (!isValid(coord1.r, coord1.c) || !isValid(coord2.r, coord2.c) || arr[coord1.r][coord1.c] == '0' || arr[coord2.r][coord2.c] == '0')
                    return null;

                checked += 2;

            }
            r += dr[d];
            c += dc[d];
        }
        if (checked != count) return null;
        List<Coord> result = new ArrayList<>();
        result.add(coord1);
        result.add(coord2);
        result.add(top);

        return result;
    }

    public static boolean oneCountCheck(int[][] arr, int count) {
        if (count <= 2) return false;

        boolean[][] visited = new boolean[10][10];
        int checked = 0;
        Queue<Coord> queue = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            int r = i / 10;
            int c = i % 10;
            if (arr[r][c] == '1') {
                queue.offer(new Coord(r, c));
                break;
            }
        }
        while (!queue.isEmpty()) {
            Coord current = queue.poll();
            for (int d = 0; d <= 6; d += 2) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if (isValid(nr, nc) && !visited[nr][nc] && arr[nr][nc] == '1') {
                    visited[nr][nc] = true;
                    queue.offer(new Coord(nr, nc));
                    ++checked;
                }
            }
        }
        return checked == count;
    }

    public static Coord findTop(int[][] arr, int count) {

        for (int i = 0; i < 100; i++) {
            int r = i / 10;
            int c = i % 10;
            if (arr[r][c] == '0') continue;

            if (count == 3) {
                if (get3x3OneCount(arr, r, c) == 3) {
                    for (int d = 1;d<8;d+= 2) {
                        int d1 = (7+d)%8;
                        int d2 = (d+1)%8;
                        int nr1 = r+dr[d1];int nc1 = c+dc[d1];
                        int nr2 = r+dr[d2];int nc2 = c+dc[d2];
                        if (!isValid(nr1,nc1) || !isValid(nr2,nc2)) continue;
                        if ((arr[nr1][nc1] & arr[nr2][nc2]) == '1') return new Coord(r,c);
                    }
                }
            } else if (count > 3) {
                if (get3x3OneCount(arr, r, c) == 4 && getDirectionToBottom(arr, r, c, count) != -1)
                    return new Coord(r, c);
            }
        }

        return null;
    }

    public static int getDirectionToBottom(int[][] arr, int r, int c, int count) {
        int d;
        for (d = 0; d < 8; d++) {
            int b = (7 + d) % 8;
            int m = d;
            int f = (d + 1) % 8;
            int r1 = r + dr[b];
            int c1 = c + dc[b];
            int r2 = r + dr[m];
            int c2 = c + dc[m];
            int r3 = r + dr[f];
            int c3 = c + dc[f];
            char one = '1';
            one &= isValid(r1, c1) ? arr[r1][c1] : '0';
            if (count > 3)
                one &= isValid(r2, c2) ? arr[r2][c2] : '0';
            one &= isValid(r3, c3) ? arr[r3][c3] : '0';
            if (one == '1') return d;
        }
        return -1;
    }

    public static int get3x3OneCount(int[][] arr, int r, int c) {
        int count = 0;
        if (isValid(r, c)) ++count;
        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isValid(nr, nc) && arr[nr][nc] == '1')
                count++;
        }
        return count;
    }

    public static boolean isValid(int r, int c) {
        if (r < 0 || c < 0 || r >= 10 || c >= 10) return false;
        return true;
    }
}
