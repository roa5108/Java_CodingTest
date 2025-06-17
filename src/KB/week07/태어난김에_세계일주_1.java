package KB.week07;

/* ================================================================
 *
 * Problem  : nossiDev - 태어난김에 세계일주 1
 * Author   : 김로아
 * Date     : 2025-06-17
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 여행자의 현재 통장 잔고 balance와,
 * 각 나라별 "입국 필요 잔고" 및 "여행 경비"가 담긴 2차원 배열 countries가 주어질 때,
 * 여행자가 방문할 수 있는 최대 국가 수를 반환
 *
 * # 입력
 * 여행자의 현재 통장 잔고 balance,
 * 각 나라별 "입국 필요 잔고" 및 "여행 경비"가 담긴 2차원 배열 countries
 * 입력: balance = 600, countries = [[70, 350], [100, 550], [350, 400]]
 *
 * # 출력
 * 여행자가 방문할 수 있는 최대 국가 수
 * 출력: 3
 *
 * 💻 알고리즘 설계
 * 재귀 + 백트래킹으로 완전 탐색
 * 1) balance가 국가의 입국 필요 잔고 이상이면서, 방문하지 않은 국가인 경우 curr에 추가, visited=true, 재귀
 * 2) 재귀 함수에 매개변수 전달 시, 현재 잔고 balance에서 여행 경비를 뺀 값을 전달 (balance 자체를 바꾸지 않음)
 * 3) 완전 탐색하면서 모든 국가들을 돌아 최대 국가 수를 구해야 하므로, 방문 취소 처리
 * 4) ans 이중 리스트에서 리스트 크기들 중 최댓값 리턴하면 정답
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayList;
import java.util.List;

class 태어난김에_세계일주_1 {
    public int solution(int balance, int[][] countries) {
        int maxCnt = 0;
        boolean[] visited = new boolean[countries.length];
        List<List<Integer>> ans = new ArrayList<>();
        recur(balance, countries, visited, new ArrayList<>(), ans);
        for (List<Integer> list : ans) {
            maxCnt = Math.max(maxCnt, list.size());
        }
        return maxCnt;
    }

    public void recur(int balance, int[][] countries, boolean[] visited, List<Integer> curr, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(curr));
        for (int i = 0; i < countries.length; i++) {
            if (balance >= countries[i][1] && !visited[i]) {
                curr.add(i);
                visited[i] = true;
                recur(balance - countries[i][0], countries, visited, curr, ans);
                curr.remove(curr.size() - 1);
                visited[i] = false;
            }
        }
    }
}
