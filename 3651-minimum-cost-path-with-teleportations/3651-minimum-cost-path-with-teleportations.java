import java.util.*;

class Solution {

    static class State {
        int r, c, t;
        long cost;

        State(int r, int c, int t, long cost) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.cost = cost;
        }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        long INF = Long.MAX_VALUE / 4;
        long[][][] dist = new long[m][n][k + 1];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(dist[i][j], INF);

        // Store all cells sorted by value
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells.add(new int[]{grid[i][j], i, j});
            }
        }
        cells.sort(Comparator.comparingInt(a -> a[0]));

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        dist[0][0][0] = 0;
        pq.offer(new State(0, 0, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            int r = cur.r, c = cur.c, t = cur.t;
            long cost = cur.cost;

            if (cost > dist[r][c][t]) continue;

            // If reached destination
            if (r == m - 1 && c == n - 1) {
                return (int) cost;
            }

            // Normal moves: right & down
            if (r + 1 < m) {
                long nc = cost + grid[r + 1][c];
                if (nc < dist[r + 1][c][t]) {
                    dist[r + 1][c][t] = nc;
                    pq.offer(new State(r + 1, c, t, nc));
                }
            }

            if (c + 1 < n) {
                long nc = cost + grid[r][c + 1];
                if (nc < dist[r][c + 1][t]) {
                    dist[r][c + 1][t] = nc;
                    pq.offer(new State(r, c + 1, t, nc));
                }
            }

            // Teleport
            if (t < k) {
                int val = grid[r][c];
                for (int[] cell : cells) {
                    if (cell[0] > val) break;

                    int nr = cell[1];
                    int nc = cell[2];

                    if (cost < dist[nr][nc][t + 1]) {
                        dist[nr][nc][t + 1] = cost;
                        pq.offer(new State(nr, nc, t + 1, cost));
                    }
                }
            }
        }

        return -1; // should never happen
    }
}
