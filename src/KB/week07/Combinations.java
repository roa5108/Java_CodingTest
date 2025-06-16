package KB.week07;

/* ================================================================
 *
 * Problem  : LeetCode - Combinations
 * Author   : 김로아
 * Date     : 2025-06-16
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 정수 n과 k가 주어지고, [1, n] 범위에서 k개를 골라 가능한 모든 조합을 리턴하기
 *
 * # 입력
 * 정수 n, k
 * Input: n = 4, k = 2
 *
 * # 출력
 * 1부터 n까지의 정수에서 k개를 골라 만들 수 있는 모든 조합
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 *
 * 💻 알고리즘 설계
 * 재귀 + 백트래킹을 사용해 가능한 조합들을 구하고, 정답 배열에 추가하기.
 * start 변수를 따로 두어 중복을 방지. start부터 탐색.
 * 다음 숫자는 현재 숫자보다 커야함.
 *
 * ⏰ 시간복잡도
 * 	O(C(n, k) × k)
 * ================================================================
 */

import java.util.ArrayList;
import java.util.List;

class Combinations {
    private static void backtrack(int start, List<Integer> curr, int n, int k, List<List<Integer>> ans) {
        if (curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i <= n; i++) {
            curr.add(i);
            backtrack(i + 1, curr, n, k, ans);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(1, new ArrayList<>(), n, k, ans);
        return ans;
    }
}