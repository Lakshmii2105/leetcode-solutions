class Solution {
    static class Edge {
        int to, cost;
        Edge(int t, int c) {
            to = t;
            cost = c;
        }
    }

    public int minCost(int n, int[][] edges) {
        List<Edge>[] graph = new ArrayList[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];

            // normal edges
            graph[2*u].add(new Edge(2*v, w));
            graph[2*u + 1].add(new Edge(2*v, w));

            // reversed edge using switch at v
            graph[2*v].add(new Edge(2*u, 2 * w));
        }

        int[] dist = new int[2 * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int[]> pq =
            new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], node = cur[1];

            if (d > dist[node]) continue;

            for (Edge e : graph[node]) {
                if (dist[e.to] > d + e.cost) {
                    dist[e.to] = d + e.cost;
                    pq.offer(new int[]{dist[e.to], e.to});
                }
            }
        }

        int ans = Math.min(dist[2*(n-1)], dist[2*(n-1) + 1]);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
