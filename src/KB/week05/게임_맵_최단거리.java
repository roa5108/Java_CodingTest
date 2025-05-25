package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 게임 맵 최단거리
 * Author   : 김로아
 * Date     : 2025-05-24
 *
 * ================================================================
 * 📌 문제 분석 요약
 * nxm 크기의 게임 맵 maps 인접행렬이 주어지고, 0은 벽, 1은 길
 * 캐릭터가 (1,1) 위치에서 상대방 진영인 (n,m) 위치까지 최단거리로 가야함
 * 도달하지 못하면 -1 반환
 *
 * # 입력
 * nxm 크기의 maps 배열
 *
 * # 출력
 * 상대팀 진영에 도착하기 위해 지나야 하는 칸의 최소 개수
 *
 * 💻 알고리즘 설계
 * (0,0) ~ (n-1,m-1) 로 좌표를 변경해서 풀기 쉽게 만듦
 * 정석 암시적 그래프 문제, bfs로 최단거리 구하기
 * 동서남북으로 한 칸씩 이동 가능하므로, dr, dc를 통해 이동 가능한지 체크 후 이동
 * 거리 변수를 따로 만들지 않고 이동할 때마다 maps 값 자체를 +1 시키는 방식으로 구현
 * 시작점 (0,0)에서 도착점 (n-1,m-1)까지의 최단 경로 하나만 탐색하면 됨. (모든좌표에 대해 bfs 수행 X)
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayDeque;
import java.util.Queue;

class 게임_맵_최단거리 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m;

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        boolean[][] visited = new boolean[n][m];

        bfs(0, 0, visited, maps);
        if (maps[n - 1][m - 1] <= 1) return -1;
        return maps[n - 1][m - 1];
    }

    public void bfs(int r, int c, boolean[][] visited, int[][] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];
                if (0 <= nextR && nextR < n && 0 <= nextC && nextC < m && !visited[nextR][nextC] && maps[nextR][nextC] == 1) {
                    visited[nextR][nextC] = true;
                    maps[nextR][nextC] = maps[curR][curC] + 1;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }
    }
}