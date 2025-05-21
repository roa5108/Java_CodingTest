package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* ================================================================
 *
 * Problem  : BOJ_2529 - 부등호
 * Author   : 김로아
 * Date     : 2025-05-21
 *
 * ================================================================
 * 📌 문제 분석 요약
 *
 * # 입력
 *
 * # 출력
 *
 * 💻 알고리즘 설계
 *
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

public class B_2529 {
    static int n;
    static String[] signs;
    static boolean[] visited = new boolean[10];
    static List<String> results = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        signs = br.readLine().split(" ");
        dfs(0, "");

        Collections.sort(results);
        System.out.println(results.get(results.size() - 1));
        System.out.println(results.get(0));
    }

    static boolean isValid(String op, int a, int b) {
        if (op.equals("<")) return a < b;
        if (op.equals(">")) return a > b;
        return false;
    }

    static void dfs(int depth, String num) {
        if (depth == n + 1) {
            results.add(num);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) continue;
            if (depth == 0 || isValid(signs[depth - 1], num.charAt(depth - 1) - '0', i)) {
                visited[i] = true;
                dfs(depth + 1, num + i);
                visited[i] = false;
            }
        }
    }
}
