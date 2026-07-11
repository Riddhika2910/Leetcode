class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word; // Stores complete word if this node is the end
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c,
                     TrieNode node, List<String> result) {

        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return;

        char ch = board[r][c];

        if (ch == '#' || node.children[ch - 'a'] == null)
            return;

        node = node.children[ch - 'a'];

        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicates
        }

        board[r][c] = '#';

        dfs(board, r + 1, c, node, result);
        dfs(board, r - 1, c, node, result);
        dfs(board, r, c + 1, node, result);
        dfs(board, r, c - 1, node, result);

        board[r][c] = ch;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';

                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];
            }

            curr.word = word;
        }

        return root;
    }
}

        
    
