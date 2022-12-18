package BOJ.gold.선분교차2;
// https://www.acmicpc.net/problem/17387

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Coord[] coords = new Coord[4];

        // 입력
        String[] tokens = br.readLine().split(" ");
        coords[0] = new Coord(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        coords[1] = new Coord(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));

        tokens = br.readLine().split(" ");
        coords[2] = new Coord(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        coords[3] = new Coord(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));

        // (x1, y1) -> (x2, y2) -> (x3, y3) 진행방향이 시계방향인지 반시계방향인지 판별
        // (x1, y1) -> (x2, y2) -> (x4, y4) 진행방향이 시계방향인지 반시계방향인지 판별
        // 두 방향이 다른 경우에만 교차 or 한 점이 선분 위에 있을 때
        long firstCheck = Coord.getClockWiseDirection(coords[0], coords[1], coords[2])
                * Coord.getClockWiseDirection(coords[0], coords[1], coords[3]);
        // (x3, y3) -> (x4, y4) -> (x1, y1) 진행방향이 시계방향인지 반시계방향인지 판별
        // (x3, y3) -> (x4, y4) -> (x2, y2) 진행방향이 시계방향인지 반시계방향인지 판별
        // 두 방향이 다른 경우에만 교차 or 한 점이 선분 위에 있을 때
        long secondCheck = Coord.getClockWiseDirection(coords[2], coords[3], coords[0])
                * Coord.getClockWiseDirection(coords[2], coords[3], coords[1]);

        if (firstCheck < 0 && secondCheck < 0) {
            System.out.println(1);
        } else {
            if (firstCheck == 0 && secondCheck == 0) {
                // 원점으로부터 거리가 작은 순으로
                if (coords[0].getDistance() > coords[1].getDistance()) swap(coords[0],coords[1]);
                if (coords[2].getDistance() > coords[3].getDistance()) swap(coords[2],coords[3]);
                if (coords[0].getDistance() <= coords[3].getDistance()
                && coords[2].getDistance() <= coords[1].getDistance()) {
                    System.out.println(1);
                    return;
                }
                System.out.println(0);
            }
        }
    }

    public static void swap(Coord a, Coord b) {
        Coord temp = new Coord(a.x,a.y);
        a = b;
        b = temp;
    }
    static class Coord {
        public long x, y;

        public Coord(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public static long getClockWiseDirection(Coord src, Coord mid, Coord dst) {
            Vector from = new Vector(src, mid);
            Vector to = new Vector(mid, dst);
            return Vector.getClockWiseDirection(from, to);
        }

        public float getDistance() {
            return x*x+y*y;
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
            return (int) Math.signum(from.x * to.y - to.x * from.y);
        }
    }
}
