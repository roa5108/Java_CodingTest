package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 단어 변환
 * Author   : 김로아
 * Date     : 2025-05-24
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 규칙을 지키면서 begin 단어를 target 단어로 변환하는 가장 짧은 과정을 찾자.
 * 규칙 1) 한 번에 한 개의 알파벳만 변환 가능
 * 규칙 2) 주어진 words 배열에 있는 단어로만 변환 가능
 *
 * # 입력
 * 두 개의 단어 begin, target
 * 단어의 집합 words 문자열 배열
 *
 * # 출력
 * 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return
 *
 * 💻 알고리즘 설계
 * 1) 현재 단어와 비교할 다음 단어의 알파벳 차이가 1인지(변환 가능한지) 검사하는 canConvert 함수 정의
 * 2) 몇단계인지 나타내는 depth 변수 선언
 * 3) 큐를 생성해 words 배열을 bfs 돌기 (미방문이면서, canConvert가 true인 단어만 큐에 넣기)
 * 4) 같은 레벨에 있는 단어들을 다 돌고 나서 depth++
 * 5) begin과 target이 같아졌을 시점에 depth 리턴하면 정답
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class 단어_변환 {
    public int solution(String begin, String target, String[] words) {
        Queue<String> queue = new ArrayDeque<>();
        Map<String, Boolean> visited = new HashMap<>();

        // depth = 0일 때는 시작 단어 1개, ex) hit
        // depth = 1일 때는 begin과 한 글자 차이 나는 단어들, ex) hot
        // depth = 2일 때는 다시 그 단어들과 한 글자 차이 나는 단어들 ex) dot, lot
        int depth = 0; //몇단계 거쳤는지

        // begin부터 bfs 시작
        queue.add(begin);
        visited.put(begin, true);

        while (!queue.isEmpty()) {
            int size = queue.size(); // 바꿀 수 있는 단어가 여러개일 수 있으니까 (같은 깊이에서 여러 단어가 존재할 때 대비)

            // 같은 depth의 단어들만 처리
            for (int i = 0; i < size; i++) { //한 depth 레벨의 단어들을 전부 순회한 뒤에만 depth++ 하기 위해.
                String cur = queue.remove();
                if (cur.equals(target)) { // target을 찾으면 depth 리턴 후 종료
                    return depth;
                }
                for (String word : words) {
                    if (canConvert(cur, word) && !visited.containsKey(word)) {
                        queue.add(word);
                        visited.put(word, true);
                    }
                }
            }
            depth++; // 다음 레벨로 넘어감
        }
        return 0;
    }

    // 현재 단어와 다음 비교 대상 단어의 알파벳 차이가 1 나면 true
    public boolean canConvert(String cur, String next) {
        int diff = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) != next.charAt(i)) {
                diff++;
            }
        }
        if (diff == 1) {
            return true;
        }
        return false;
    }
}