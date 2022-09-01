package bronze.직사각형면적;
// https://www.acmicpc.net/problem/2669

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] markedArea = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String[] token = br.readLine().split(" ");
            int x1 = Integer.parseInt(token[0]);
            int y1 = Integer.parseInt(token[1]);
            int x2 = Integer.parseInt(token[2]);
            int y2 = Integer.parseInt(token[3]);
            marking(x1, y1, x2, y2);
        }

        int area = 0;
        for (int r = 1; r <= 100; r++) {
            for (int c = 1; c <= 100; c++) {
                if(markedArea[r][c]) area++;
            }
        }
        System.out.println(area);
    }

    public static void marking(int x1, int y1, int x2, int y2) {
        for (int c = x1; c < x2; c++) {
            for (int r = y1; r < y2; r++) {
                markedArea[r][c] = true;
            }
        }
    }
}
