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
    int[] dr = {-1, 1, 0, 0}; // 상하좌우 방향 이동 (행 변화)
    int[] dc = {0, 0, -1, 1}; // 상하좌우 방향 이동 (열 변화)

    public boolean exist(char[][] board, String word) {
        m = board.length; // 보드의 행 길이
        n = board[0].length; // 보드의 열 길이
        boolean[][] visited = new boolean[m][n];

        // 보드의 모든 칸을 시작점으로 시도해봄
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // (i,j)에서 DFS 시작. 만약 찾으면 true 리턴
                if (dfs(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false; // 끝까지 못 찾았으면 false
    }

    public boolean dfs(char[][] board, String word, boolean[][] visited, int r, int c, int idx) {
        // 단어의 모든 글자를 다 찾은 경우
        if (idx == word.length()) return true;

        // 범위 벗어남 또는 이미 방문함 또는 현재 글자가 다름 → 실패
        if (r < 0 || r >= m || c < 0 || c >= n) return false;
        if (visited[r][c] || board[r][c] != word.charAt(idx)) return false;
        visited[r][c] = true;

        // 상하좌우 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            // 다음 칸에서 계속 단어 찾기
            if (dfs(board, word, visited, nr, nc, idx + 1)) return true;
        }
        visited[r][c] = false; // 백트래킹: 방문 해제 (다른 경로 위해 복원)
        return false; // 4방향 다 해도 못 찾으면 false
    }
}

