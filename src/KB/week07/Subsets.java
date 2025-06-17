package KB.week07;

/* ================================================================
 *
 * Problem  : LeetCode - Subsets
 * Author   : 김로아
 * Date     : 2025-06-17
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 정수 배열이 주어지고, 가능한 모든 부분 집합을 리턴하기 (중복 X, 순서는 상관없음)
 * # 입력
 * 정수 배열
 * Input: nums = [1,2,3]
 *
 * # 출력
 * 부분 집합들을 배열에 담아 리턴
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 💻 알고리즘 설계
 * 재귀 + 백트래킹 사용
 * 공집합, 자기 자신도 포함이므로 재귀함수 호출 시 부분 집합을 곧바로 정답 배열에 추가.
 *
 * ⏰ 시간복잡도
 * O(2^n * n)
 * ================================================================
 */

import java.util.ArrayList;
import java.util.List;

class Subsets {
    public static void backtrack(int start, List<Integer> curr, int[] nums, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(curr));
        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack(i + 1, curr, nums, ans);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(0, new ArrayList<>(), nums, ans);
        return ans;
    }
}
