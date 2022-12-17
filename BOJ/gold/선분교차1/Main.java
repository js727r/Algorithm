package BOJ.gold.선분교차1;
// https://www.acmicpc.net/problem/17386

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Coord[] coords = new Coord[4];

        // 입력
        String[] tokens = br.readLine().split(" ");
        coords[0] = new Coord(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));
        coords[1] = new Coord(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));

        tokens = br.readLine().split(" ");
        coords[2] = new Coord(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));
        coords[3] = new Coord(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));

        // (x1, y1) -> (x2, y2) -> (x3, y3) 진행방향이 시계방향인지 반시계방향인지 판별
        // (x1, y1) -> (x2, y2) -> (x4, y4) 진행방향이 시계방향인지 반시계방향인지 판별
        // 두 방향이 다른 경우에만 교차
        long firstCheck = Coord.getClockWiseDirection(coords[0], coords[1], coords[2])
                * Coord.getClockWiseDirection(coords[0], coords[1], coords[3]) < 0 ? 1 : 0;
        // (x3, y3) -> (x4, y4) -> (x1, y1) 진행방향이 시계방향인지 반시계방향인지 판별
        // (x3, y3) -> (x4, y4) -> (x2, y2) 진행방향이 시계방향인지 반시계방향인지 판별
        // 두 방향이 다른 경우에만 교차
        long secondCheck = Coord.getClockWiseDirection(coords[2], coords[3], coords[0])
                * Coord.getClockWiseDirection(coords[2], coords[3], coords[1]) < 0 ? 1 : 0;

        // 1차 검증값과 2차 검증값이 같은 경우에만 교차
        System.out.println(firstCheck*secondCheck);

    }

    static class Coord {
        public long x, y;

        public Coord(long x, long y) {
            this.x = x;
            this.y = y;
        }
        public static long getClockWiseDirection(Coord src, Coord mid, Coord dst) {
            Vector from = new Vector(src,mid);
            Vector to = new Vector(mid,dst);
            return Vector.getClockWiseDirection(from, to);
        }
    }

    static class Vector extends Coord {
        public Vector(long x, long y) {
            super(x, y);
        }

        public Vector(Coord from, Coord to) {
            super(to.x - from.x, to.y - from.y);
        }

        /**
         * 두 벡터가 이루는 방향이 반시계 혹은 시계방향인지 리턴
         *
         * @param from
         * @param to
         * @return 반시계 방향인 경우 -1 리턴. 시계 방향인 경우 1 리턴. 직선상에 있는 경우 0 리턴
         */
        public static long getClockWiseDirection(Vector from, Vector to) {
            return (int)Math.signum(from.x * to.y - to.x * from.y);
        }
    }
}
