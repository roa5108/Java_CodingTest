package PGS.Lv2;

/* ================================================================
 *
 * Problem  : PGS - 피로도
 * Author   : 김로아
 * Date     : 2025-06-17
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 일정 피로도를 사용해 던전 탐험 가능.
 * '최소 필요 피로도'와 '소모 피로도'가 주어졌을 때, 유저가 탐험할 수 있는 최대 던전 수 리턴하기
 *
 * # 입력
 * 유저의 현재 피로도 k,
 * 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열 dungeons
 *
 * # 출력
 * 유저가 탐험할수 있는 최대 던전 수
 *
 * 💻 알고리즘 설계
 * 재귀 + 백트래킹으로 완전 탐색
 * 1) k가 던전의 최소 필요 피로도 이상이면서, 방문하지 않은 던전인 경우 curr에 추가, visited=true, 재귀
 * 2) 재귀 함수에 매개변수 전달 시, 현재 피로도 k에서 소모 피로도를 뺀 값을 전달 (k 자체를 바꾸지 않음)
 * 3) 완전 탐색하면서 모든 던전을 돌아 최대 던전 수를 구해야 하므로, 방문 취소 처리
 * 4) ans 이중 리스트에서 리스트 크기들 중 최댓값 리턴하면 정답
 *
 * ⏰ 시간복잡도
 * O(n * n!)
 * ================================================================
 */

import java.util.ArrayList;
import java.util.List;

class 피로도 {
    public int solution(int k, int[][] dungeons) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[dungeons.length];
        recur(k, dungeons, new ArrayList<>(), ans, visited);
        int maxCnt = 0;
        for (List<Integer> sub : ans) {
            if (sub.size() > maxCnt) {
                maxCnt = sub.size();
            }
        }
        return maxCnt;
    }

    public void recur(int k, int[][] dungeons, List<Integer> curr, List<List<Integer>> ans, boolean[] visited) {
        ans.add(new ArrayList<>(curr));
        for (int i = 0; i < dungeons.length; i++) {
            if (k >= dungeons[i][0]) {
                if (!visited[i]) {
                    curr.add(i);
                    visited[i] = true;
                    recur(k - dungeons[i][1], dungeons, curr, ans, visited);
                    curr.remove(curr.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}