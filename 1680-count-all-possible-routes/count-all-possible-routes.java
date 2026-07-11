class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] dp;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        dp = new int[n][fuel + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return dfs(locations, start, finish, fuel);
    }

    private int dfs(int[] locations, int curr, int finish, int fuel) {
        if (fuel < 0) {
            return 0;
        }

        if (dp[curr][fuel] != -1) {
            return dp[curr][fuel];
        }

        long ans = (curr == finish) ? 1 : 0;

        for (int next = 0; next < locations.length; next++) {
            if (next == curr) {
                continue;
            }

            int cost = Math.abs(locations[curr] - locations[next]);

            if (fuel >= cost) {
                ans = (ans + dfs(locations, next, finish, fuel - cost)) % MOD;
            }
        }

        dp[curr][fuel] = (int) ans;
        return dp[curr][fuel];
    }
}

        
    
