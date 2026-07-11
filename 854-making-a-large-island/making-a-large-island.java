class Solution {

    private int n;
    private int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int largestIsland(int[][] grid) {
        n = grid.length;

        Map<Integer, Integer> islandSize = new HashMap<>();
        int id = 2;

        // Color each island and store its size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, id);
                    islandSize.put(id, size);
                    id++;
                }
            }
        }

        int max = 0;

        // Existing largest island
        for (int size : islandSize.values()) {
            max = Math.max(max, size);
        }

        // Try changing each 0 to 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {

                    Set<Integer> seen = new HashSet<>();
                    int size = 1;

                    for (int[] d : directions) {
                        int x = i + d[0];
                        int y = j + d[1];

                        if (x >= 0 && y >= 0 && x < n && y < n) {
                            int islandId = grid[x][y];

                            if (islandId > 1 && seen.add(islandId)) {
                                size += islandSize.get(islandId);
                            }
                        }
                    }

                    max = Math.max(max, size);
                }
            }
        }

        return max == 0 ? n * n : max;
    }

    private int dfs(int[][] grid, int r, int c, int id) {

        if (r < 0 || c < 0 || r >= n || c >= n || grid[r][c] != 1) {
            return 0;
        }

        grid[r][c] = id;

        int size = 1;

        for (int[] d : directions) {
            size += dfs(grid, r + d[0], c + d[1], id);
        }

        return size;
    }
}

        
    
