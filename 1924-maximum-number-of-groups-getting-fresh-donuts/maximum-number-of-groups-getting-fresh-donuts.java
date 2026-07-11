class Solution {
    Map<String, Integer> memo = new HashMap<>();
    int batchSize;

    public int maxHappyGroups(int batchSize, int[] groups) {
        this.batchSize = batchSize;

        int[] cnt = new int[batchSize];
        int ans = 0;

        for (int g : groups) {
            int r = g % batchSize;
            if (r == 0)
                ans++;
            else
                cnt[r]++;
        }

        return ans + dfs(cnt, 0);
    }

    private int dfs(int[] cnt, int remain) {

        String key = Arrays.toString(cnt) + "#" + remain;

        if (memo.containsKey(key))
            return memo.get(key);

        int best = 0;

        for (int r = 1; r < batchSize; r++) {

            if (cnt[r] == 0)
                continue;

            cnt[r]--;

            int nextRemain = (remain + r) % batchSize;

            int happy = (remain == 0 ? 1 : 0)
                    + dfs(cnt, nextRemain);

            best = Math.max(best, happy);

            cnt[r]++;
        }

        memo.put(key, best);

        return best;
    }
}

        
    
