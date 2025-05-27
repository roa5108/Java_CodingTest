package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 보물지도
 * Author   : 김로아
 * Date     : 2025-05-27
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 1) 가로 길이가 n, 세로 길이가 m인 직사각형 모양의 보물 지도
 * 2) 출발점에서 함정을 피해 보물 위치로 이동해야함.
 * 3) 이동할 때는 [상, 하, 좌, 우]로 한 칸 걸어서 이동 -> 걸리는 시간은 1
 * 4) 신비로운 신발 사용하면 한 번에 두 칸 이동, 함정도 넘을 수 있음, but 한 번만 사용 가능 -> 걸리는 시간은 1
 * 5) 출발점에서 보물까지 이동하는데 필요한 최소 시간 구하기 !
 *
 * # 입력
 * 가로 길이를 나타내는 정수 n -> 열 길이
 * 세로 길이를 나타내는 정수 m -> 행 길이
 * 함정의 위치를 나타내는 2차원 정수 배열 hole
 *
 * # 출력
 * 보물이 있는 칸으로 이동하는데 필요한 최소 시간
 * 단, 보물이 있는 칸으로 이동할 수 없다면, -1을 return
 *
 * 💻 알고리즘 설계
 * 1) <일반 이동>, <점프 이동> 각각 이동 거리 배열(dc, dr, shoesDc, shoesDr) 선언
 * 2) 최단 시간을 구해야 하므로 bfs로 구현 -> queue, visited, isHole 선언
 * 3) 함정 위치는 boolean형 2차원 배열로 isHole 구현
 * 4) 걸어서 이동한 지점이 도착지점이면 time + 1 리턴
 * 5-1) (일반 이동 경우) 이동 가능한지 검사하고, 함정이 아니라면 방문 체크, 큐에 추가
 * 5-2) (일반 이동 경우) 계산한 지점이 함정이고, 신발을 사용하지 않은 상태라면, 점프 이동 지점 계산
 * 5-3) 점프한 지점이 도착지점이면 time + 1 리턴
 * 5-4) (점프 이동 경우) 이동 가능한지 검사하고, 함정이 아니라면 방문 체크, 큐에 추가
 * 6) 보물이 있는 칸으로 이동하지 못한 경우 -1 리턴
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayDeque;
import java.util.Queue;

class 보물지도 {
    // 기본 이동: 아래, 위, 왼쪽, 오른쪽
    public static int[] dc = {0, 0, -1, 1};
    public static int[] dr = {1, -1, 0, 0};

    // 신발 사용 시 이동 (2칸 점프)
    public static int[] shoesDc = {0, 0, -2, 2};
    public static int[] shoesDr = {2, -2, 0, 0};

    // 행 : m, 열 : n
    public int solution(int m, int n, int[][] hole) {
        Queue<int[]> queue = new ArrayDeque<>(); // [r, c, usedShoes, time]
        boolean[][][] visited = new boolean[m + 1][n + 1][2]; // visited[r][c][usedShoes]
        boolean[][] isHole = new boolean[m + 1][n + 1]; // 함정 여부

        // 함정 위치 표시
        for (int[] h : hole) {
            isHole[h[0]][h[1]] = true;
        }

        // 시작 위치 큐 삽입
        queue.add(new int[]{1, 1, 0, 0});
        visited[1][1][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int curR = cur[0];
            int curC = cur[1];
            int usedShoes = cur[2];
            int time = cur[3];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                // 도착지라면 종료
                if (nextR == m && nextC == n) return time + 1;

                if (0 < nextR && nextR <= m && 0 < nextC && nextC <= n && !visited[nextR][nextC][usedShoes]) {
                    // 일반 이동
                    if (!isHole[nextR][nextC]) {
                        visited[nextR][nextC][usedShoes] = true;
                        queue.add(new int[]{nextR, nextC, usedShoes, time + 1});
                    }

                    // 점프 이동
                    else if (usedShoes == 0) {
                        int jumpR = curR + shoesDr[i];
                        int jumpC = curC + shoesDc[i];

                        // 도착지라면 종료
                        if (jumpR == m && jumpC == n) return time + 1;

                        if (0 < jumpR && jumpR <= m && 0 < jumpC && jumpC <= n && !visited[jumpR][jumpC][1] && !isHole[jumpR][jumpC]) {
                            visited[jumpR][jumpC][1] = true;
                            visited[jumpR][jumpC][1] = true;
                            queue.add(new int[]{jumpR, jumpC, 1, time + 1});
                        }
                    }
                }
            }
        }
        // 도달 불가능한 경우
        return -1;
    }
}
