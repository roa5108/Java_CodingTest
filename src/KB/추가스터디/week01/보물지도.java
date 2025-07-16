package KB.추가스터디.week01;

/* ================================================================
 *
 * Problem  : PGS - 보물지도
 * Author   : 김로아
 * Date     : 2025년 07월 15일
 *
 * ================================================================
 * 📌 문제 분석 요약
 * - (1,1)에서 (n,m)으로 함정을 피해 최소 시간으로 이동해야함
 * - 상하좌우 한 칸씩 이동 가능하고, 걸리는 시간은 1
 * - 신비로운 신발 한 번 사용 가능, 두 칸 이동되며 걸리는 시간은 1
 *
 * # 입력
 * - n, m, 함정 위치를 나타내는 2차원 정수 배열 hole
 *
 * # 출력
 * - 최소 시간 리턴
 * - 이동 불가능하다면 -1 리턴
 *
 * 💻 알고리즘 설계
 * 1. visited에서 usedShoes 항목까지 체크하도록함
 * 2. 상하좌우 조건 체크하며 BFS 를 돈다
 * 3. usedShoes가 0인 경우, 신발 사용 처리
 *
 * ⏰ 시간복잡도
 * -
 * ================================================================
 */

import java.util.ArrayDeque;
import java.util.Queue;

class 보물지도 {
    int[] dr = {1, -1, 0, 0}; // 행:상하좌우
    int[] dc = {0, 0, -1, 1}; // 열:상하좌우

    public int solution(int n, int m, int[][] hole) {
        boolean[][][] visited = new boolean[n + 1][m + 1][2]; //n, m, usedShoes
        boolean[][] isHole = new boolean[n + 1][m + 1];

        for (int[] h : hole) {
            isHole[h[0]][h[1]] = true; //함정 표시
        }

        // BFS
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 1, 0, 0}); //n, m, usedShoes, time
        visited[1][1][0] = true;
        while (!q.isEmpty()) {
            int[] now = q.remove();
            int cr = now[0], cc = now[1], usedShoes = now[2], time = now[3];
            if (cr == n && cc == m) return time; // 도착 시 시간 리턴

            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                int nr = cr + dr[i], nc = cc + dc[i];
                if (1 <= nr && nr <= n && 1 <= nc && nc <= m && !visited[nr][nc][usedShoes] && !isHole[nr][nc]) {
                    visited[nr][nc][usedShoes] = true;
                    q.add(new int[]{nr, nc, usedShoes, time + 1});
                }
            }

            if (usedShoes == 0) { // 신비로운 신발 미사용 시, 사용 처리
                for (int i = 0; i < 4; i++) {
                    int nr = cr + dr[i] * 2, nc = cc + dc[i] * 2; // 신발은 두 칸 이동
                    if (1 <= nr && nr <= n && 1 <= nc && nc <= m && !visited[nr][nc][1] && !isHole[nr][nc]) {
                        visited[nr][nc][1] = true;
                        q.add(new int[]{nr, nc, 1, time + 1});
                    }
                }
            }
        }
        return -1;
    }
}
