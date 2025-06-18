package KB.week07;

/* ================================================================
 *
 * Problem  : LeetCode - Word Search
 * Author   : 김로아
 * Date     : 2025-06-18
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 2차원 보드(board)에서 단어(word)의 글자들이 상하좌우로 인접하게 연결되어 있는지 확인하는 문제
 * 같은 셀을 중복해서 사용할 수 없으며, 정확히 word의 순서대로 연속되어야 함.
 *
 * # 입력
 * - char[][] board : 대소문자 알파벳으로 이루어진 2차원 배열 (최대 6x6 크기)
 * - String word : 찾고자 하는 단어 (최대 15자)
 *
 * # 출력
 * - boolean : 보드 내에서 해당 단어를 찾을 수 있으면 true, 아니면 false
 *
 * 💻 알고리즘 설계
 * 1. board의 모든 좌표(i, j)를 시작점으로 DFS 탐색 시작
 * 2. 현재 좌표의 문자가 word의 현재 문자와 일치하면 다음 문자로 재귀
 * 3. 방문 처리 및 백트래킹(visited를 true로 했다가 재귀 후 false로 복원)
 * 4. 인접한 4방향(상하좌우)으로 이동하며 word 전체가 매칭되면 true 리턴
 * 5. DFS로 하나라도 일치하면 전체 탐색을 중단하고 true 반환
 *
 * ⏰ 시간복잡도
 * - 최악의 경우 O(M * N * 4^L)
 *   (M*N: 시작 좌표 수, L: 단어 길이, 각 위치에서 최대 4방향 탐색)
 *
 * ================================================================
 */


class Word_Search {
    static int m, n;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, boolean[][] visited, int r, int c, int idx) {
        if (idx == word.length()) return true;
        if (r < 0 || r >= m || c < 0 || c >= n) return false;
        if (visited[r][c] || board[r][c] != word.charAt(idx)) return false;
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (dfs(board, word, visited, nr, nc, idx + 1)) return true;
        }
        visited[r][c] = false;
        return false;
    }
}

