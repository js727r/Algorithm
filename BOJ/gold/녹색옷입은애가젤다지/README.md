# 아이디어

1. 문제로 제시된 배열의 각 지점(r,c)를 정점으로 하는 방향 그래프로 생각한다.
2. (r,c)의 값이 이 정점으로 오는 weight값이라고 생각한다.
3. 각 정점은 상하좌우 4방향으로 인접한 정점과 연결되어 있다.
4. 이 그래프로 출발지부터 목적지까지 Dijkstra 알고리즘을 통해 최소비용을 계산한다.

# 풀이

```java
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
```
