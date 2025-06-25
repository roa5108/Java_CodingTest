package KB.week08;

/* ================================================================
 *
 * Problem  : LeetCode - Coin Change
 * Author   : 김로아
 * Date     : 2025-06-25
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 동전 종류가 주어졌을 때, amount를 만들 수 있는 최소 동전 개수는?
 * 동전은 무한히 사용 가능
 *
 * # 입력
 * 동전 종류 배열
 *
 * # 출력
 * amount를 만들 수 있는 최소 동전 개수
 *
 * 💻 알고리즘 설계
 * Bottom-Up DP
 * dp[i] = i원을 만들기 위한 최소 동전 개수
 * 점화식: dp[i] = min(dp[i - coin] + 1) for each coin in coins
 * dp[0] = 0: 0원을 만들기 위해선 동전이 0개 필요
 * dp[i]를 모두 초기값 amount+1로 설정 (불가능을 의미)
 * 최종적으로 dp[amount]가 여전히 초기값이면 → -1 반환
 *
 * ⏰ 시간복잡도
 * O(amount × n) - amount는 목표 금액, n은 동전 종류 개수
 * ================================================================
 */

import java.util.Arrays;

class Coin_Change {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // dp[i] = i원을 만드는 최소 동전 수
        Arrays.fill(dp, amount + 1); // 초기값: 불가능한 큰 수
        dp[0] = 0;

        // 모든 금액에 대해 최소 동전 수 계산
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // 최종 결과 확인
        return dp[amount] > amount ? -1 : dp[amount];
    }
}