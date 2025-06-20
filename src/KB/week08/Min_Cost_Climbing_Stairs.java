package KB.week08;

/* ================================================================
 *
 * Problem  :
 * Author   : 김로아
 * Date     : 2025-06-20
 *
 * ================================================================
 * 📌 문제 분석 요약
 * i번째 계단의 비용은 cost[i]이다.
 * 각 계단의 비용이 주어졌을 때, 꼭대기까지 오르려면 필요한 최소 비용은 몇?
 * 계단은 한 번에 1개 혹은 2개 오를 수 있고, 0번째 혹은 1번째 index에서 시작 가능
 * 꼭대기는 마지막 인덱스 그 다음 위치
 *
 * # 입력
 * 각 계단의 비용 배열
 * Input: cost = [10,15,20]
 *
 * # 출력
 * 꼭대기까지 오르는 데 필요한 최소 비용
 * Output: 15
 *
 * 💻 알고리즘 설계
 * - DP 사용 (Top-Down 방식 with Memoization)
 * 1) 꼭대기에 도달할 수 있는 계단 수: cost.length
 * 2) dp(n): n번째 계단에 도달하는 데 드는 최소 비용
 * 3) 시작점은 0 또는 1번 계단이므로, dp(0) = 0, dp(1) = 0
 * 4) 점화식:
 *    dp(n) = min(dp(n-1) + cost[n-1], dp(n-2) + cost[n-2])
 *    → n-1 또는 n-2 계단에서 오는 두 가지 경로 중 더 저렴한 비용 선택
 *
 * ⏰ 시간복잡도
 * - O(n): 각 계단마다 한 번씩만 계산됨 (메모이제이션)
 * - 공간복잡도 O(n): memo 저장용
 * ================================================================
 */

import java.util.HashMap;
import java.util.Map;

class Min_Cost_Climbing_Stairs {
    static Map<Integer, Integer> memo;

    public int minCostClimbingStairs(int[] cost) {
        memo = new HashMap<>();
        int n = cost.length;
        return dp(cost, n);
    }

    public int dp(int[] cost, int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            // n-1 번째 계단까지 드는 최소 비용 + 마지막에 n-1에서 한 칸 올라오는 비용
            // n-2 번째 계단까지 드는 최소 비용 + 마지막에 n-2에서 두 칸 올라오는 비용
            // 둘 중 작은 값을 memo에 저장
            memo.put(n, Math.min(dp(cost, n - 1) + cost[n - 1], dp(cost, n - 2) + cost[n - 2]));
        }
        return memo.get(n);
    }
}