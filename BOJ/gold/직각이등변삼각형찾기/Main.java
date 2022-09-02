package gold.직각이등변삼각형찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

// https://www.acmicpc.net/problem/2658
public class Main {
    static class Coord extends Comparable {
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

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

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
                System.out.printf("%d %d", p.r, p.c);
            }
        }

    }

    public static List<Coord> checkTriangle(int[][] arr, int count) {
        boolean qualified = true; // 유효한 삼각형인지 여부

        // 직각 이등변 삼각형을 띄는지 체크
        // 1의 개수가 1 + 3 + 5 + 7 + ... 로 나타나야 함
        if (!oneCountCheck()) return null;

        // 직각 부분의 꼭짓점을 찾기 -> 3x3 영역에 1의 개수가 4인 지점(없다면 종료)
        Coord top = findTop(arr);
        if (top == null) return null;

        // 밑변의 방향으로 추정되는 d값 찾기
        int d;
        for (d = 0; d < 4; d++) {
            int nr = top.r + dr[d];
            int nc = top.c + dc[d];
            if (arr[nr][nc] == 1)
                break;
        }
        // 해당 방향으로 1, 3, 5, 7... 개를 겁사하면서 1의 개수와 딱 떨어지는지 확인
        int checked = 0;
        for (int step = 1; checked <= count; step++) {
            for (int x = 0; x < step; x++) {
                if (d <= 1) {   // 양 옆으로 뻗어나가는 경우

                } else {
                    
                }
            }
        }


        return null;
    }

    public static boolean oneCountCheck() {
        return true;
    }

    public static Coord findTop(int[][] arr) {
        return null;
    }

    public List<Coord> scanFromTop(Coord top, int d) {
        return null;
    }
}
