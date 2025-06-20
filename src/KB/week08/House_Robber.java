package KB.week08;

/* ================================================================
 *
 * Problem  : LeetCode - House Robber
 * Author   : 김로아
 * Date     : 2025-06-20
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 도둑은 인접한 두 집을 털 수 없고, 각 집에는 돈이 들어있다.
 * 최대 얼마를 훔칠 수 있는지를 구하는 문제.
 * 한 칸 건너뛰면서 최적 선택을 누적해나가는 최적화 문제 → DP로 해결 가능
 *
 * # 입력
 * int[] nums:
 * 각 집에 있는 돈의 배열 (1 ≤ nums.length ≤ 100, 0 ≤ nums[i] ≤ 400)
 *
 * # 출력
 * int: 훔칠 수 있는 최대 금액
 *
 * 💻 알고리즘 설계
 * - dp[n]: 0번째부터 (n-1)번째 집까지 중 훔칠 수 있는 최대 금액
 * - 점화식: dp[n] = max(dp[n-1], dp[n-2] + nums[n-1])
 *   → 현재 집을 털면: 두 칸 전까지의 최댓값 + 현재 집 금액
 *   → 현재 집을 안 털면: 직전까지의 최댓값 그대로
 * - Top-down 방식 (재귀 + 메모이제이션)으로 구현
 * - memo에 dp 결과 저장해 중복 계산 방지
 *
 * ⏰ 시간복잡도
 * O(n)
 * ================================================================
 */

import java.util.HashMap;
import java.util.Map;

class House_Robber {
    static int[] nums;
    static Map<Integer, Integer> memo;

    public int rob(int[] nums) {
        this.nums = nums;
        memo = new HashMap<>();
        int n = nums.length;
        return dp(n);
    }

    public int dp(int n) {
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]); // 첫 번째 집 또는 두 번째 집 중 돈이 더 많은 쪽 선택

        if (!memo.containsKey(n)) {
            // 현재 집을 털지 않을 경우: dp(n-1)
            // 현재 집을 털 경우: dp(n-2) + 현재 집의 돈(nums[n-1])
            // 두 경우 중 더 큰 값을 저장
            memo.put(n, Math.max(dp(n - 1), dp(n - 2) + nums[n - 1]));
        }
        return memo.get(n);
    }
}