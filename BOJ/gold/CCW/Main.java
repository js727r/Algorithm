package BOJ.gold.CCW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        Coord[] points = new Coord[3];
        for (int i = 0; i < 3; i++) {
            String[] coord = br.readLine().split(" ");
            points[i] = new Coord(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
        }

        Vector[] vectors = new Vector[2];
        // P1 -> P2 벡터
        vectors[0] = new Vector(points[0], points[1]);
        // P2 -> P3 벡터
        vectors[1] = new Vector(points[1], points[2]);
        // 벡터 외적 후 부호 확인하여 결과 리턴

        System.out.println((int)Math.signum(Vector.getCrossZ(vectors[0], vectors[1])));
    }

    static class Coord {
        public int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Vector extends Coord {
        public Vector(int x, int y) {
            super(x, y);
        }

        public Vector(Coord from, Coord to) {
            super(to.x - from.x, to.y - from.y);
        }

        /**
         *  2차원 벡터의 외적 연산 후 Z값 리턴
         * @param v1
         * @param v2
         * @return 외적한 결과 벡터의 Z값
         */
        public static int getCrossZ(Vector v1, Vector v2) {
            return v1.x*v2.y-v2.x*v1.y;
        }
    }

}
