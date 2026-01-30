import java.util.*;

class Solution {
    public long minimumCost(
            String source,
            String target,
            String[] original,
            String[] changed,
            int[] cost
    ) {
        int n = source.length();
        long INF = (long) 1e18;

        // 1. Collect all unique transform strings
        Set<String> set = new HashSet<>();
        for (String s : original) set.add(s);
        for (String s : changed) set.add(s);

        List<String> list = new ArrayList<>(set);
        int m = list.size();

        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < m; i++) {
            idx.put(list.get(i), i);
        }

        // 2. Floyd–Warshall
        long[][] dist = new long[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            int u = idx.get(original[i]);
            int v = idx.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 3. Group original strings by length
        Map<Integer, List<String>> byLength = new HashMap<>();
        for (String s : set) {
            byLength.computeIfAbsent(s.length(), k -> new ArrayList<>()).add(s);
        }

        // 4. DP
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;

            // No operation
            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            // Try only valid substring lengths
            for (int len : byLength.keySet()) {
                int j = i + len;
                if (j > n) continue;

                String s = source.substring(i, j);
                String t = target.substring(i, j);

                if (s.equals(t)) {
                    dp[j] = Math.min(dp[j], dp[i]);
                } else if (idx.containsKey(s) && idx.containsKey(t)) {
                    long d = dist[idx.get(s)][idx.get(t)];
                    if (d < INF) {
                        dp[j] = Math.min(dp[j], dp[i] + d);
                    }
                }
            }
        }

        return dp[n] == INF ? -1 : dp[n];
    }
}
