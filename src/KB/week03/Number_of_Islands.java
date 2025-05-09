package KB.week03;

import java.util.LinkedList;
import java.util.Queue;

/* ================================================================
 *
 * Problem  : Leetcode - Number of Islands
 * Author   : 김로아
 * Date     : 2025-05-09
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 땅을 의미하는 1과 물을 의미하는 0으로 이루어진 행렬이 주어진다.
 * 땅은 수직과 수평방향으로만 연결될 수 있다.
 * 연결된 땅, 즉 섬의 개수를 구하라.
 *
 * # 입력
 * 0과 1로 이루어진 행렬
 * Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
 * # 출력
 * 섬의 개수
 *
 * 💻 알고리즘 설계
 * 1) 상하좌우로 한칸씩 이동하면서 동적으로 탐색.
 * 2) bfs 내부에서 조건을 (0부터 행렬길이 이내인지, 해당 값이 1인지, 방문안했는지) 검사
 * 3) 만족하는 경우 방문 처리하고 큐 삽입
 * 4) 전체 행렬을 (0,0)부터 돌면서 방문하지 않았고, 1인 경우 bfs 실행한 후 count++
 * 5) count를 리턴
 *
 * ⏰ 시간복잡도
 * O(N*M) N은 행 길이, M는 열 길이
 * ================================================================
 */

class Number_of_Islands {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static int rowLength;
    static int colLength;

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < rowLength && 0 <= c && c < colLength;
    }

    public static void bfs(int r, int c, char[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (isValid(nextR, nextC) && !visited[nextR][nextC] && grid[nextR][nextC] == '1') {
                    q.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        rowLength = grid.length;
        colLength = grid[0].length;
        visited = new boolean[rowLength][colLength];
        int count = 0;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }
}
