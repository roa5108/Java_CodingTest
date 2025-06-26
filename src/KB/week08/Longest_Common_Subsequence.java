package KB.week08;

/* ================================================================
 *
 * Problem  : LeetCode - Longest Common Subsequence
 * Author   : 김로아
 * Date     : 2025-06-26
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 두 문자열의 가장 긴 공통 부분 수열의 길이 반환하기
 * 없으면 0을 반환
 * (공통 부분 수열 -> 두 수열(문자열)에서 순서를 유지한 채 공통으로 존재하는 가장 긴 부분 수열)
 *
 * # 입력
 * 두 개의 문자열 text1, text2 (길이 최대 1000)
 *
 * # 출력
 * 두 문자열의 가장 긴 공통 부분 수열(LCS)의 길이
 *
 * 💻 알고리즘 설계
 * 1) 두 문자열의 길이를 각각 m, n이라 할 때, 크기 (m+1) x (n+1)의 2차원 DP 배열 생성
 * 2) dp[i][j]는 text1의 i번째 문자까지와 text2의 j번째 문자까지 비교했을 때 LCS의 최대 길이를 의미
 * 3) 문자열의 인덱스를 1부터 시작해 비교하며 다음과 같은 점화식을 사용:
 *    - text1[i-1] == text2[j-1] → dp[i][j] = dp[i-1][j-1] + 1
 *    - 그 외 → dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * 4) 최종 정답은 dp[m][n]에 저장됨
 *
 * ⏰ 시간복잡도
 *  O(m * n) → text1과 text2의 모든 문자쌍을 비교
 * ================================================================
 */

class Longest_Common_Subsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1]; // dp[i][j]는 text1[0..i-1]과 text2[0..j-1]의 LCS 길이

        // 모든 부분 문자열에 대해 점화식을 이용해 LCS 계산
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 문자가 같으면 이전 값 + 1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // 전체 문자열에 대한 LCS 길이 반환
        return dp[m][n];
    }
}