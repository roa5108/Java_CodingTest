package KB.week07;

/* ================================================================
 *
 * Problem  : LeetCode - Permutations
 * Author   : 김로아
 * Date     : 2025-06-13
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 서로 다른 정수들이 담긴 nums 배열이 주어지고, 가능한 순열을 모두 리턴. (순서는 상관없다.)
 *
 * # 입력
 * 서로 다른 정수들 담긴 nums 배열
 * Input: nums = [1,2,3]
 *
 *
 * # 출력
 * 가능한 순열들 배열
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 💻 알고리즘 설계
 * 재귀 + 백트래킹으로 nums를 돌며 순열 배열을 하나씩 정답 배열에 추가하기.
 *
 * ⏰ 시간복잡도
 * O(n! * n)
 * ================================================================
 */

import java.util.ArrayList;
import java.util.List;

class Permutations {
    public static void backtrack(int r, List<Integer> curr, int[] nums, boolean[] visited, List<List<Integer>> ans) {
        if (curr.size() == r) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curr.add(nums[i]);
                visited[i] = true;
                backtrack(r, curr, nums, visited, ans);
                curr.remove(curr.size() - 1);
                visited[i] = false;
            }
        }
    }

    // 메인 함수
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(nums.length, new ArrayList<>(), nums, visited, ans);
        return ans;
    }
}