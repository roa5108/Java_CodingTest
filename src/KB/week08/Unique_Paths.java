package KB.week08;

/* ================================================================
 *
 * Problem  : LeetCode - Unique Paths
 * Author   : 김로아
 * Date     : 2025-06-20
 *
 * ================================================================
 * 📌 문제 분석 요약
 * - m x n 크기의 격자판이 주어진다.
 * - 좌상단 (1,1)에서 시작하여 우하단 (m,n)까지 오직 아래 또는 오른쪽으로만 이동할 수 있다.
 * - 총 몇 개의 경로로 목적지까지 도달할 수 있는지 구하는 문제
 *
 * # 입력
 * - m (1 ≤ m ≤ 100)
 * - n (1 ≤ n ≤ 100)
 *
 * # 출력
 * - 총 가능한 경로의 수 (정수)
 *
 * 💻 알고리즘 설계
 * - DFS + 메모이제이션(Top-down DP) 방식 사용
 * - 중복되는 (m, n)에 대한 하위 경로 수 계산을 Map에 저장하여 성능 최적화
 * - base case: m == 1 또는 n == 1인 경우는 오직 한 가지 경로만 존재
 * - memoization 키: (m, n) 쌍을 List<Integer>로 구성하여 Map에 저장
 *
 * ⏰ 시간복잡도
 * - O(m * n)
 *   각 (m, n) 쌍에 대해 최대 한 번씩만 DFS 수행됨
 *
 * ================================================================
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Unique_Paths {
    static Map<List<Integer>, Integer> memo;

    public int uniquePaths(int m, int n) {
        memo = new HashMap<>();
        return dfs(m, n);
    }

    public int dfs(int m, int n) {
        // base case: 한 쪽이 1이면 경로는 오직 한 가지
        if (m == 1 || n == 1) return 1;

        List<Integer> key = Arrays.asList(m, n);
        if (!memo.containsKey(key)) {
            memo.put(key, dfs(m, n - 1) + dfs(m - 1, n));
        }
        return memo.get(key);
    }
}